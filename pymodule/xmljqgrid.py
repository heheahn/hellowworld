#!/bin/env python
# -*- coding: utf-8 -*-

"""
xmljqgrid.py

Modular access to xmljqgrid, jQuery grid plugin. http://www.trirand.com/blog/


Example of usage:

# Without a view
controller:
    from modules.xmljqgrid import XmlJqGrid

    def mycontroller()
        return dict(xmljqgrid=XmlJqGrid(globals(), db.mytable)())

# With a view
controller:
    from modules.xmljqgrid import XmlJqGrid

    def mycontroller()
        return dict(xmljqgrid=XmlJqGrid(globals(), db.mytable)())

view:
    {{=xmljqgrid}}


# Customize xmljqgrid options
controller:
    from modules.xmljqgrid import XmlJqGrid

    def mycontroller():
        jqgrid_options = {
            'colNames': ['ID', 'Name', 'Category'],
            'colModel': [
              {'name': 'id', 'index': 'id', 'width': 55},
              {'name': 'name', 'index': 'name'},
              {'name': 'category', 'index': 'category'},
            ],
            'caption': "List of names and categories.",
            'rowNum': 40,
            'rowList': [20, 40, 60],
            }
        return dict(xmljqgrid=XmlJqGrid(globals(), db.mytable,
            jqgrid_options=jqgrid_options)())


Requirements (by default they come with the XmlJqGrid standalone app):
    xmljqgrid
        The default implementation expects to find xmljqgrid installed at
        static/xmljqgrid

    jQuery-ui
        The default implementation expects to find the following jquery_ui
        files.
        static/jqueryui/css/jquery-ui.custom.css
        static/jqueryui/js/jquery-ui.custom.js

    jquery-ui timepicker
        http://trentrichardson.com/examples/timepicker/
        static/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.css
        static/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.js
"""

import gluon.contrib.simplejson as json
from xmltodict import *
from copy import deepcopy
from lxml import etree
from exception import *
from gluon.html import DIV, SCRIPT, TABLE, URL, XML
from gluon.http import HTTP
from string import Template
import math
import logging
import re

DEFAULT = '__USE_DEFAULT_SETTING__'


class NoFilterForFieldType(Exception):
    """Exception indicating no filter is defined for field type."""
    pass


def XMLJQGRID(environment, table):
    """Convenience function so XMLJQGRID works like a native web2py html helper
    Usage in your controller:
        def foo():
            ...
            return {'bar':DIV(
                H1('blah blah'),
                XMLJQGRID(globals(), db.things),
                )}
    """
    return XmlJqGrid(environment, table)()


class Raw(object):
    "Used by JSONEncoderRaw"

    def __init__(self, payload):
        self.payload = payload

    def as_is(self):
        return self.payload


class JSONEncoderRaw(json.JSONEncoder):
    "Raw objects will be encoded as-is. So output might not be strict json."
    PLACEHOLDER_PATTERN = re.compile(r'"__RAW__-?\d+"')
    locker = {}

    def default(self, obj):
        if isinstance(obj, Raw):
            signature = '__RAW__%s' % id(obj)
            self.locker['"%s"' % signature] = obj.as_is()
            return signature
        return json.JSONEncoder.default(self, obj)

    def encode(self, o):
        result = json.JSONEncoder.encode(self, o)
        for placeholder in self.PLACEHOLDER_PATTERN.findall(result):
            result = result.replace(placeholder, self.locker.pop(placeholder))
        return result




