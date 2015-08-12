package com.chobo.excercise;
import java.io.*;

public class DirectoryInfoTest {
  static int totalFiles = 0;
  static int totalDirs = 0;
  static int totalBytes = 0;

  public static void main(String[] args) {
    if ( args.length != 1 ) {
      System.out.println("USAGE: java DirectoryInfoTest directory");
      System.exit(0);
    }

    File f = new File(args[0]);

    if ( !f.exists() || !f.isDirectory() ) {
      System.out.println("디렉토리가 아닙니다.");
      System.exit(0);
    }

    countFiles(f);

    System.out.println();
    System.out.println("파일 갯수: " + totalFiles);
    System.out.println("디렉토리 갯수: " + totalDirs);
    System.out.println("bytes: " + totalBytes);

  }

  public static void countFiles(File dir) {
    File[] list = dir.listFiles();

    for ( int ii = 0; ii < list.length; ii++ ) {
      File current = list[ii];

      if ( current.isFile() ) {
        totalFiles++;
        totalBytes += current.length();
      }
      else {
        totalDirs++;
        countFiles(current);
      }
    }
  }

}
