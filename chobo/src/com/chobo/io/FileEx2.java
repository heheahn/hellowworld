package com.chobo.io;
import java.io.*;

public class FileEx2 {
  public static void main(String[] args) {
    if ( args.length != 1 ) {
      System.out.println("USAGE: java FileEx2 DIRECTORY");
      System.exit(0);
    }

    File f = new File(args[0]);

    if ( f.isFile() || !f.isDirectory() ) {
      System.out.println("유효하지 않은 디렉토리입니다.");
      System.exit(0);
    }

    File[] files = f.listFiles();

    for ( int ii = 0; ii < files.length; ii++ ) {
      File curFile = files[ii];
      String name = files[ii].getName();
      System.out.print(curFile.isDirectory() ? "[" + name + "]" : name);
    }
  }
}
