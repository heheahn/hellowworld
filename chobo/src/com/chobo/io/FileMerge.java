package com.chobo.io;
import java.io.*;

public class FileMerge {
  public static void main(String[] args) throws Exception {
    if ( args.length  != 1 ) {
      System.out.println("USAGE: java FileMerge filename");
      System.exit(0);
    }

    String fileName = args[0];
    try {
      System.out.println(System.getProperty("java.io.tmpdir"));
      File tmpFile = File.createTempFile("merge", ".tmp");
      tmpFile.deleteOnExit();

      FileOutputStream fos = new FileOutputStream(tmpFile);
      BufferedOutputStream bos = new BufferedOutputStream(fos);

      FileInputStream fis = null;
      BufferedInputStream bis = null;

      int cnt = 1;
      int data = 0;

      File f = new File(fileName + "_" + cnt);
      while ( f.exists() ) {
        f.setReadOnly();
        fis = new FileInputStream(f);
        bis = new BufferedInputStream(fis);

        while ( (data = bis.read()) != -1 ) {
          bos.write(data);
        }
        bis.close();
        f = new File(fileName + "_" + ++cnt);
      }
      bos.close();

      File oldFile = new File(fileName);
      if ( oldFile.exists() ) {
        oldFile.delete();
      }
      tmpFile.renameTo(oldFile);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
