package com.chobo.io;
import java.io.*;
import java.util.ArrayList;

class FileEx3 {
  static int fileCnt = 0;
  static int dirCnt = 0;
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

    printFileList(f);
  }

  public static void printFileList(File dir) {
    System.out.println(dir.getAbsolutePath() + " 디렉토리");

    File[] list = dir.listFiles();

    ArrayList subDir = new ArrayList();

    for ( int ii = 0; ii < list.length; ii++ ) {
      File curFile = list[ii];
      String name = curFile.getName();

      if ( curFile.isDirectory() ) {
        name = "[" + name + "]";
        subDir.add(ii+"");
      }
      System.out.println(name);
    }
    int dirNum = subDir.size();
    int fileCnt = list.length - dirNum;

    fileCnt += fileCnt;
    dirCnt += dirNum;

    System.out.println(dirNum + "개 디렉토리, " + fileCnt + "개 파일");
    System.out.println();

    for ( int ii = 0; ii < subDir.size(); ii++ ) {
      int index = Integer.parseInt((String)subDir.get(ii));
      printFileList(list[index]);
    }
  }
}
