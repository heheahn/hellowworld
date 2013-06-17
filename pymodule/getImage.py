#!/bin/env python
# -*- coding: utf-8 -*-

import ftplib
ftp = ftplib.FTP('192.168.20.71', 'heheahn', '111235')
ftp.cwd('/home/heheahn/ngsf/src/images')
ftp.retrbinary('RETR bzImage', open('bzImage', 'wb').write)
ftp.quit()