class XmlJqGrid(object):
    """Class representing interface to xmljqgrid, jquery grid plugin."""

    default_jqgrid_options = {
        'data': {},
        'gridview': True,
        'datatype': 'xml',
        'mtype': 'GET',
        'contentType': "application/xml; charset=utf-8",
        'rowNum': 20,
        'rowList': [10, 20, 30, 50, 100, 200, 300, 500],
        'sortname': 'id',
        'sortorder': 'desc',
        'viewrecords': True,
        'height': '100%',
        'innerForm': True,
        'alertHandler': 'g_alert',
        'xmlReader': {}
        }
    default_options = default_jqgrid_options      # for backward compatibility
    default_nav_grid_options = {
        # http://www.trirand.com/jqgridwiki/doku.php?id=wiki:navigator
        'search': False,  # as of 2011-08-03 navGrid Search not implemented
        'add': False, 'edit': False, 'del': False, 'view': False,
      'refresh': False, 'alertHandler': 'g_alert'
        }
    default_nav_edit_options = {}   # e.g. {'width': 400, 'editCaption': '*'}
    default_nav_add_options = {}    # e.g. {'width': 400, 'addCaption': '+'}
    default_nav_del_options = {}    # e.g. {'width': 400, 'caption': '-'}
    default_nav_search_options = {}     # e.g. {'width': 400, 'caption': '?'}
    default_nav_view_options = {}   # e.g. {'width': 400, 'caption': '='}
    default_filter_toolbar_options = None  # None to disable, {...} to enable

    template = '''
        jQuery(document).ready(function(){
          jQuery.extend(jQuery.jgrid.edit, { // for both add and edit
            errorTextFormat: function(data){
                return JSON.parse(data.responseText);        // match cud()
            }
          });
          jQuery.extend(jQuery.jgrid.del, {
            errorTextFormat: function(data){
                return JSON.parse(data.responseText);        // match cud()
            }
          });
          jQuery("#$list_table_id").jqGrid({
            complete: function(jsondata, stat) {
                if (stat == "success") {
                    jQuery("#$list_table_id").addJSONData(
                        JSON.parse(jsondata.responseText).d);
                }
            },
            $callbacks
            $basic_options
          });
          $extra
        });'''

    response_files = []         # Can be overrided in controller

    def __init__(self,
        environment,
        rootXml = None,
        query = None,
        orderby=None,
        jqgrid_options = None,
        select_callback_url = None,
        nav_grid_options = DEFAULT,   # Use None to disable, {...} to enable
        nav_edit_options = DEFAULT,
        nav_add_options = DEFAULT,
        nav_del_options = DEFAULT,
        nav_search_options = DEFAULT,
        nav_view_options = DEFAULT,
        filter_toolbar_options = DEFAULT,  # Use None to disable, {...} to enable
        pager_div_id = None,
        list_table_id = None,
        ):
        if nav_grid_options is not DEFAULT:
          nav_grid_options.setdefault('alertHandler', 'g_alert')

        request = environment['request']
        xml = {}
        options = {}
        if rootXml is not None:
          self.rootXml = rootXml
          if query is not None:
            xml = rootXml.xpath(query)
          else:
            query = './'
            xml = rootXml.xpath(query)

          xml = deepcopy(xml[0])

          if len(xml):
            self.default_jqgrid_options['xmlReader'] = {
              'id': xml.tag + '>' + xml[0].tag + '>' + 'name',
              'repeatitems': 0,
              'root': xml.tag,
              'row': xml[0].tag,
              'page': xml.tag + '>page',
              'total': xml.tag + '>total',
              'records': xml.tag + '>records'
            }

          options = dict(self.default_jqgrid_options)
          if jqgrid_options:
              options.update(jqgrid_options)

          if len(xml):
            firstItems = xml[0]
            options.setdefault(
              'colModel', [{'name':f.tag, 'index':f.tag} for f in firstItems])
            if not 'colNames' in options:
              options['colNames'] = [item.tag for item in firstItems]
            # row id를 key값으로만 지정하기 때문에 prefix를 넣어준다.
            options.setdefault('idPrefix', xml.tag + '_')

          options.setdefault(
            'beforeSelectRow', Raw("""
                                   function(id) {
                                     $(this).editGridRow(id, %s);
                                   return true;
                                  }
                                   """ % dumps(nav_edit_options)))
          options.setdefault(
            'loadComplete', Raw("""
                                function(id) {
                                  $(this).jqGrid('editGridRow', 'new', %s);
                                }
                                """ % dumps(nav_add_options)))

        else:
          # server data가 필요하지 않을 경우
          xml = etree.Element('static')
          rootXml = xml
          options = dict(self.default_jqgrid_options)
          if jqgrid_options:
            options.update(jqgrid_options)

        self.pager_div_id = pager_div_id or ('jqgrid_pager_%s' % xml.tag)
        self.list_table_id = list_table_id or ('jqgrid_list_%s' % xml.tag)
        data_vars = {'w2p_jqgrid_action': 'data',
                'w2p_list_table_id': self.list_table_id}
        data_vars.update(request.vars)
        options.setdefault('url', URL(r=request,
                # No need for URL(..., hmac_hash=...) here,
                # because even tampered url won't get access to other table
                args=request.args, vars=data_vars))
        self.options = options
        if request.vars.get('w2p_jqgrid_action') == 'data' and \
                request.vars.get('w2p_list_table_id') == self.list_table_id:
            environment['response'].view = 'generic.xml'
            raise HTTP(200, environment['response'].render(self.data(
                    environment, xml, query=query, orderby=orderby,
                    fields=[v.get('name') for v in options['colModel']])))

        options.setdefault('editurl', URL(r=request, args=request.args,
                vars={'w2p_jqgrid_action': 'cud',
                    'w2p_list_table_id': self.list_table_id}))
        if request.vars.get('w2p_jqgrid_action') == 'cud' and \
                request.vars.get('w2p_list_table_id') == self.list_table_id:
            raise HTTP(200, self.cud(environment, rootXml, query, options))

        options.setdefault('caption', 'Data of %s' % xml.tag)
        if not 'pager' in options:
            options['pager'] = self.pager_div_id
        # setGroupHeaders method needs to be called after grid is created
        self.set_group_headers = None
        if 'setGroupHeaders' in options:
            self.set_group_headers = options['setGroupHeaders']
            del options['setGroupHeaders']
        self.jqgrid_options = options
        self.initialize_response_files(environment, self.response_files)
        self.callbacks = ''
        if select_callback_url:
            self.callbacks += '''
                onSelectRow: function(id){
                    window.location.href = '%s'.replace('{id}', id);
                },''' % select_callback_url

        self.nav_grid_options = self.default_nav_grid_options \
                if nav_grid_options == DEFAULT else nav_grid_options
        self.nav_edit_options = self.default_nav_edit_options \
                if nav_edit_options == DEFAULT else nav_edit_options
        self.nav_add_options = self.default_nav_add_options \
                if nav_add_options == DEFAULT else nav_add_options
        self.nav_del_options = self.default_nav_del_options \
                if nav_del_options == DEFAULT else nav_del_options
        self.nav_search_options = self.default_nav_search_options \
                if nav_search_options == DEFAULT else nav_search_options
        self.nav_view_options = self.default_nav_view_options \
                if nav_view_options == DEFAULT else nav_view_options
        self.filter_toolbar_options = self.default_filter_toolbar_options \
                if filter_toolbar_options == DEFAULT else \
                filter_toolbar_options

    @classmethod    # this way a XmlJqGrid instance is not required
    def initialize_response_files(cls, environment, response_files=None,
            lang='en', theme='ui-lightness'):
        """Method for preparing response.files.

        This is a class method because this will be called without a XmlJqGrid
        instance.
        Args:
            environment: dict, should be: globals()
            response_files: should be a list of url, to override the default
            lang: e.g. 'en'. Valid names are those xx in
                  static/jqgrid/js/i18n/grid.locale-xx.js
            theme: e.g. 'smoothness' or 'ui-lightness', etc.
        """
        appname = XmlJqGrid.__module__.split('.')[1]   # Auto detect this app name
        if not response_files:      # then use default location
            response_files = [
                    URL(a=appname, c='static', f=x, extension=False) for x in [
                    #'jqueryui/css/%s/jquery-ui.custom.css' % theme,
                    #'jqueryui/js/jquery-ui.custom.min.js',
                    'jqgrid/css/ui.jqgrid.css',
                    'jqgrid/js/i18n/grid.locale-%s.js' % (lang or 'en'),
                    'jqgrid/js/jquery.jqGrid.src.js',
                    #'jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.css',
                    #'jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.js',
                    ]]
        environment['response'].files.extend(response_files)
        return response_files

    def __call__(self):
        return DIV(self.script(), self.list(), self.pager(), _id = self.list_table_id + '_root')

    def column(self, name):
        """Convenience method used to return the colModel column with the
        provided name.

        Example: set the 'date' column width to 100
            jqgrid.column('date')['width'] = 100
        """
        if not self.jqgrid_options:
            return
        if not 'colModel' in self.jqgrid_options:
            return
        try:
            return [x for x in self.jqgrid_options['colModel'] \
                    if x['name'] == name][0]
        except IndexError:
            return

    @classmethod
    def makePath(cls, result, node, searchCond):
      result += '/' + node.tag
      result += '['
      i = 0
      for child in node:
        if child.tag in searchCond.keys():
          if not i:
            result += ' and '
            ++i
          result += "%s = %s" % (child.tag, searchCond[child.tag])
      result += ']'

      if not node[0]:
        return

      for child in node:
          cls.makePath(result, child, searchCond)

    @classmethod
    def data(cls, environment, xml, query=None, orderby=None, fields=None):
        """Method for accessing jqgrid row data.

        This is a class method because this will be called without a JqGrid
        instance.
        Caveat: Don't use request.args within method.
        Args:
            environment: dict, eg: globals()
            table: DAL Table instance
            query: DAL Query instance
            orderby: DAL orderby instance, if None, the orderby is determined
                by the request.vars.sidx and request.vars.sord.
            fields: list of table field names
        """
        request = environment['request']
        page = int(request.vars.page)
        pagesize = int(request.vars.rows)
        limitby = (page * pagesize - pagesize, page * pagesize)
        queries = {}
        xpath = '.'
        if not fields:
            fields = [item.name for item in cls.options['colModel']]
        for k, v in request.vars.items():
            #Only works when filter_toolbar_options != {stringResult:True, ...}
            if k in fields and v:
                queries[k] = v

        #xpath = cls.makePath(xpath, xml, queries)

        sortorder = ''
        rowcount = 20
        if orderby is None:
            if request.vars.sidx in fields:
                orderby = request.vars.sidx
            else:
                orderby = cls.orderby_for_column(xml, request.vars.sidx)
            if orderby and request.vars.sord == 'desc':
                sortorder = False
            else:
                sortorder = True
                pass
            if request.vars.rows:
                rowcount = int(request.vars.rows)

        total_records, rows = cls.data_rows(xml, request, xpath, orderby, limitby, fields, sortorder, rowcount)
        total_pages = int(math.ceil(total_records / float(pagesize)))
        etree.SubElement(rows, "total").text = str(total_pages)
        etree.SubElement(rows, "page").text = str(min(page, total_pages))
        etree.SubElement(rows, "records").text = str(total_records)

        """ generic.xml에 분기 적용 """
        return dict(_rawxmlstring = etree.tostring(rows, pretty_print = True))

    @staticmethod
    def data_rows(xml, request, xpath, orderby=None, limitby=None, fields=None, sortorder=None, rowcount=20):
        """Return data rows for the jqgrid.

        Override this method to provide custom data access (eg table joins)
        in a subclass.

        Args:
            table: gluon.dal.Table instance
            query: gluon.dal.Query instance
            orderby: list of gluon.dal.Expressions
            limitby: tuple (offset, limit)
            fields: list of field names, if None, table.fields is used.

        Return:
            list of dicts,
                eg [{'id': id1, 'cell': ['col1', 'col2', 'col3'..]},...]
        """

        if request.vars.filters and len(xml):
          tree = xml.getroottree()
          # 첫번째 item path를 가져온다
          path = tree.getpath(xml[0])
          # [1] 제거
          countReg = '\[+\d+\]+'
          path = re.sub(countReg, '', path)

          sRules = 'rules'
          sField = 'field'
          sData = 'data'
          sOption = 'op'
          result = deepcopy(path)
          filters = json.loads(request.vars.filters)
          for rule in filters[sRules]:
            fieldPath = xml.find('.//' + rule[sField])
            if fieldPath is None:
              continue
            fieldPath = tree.getpath(fieldPath)
            #field count 제거
            fieldPath = re.sub(countReg, '', fieldPath)
            fieldPath = fieldPath.replace(path, '')
            # 마지막 depth를 제거하고 option에서 추가한다.
            if fieldPath.rfind('/') != -1:
              fieldPath = fieldPath[:fieldPath.rfind('/')]
            # depth가 더 있으면 첫번째 슬러시를 제거하고 []를 추가한다.
            hasChildren = False
            if fieldPath.find('/') != -1:
              fieldPath = fieldPath[1:]
              hasChildren = True
            rField = rule[sField].encode('utf-8')
            rData = repr(rule[sData].encode('utf-8'))
            # equal
            if rule[sOption] == 'eq':
              if hasChildren:
                result += '[' + fieldPath + '[' + rField + '=' + rData + ']]'
              else:
                result += '[' + fieldPath + rField + '=' + rData + ']'
              pass
            # not equal
            elif rule[sOption] == 'ne':
              if hasChildren:
                result += '[' + fieldPath + '[' + rField + '!=' + rData + ']]'
              else:
                result += '[' + fieldPath + rField + '!=' + rData + ']'
              pass
            elif rule[sOption] == 'lt':
              pass
            elif rule[sOption] == 'le':
              pass
            elif rule[sOption] == 'gt':
              pass
            elif rule[sOption] == 'ge':
              pass
            # begins with
            elif rule[sOption] == 'bw':
              if hasChildren:
                result += '[' + fieldPath+ '[' + rField  + '[starts-with(text(),' + rData + ')]]]'
              else:
                result += '[' + fieldPath + rField + '[starts-with(text(),' + rData + ')]]'
              pass
            # does not begins with
            elif rule[sOption] == 'bn':
              if hasChildren:
                result += '[' + fieldPath+ '[' + rField  + '[not(starts-with(text(),' + rData + '))]]]'
              else:
                result += '[' + fieldPath + rField + '[not(starts-with(text(),' + rData + '))]]'
              pass
            #TODO:: in???? db에 in인거 같은데..
            # is in
            elif rule[sOption] == 'in':
              inResult = ''
              for data in rData.split():
                data = data.replace('\'', '')
                inResult += ' or ' + rField + '=' + repr(data)
              inResult = inResult[4:]
              if hasChildren:
                result += '[' + fieldPath + '[' + inResult + ']]'
              else:
                result += '[' + fieldPath + inResult + ']'
              pass
            # is not in
            elif rule[sOption] == 'ni':
              if hasChildren:
                result += '[' + fieldPath+ '[' + rField  + '[not(contains(text(),' + rData + '))]]]'
              else:
                result += '[' + fieldPath + rField + '[not(contains(text(),' + rData + '))]]'
              pass
            # ends with
            elif rule[sOption] == 'ew':
              if hasChildren:
                result += '[' + fieldPath+ '[' + rField  + '[ends-with(text(),' + rData + ')]]]'
              else:
                result += '[' + fieldPath + rField + '[ends-with(text(),' + rData + ')]]'
              pass
            # does not ends with
            elif rule[sOption] == 'en':
              if hasChildren:
                result += '[' + fieldPath+ '[' + rField  + '[not(starts-with(text(),' + rData + '))]]]'
              else:
                result += '[' + fieldPath + rField + '[not(starts-with(text(),' + rData + '))]]'
              pass
            # contains
            elif rule[sOption] == 'cn':
              if hasChildren:
                result += '[' + fieldPath+ '[' + rField  + '[contains(text(),' + rData + ')]]]'
              else:
                result += '[' + fieldPath + rField + '[contains(text(),' + rData + ')]]'
              pass
            # does not contains
            elif rule[sOption] == 'nc':
              if hasChildren:
                result += '[' + fieldPath+ '[' + rField  + '[not(contains(text(),' + rData + '))]]]'
              else:
                result += '[' + fieldPath + rField + '[not(contains(text(),' + rData + '))]]'
              pass
            # is null
            elif rule[sOption] == 'nu':
              if hasChildren:
                result += '[' + fieldPath + '[' + rField + '=\'\']]'
              else:
                result += '[' + fieldPath + rField + '=\'\']'
              pass
            # is not null
            elif rule[sOption] == 'nn':
              if hasChildren:
                result += '[' + fieldPath + '[' + rField + '!=\'\']]'
              else:
                result += '[' + fieldPath + rField + '!=\'\']'
              pass
          newNode = etree.Element(xml.tag)
          xml = xml.xpath(result)
          if len(xml):
            for node in xml:
              newNode.append(node)
          xml = newNode

          """
          newNode = etree.Element(xml.tag)
          xml = xml.xpath(result)
          if len(xml):
            newNode.append(xml)
          else:
            xml = newNode
          """

        totalcount = len(xml)

        # sort
        data = []
        for el in xml:
          key = el.findtext(orderby)
          data.append((key, el))

        data.sort(reverse = sortorder)
        xml[:] = [item[-1] for item in data]
        xml[:] = xml[limitby[0]:limitby[1]]
        return (totalcount, xml)

    @staticmethod
    def data_records(table, query):
        """Return the number of records in the data rows for the jqgrid.

        Override this method to provide a custom method for accessing the
        number of records in a subclass.

        Args:
            table: gluon.dal.Table instance
            query: gluon.dal.Query instance

        Return:
            integer, number of records.
        """
        return int(table._db(query).count())

    @staticmethod
    def filter_query_by_field_type(field, value):
        """Return a query for filtering results on field by field type.

        Args:
            field: gluon.dal.Field instance
            value: mixed, the value of the field to filter on

        Returns:
            gluon.dal.Query instance
        """
        if field.type in ('text', 'string'):
            query = field.startswith(value)
        elif field.type in ('id', 'integer', 'float', 'double',
                'date', 'datetime', 'time'):
            # intentionally not use exact matching
            # note: startswith() fails
            query = (field.like(value + '%'))
        elif field.type.startswith('decimal'):
            query = (field.like(value + '%'))
        elif field.type.startswith('list:reference'):
            query = (field.contains(value))
        elif field.type.startswith('reference'):
            query = (field == int(value))
        elif field.type == 'boolean':
            query = (field == value)
        else:
            raise NoFilterForFieldType(
                    'No filtering support for field type {t}' % (field.type))
        return query

    @staticmethod
    def filter_query(db, column, value):
        """Return a query for filtering results

        Override this method to provide custom filtering in a subclass. See
        commented sample code below.

        Args:
            db: gluon.dal.Dal instance
            column: string, jqgrid column name
            value: mixed, the value of the field to filter on

        Returns:
            gluon.dal.Query instance
        """
        query = None
        #if column == 'person' and value:
        #   query = db.person.last_name.startswith(value)
        #elif column == 'thing' and value:
        #   ...
        return query

    @staticmethod
    def orderby_for_column(table, column):
        """Return an orderby expression list for suitable for sorting a
        specific column.

        Override this method to provide custom sorting in a subclass. Columns
        with names that do not match a table field name must be handled here
        for column sorting to work. See commented code below for an example.

        Args:
            table: gluon.dal.Table instance
            column: string, jqgrid column name

        Returns:
            list of gluon.dal.Expression instances
        """
        orderby = None
        # if column == 'person':
        #     orderby = [table._db.person.last_name,
        #             table._db.person.first_name]
        # elif column == 'phone':
        #     orderby = [table._db.person.phone]
        # else:
        #     logging.warn('No sorting for column %s' % (column))
        return orderby

    @classmethod
    def cud(cls, environment, rootXml, query, options):
        "Create/update/delete callback, defined by editurl option."
        request = environment['request']
        xmlReaderId = options.get('xmlReader')['id'].split('>')
        xmlReaderId = xmlReaderId[-1]
        xmlReaderId = xmlReaderId.strip()
        curXml = rootXml.find(query)

        if request.vars.oper == 'del' and request.vars.id:
          ids = request.vars.id.split(',')
          for id in ids:
            id = repr(id)
            node = curXml.xpath('//' + xmlReaderId + '[text() =' + id + ']')
            if node is []:
              exHandler = ExceptionHandler(environment, makeHttp = True)
              exHandler.showWarning(ExceptionMsg['noId'])
            else:
              curXml.remove(node[0].getparent())

        elif request.vars.oper in ['edit', 'add'] and request.vars.id:
          if request.post_vars._addResult:
            curXml.append(etree.fromstring(request.post_vars._addResult))
          elif request.post_vars._editResult:
            for node in curXml:
              if node.find('.//' + xmlReaderId).text == request.vars.id:
                # 삭제 후 추가한다.
                curXml.remove(node)
                break
              curXml.append(etree.fromstring(request.post_vars._editResult))

          cls.cud_callback(environment, curXml)
            #form = crud.update(table,
                    #request.vars.id if request.vars.id != '_empty' else None,
                    #formname=None)      # another magic

              #if form.errors:
                  #raise HTTP(406,
                      #',<br />'.join('%s:%s' % (form.table[k].label, v)
                          #for k, v in form.errors.items())
                      #)
          #if not form or not form.errors:
              #cls.cud_callback(environment, table, form)

    @classmethod
    def cud_callback(cls, environment, xml):
        """Callback called after cud update.

        Intended to be overridden in subclass.
        """
        pass

    def list(self):
        """Return a HTML table representing jqgrid list."""
        return TABLE(_id=self.list_table_id)

    def pager(self):
        """Return a HTML div representing jqgrid pager."""
        return DIV(_id=self.pager_div_id)

    def script(self):
        """Return a HTML script representing jqgrid javascript."""
        # so user has chance for customizing, between __init__() and script()
        self.basic_options = dumps(self.jqgrid_options)[1:-1]    # Strip quotes
        self.extra = ''
        if isinstance(self.nav_grid_options, dict):
            self.extra += \
                "jQuery('#%s').jqGrid('navGrid','#%s',%s,%s,%s,%s,%s,%s);" % (
                self.list_table_id, self.pager_div_id,
                dumps(self.nav_grid_options),
                dumps(self.nav_edit_options),
                dumps(self.nav_add_options),
                dumps(self.nav_del_options),
                dumps(self.nav_search_options),
                dumps(self.nav_view_options)
                )
        if isinstance(self.filter_toolbar_options, dict):
            self.extra += "jQuery('#%s').jqGrid('filterToolbar',%s);" % (
                    self.list_table_id, dumps(self.filter_toolbar_options))
        if self.set_group_headers:
            self.extra += "jQuery('#%s').jqGrid('setGroupHeaders',%s);" % (
                    self.list_table_id, dumps(self.set_group_headers))
        return SCRIPT(Template(self.template).safe_substitute(self.__dict__))

def dumps(obj, **kwargs):
    """Serialize obj to a JSON string using JSONEncoderRaw

    By using JSONEncoderRaw, the obj can contain javascript functions.
    """
    return json.dumps(obj, cls=JSONEncoderRaw, **kwargs)

