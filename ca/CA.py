#!/dev/env python
#-*- coding: utf-8 -*-

#import pexpect
import os
import subprocess

class CA:
  master_key_password     = 'test'
  master_key_file         = 'ca'
  default_private_key_dir = 'private'
  default_certs_dir       = 'certs'
  default_ca_csr_name     = 'ca'
  default_key_extension   = '.key'
  default_csr_extension   = '.csr'
  default_crt_extension   = '.crt'
  default_key_size        = '1024'
  default_country_name    = 'KR'
  default_city            = 'Seoul'
  default_company         = 'ca'
  default_section         = 'dev'
  default_common_name     = 'CA'
  default_email           = 'heheahn@githup.com'
  default_config_file     = 'openssl.cnf'

  def __init__(self, home = None,
               key_size = None, country = None, city = None,
               company = None, section = None, common_name = None,
               email = None, config_file = None,
               csr_extension = None, crt_extension = None,
               key_extension = None):

    self.home          = home          or os.getcwd()
    self.key_size      = key_size      or self.default_key_size
    self.country       = country       or self.default_country_name
    self.city          = city          or self.default_city
    self.company       = company       or self.default_company
    self.section       = section       or self.default_section
    self.common_name   = common_name   or self.default_common_name
    self.email         = email         or self.default_email
    self.config_file   = config_file   or self.default_config_file
    self.csr_extension = csr_extension or self.default_csr_extension
    self.crt_extension = crt_extension or self.default_crt_extension
    self.key_extension = key_extension or self.default_key_extension

    self.master_key_file         = self.home + '/' + self.master_key_file
    self.default_private_key_dir = self.home + '/' + self.default_private_key_dir
    self.default_certs_dir       = self.home + '/' + self.default_certs_dir
    self.default_ca_csr_name     = self.home + '/' + self.default_ca_csr_name
    self.default_config_file     = self.home + '/' + self.default_config_file


    # ca private key
    if not os.path.exists(self.master_key_file + self.key_extension):
      self.makePrivateKey(self.master_key_file,
                          self.master_key_password,
                          True,
                          self.key_size)
    # ca certificate sign request
    if not os.path.exists(self.default_ca_csr_name + self.csr_extension):
      self.makeCsr(self.default_ca_csr_name,
                   self.master_key_file + self.key_extension,
                   self.master_key_password)
    # ca certificate
    if not os.path.exists(self.default_ca_csr_name + self.crt_extension):
      self.makeCertificate(self.default_ca_csr_name,
                           self.default_ca_csr_name,
                           self.master_key_password, '-selfsign')

  def openProcess(self, cmd):
    return subprocess.Popen(cmd, stdin = subprocess.PIPE)

  def makeCertificate(self, csrName, crtName, password, extra_option = None):
    cmd = ['openssl', 'ca', '-in', csrName + self.csr_extension,
           '-out', crtName + self.crt_extension,
           '-keyfile', self.master_key_file + self.key_extension,
           '-passin', 'stdin',
           '-config', self.config_file]

    if extra_option is not None:
      cmd.append(extra_option)

    fd = self.openProcess(cmd)
    fd.stdin.write(password + '\n')
    # sign?
    fd.stdin.write('y\n')
    # commit?
    fd.stdin.write('y\n')
    fd.stdin.write('\n')
    fd.communicate()

  def makeCsr(self, name, privateKey, password):
    fd = self.openProcess(['openssl', 'req', '-new',
                           '-key', privateKey,
                           '-passin', 'stdin',
                           '-out', name + self.csr_extension,
                           '-config', self.config_file])
    fd.stdin.write(password + '\n')
    #Country Name
    fd.stdin.write(self.country + '\n')
    #State or Province Name
    fd.stdin.write(self.city + '\n')
    #Locality Name
    fd.stdin.write(self.city + '\n')
    #Organization Name
    fd.stdin.write(self.company + '\n')
    #Organizational Unit Name
    fd.stdin.write(self.section + '\n')
    #Common Name
    fd.stdin.write(self.common_name + '\n')
    #Email Address
    fd.stdin.write(self.email + '\n')
    #A challenge password
    fd.stdin.write('\n')
    # An optional company name
    fd.stdin.write('\n')
    fd.communicate()

  def makePrivateKey(self, keyName, password, encliption = True, keySize = None):
    if encliption is True:
      encliption = '-des3'
    else:
      encliption = ''
    if keySize is not None:
      keySize = str(keySize)
    fd = self.openProcess(['openssl', 'genrsa',
                           '-out', keyName + self.key_extension,
                           '-passout', 'stdin',
                           keySize or self.key_size, encliption])
    fd.communicate(password)
    fd.stdin.close()

  def clear(self):
    import glob

    os.chdir(self.home)
    for index in glob.glob('index.*'):
      os.unlink(index)
    for cert in glob.glob('newcerts/*'):
      os.unlink(cert)
    for key in glob.glob('private/*'):
      os.unlink(key)

    subprocess.call(['touch', 'index.txt'])
    subprocess.call(['rm', 'serial', 'serial.old'])
    subprocess.call(['rm', 'ca.crt', 'ca.csr'])

    fd = open('./serial', 'w')
    fd.write('00\n')
    fd.close()


if __name__ == '__main__':

  ca = CA()
  #ca.makePrivateKey('test', 'test')
  #ca.makePrivateKey('test', 'test', False, 2048)
  #ca.makeCsr('test2', 'test.key', 'test')
  #ca.makeCertificate('test2', 'test3', 'test')
