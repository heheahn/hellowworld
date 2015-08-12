package com.chobo.io;
import java.io.*;

public class FileSplit {
  public static void main(String[] args) throws Exception {
    if ( args.length < 2) {
      System.out.println("USAGE: java FileSplit filename SIZE_KB");
      System.exit(0);
    }

    final int VOLUME = Integer.parseInt(args[1]) * 1000;

    String fileName = args[0];

    FileInputStream fis = new FileInputStream(fileName);
    BufferedInputStream bis = new BufferedInputStream(fis);

    FileOutputStream fos = null;
    BufferedOutputStream bos = null;

    int data = 0;
    int cnt = 0;
    int number = 0;
    try {
      while ((data = bis.read()) != -1) {
        if ( cnt % VOLUME == 0 ) {
          if ( cnt != 0 ) {
            bos.close();
          }
          fos = new FileOutputStream(fileName + "_" + ++number);
          bos = new BufferedOutputStream(fos);
        }
        bos.write(data);
        cnt++;
      }
      bis.close();
      bos.close();
    }
    catch (IOException e ) {
      e.printStackTrace();
    }
  }
}
