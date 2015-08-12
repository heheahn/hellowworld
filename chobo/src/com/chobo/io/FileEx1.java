package com.chobo.io;
import java.io.*;

public class FileEx1 {
  public static void main(String[] args) {
    File f = new File(args[0]);
    String fName = f.getName();
    int pos = fName.lastIndexOf(".");

    System.out.println("no path: " + fName);
    System.out.println("no extension: " + fName.substring(0, pos));
    System.out.println("extension: " + fName.substring(pos + 1));
    System.out.println("path + name: " + f.getPath());
    System.out.println("abs path: " + f.getAbsolutePath());
    System.out.println("parent: " + f.getParent());
    System.out.println();
    System.out.println("pathSeperator: " + File.pathSeparator);
    System.out.println("pathSeperatorChar: " + File.pathSeparatorChar);
    System.out.println();
    System.out.println("seperator: " + File.separator);
    System.out.println("seperatorChar: " + File.separatorChar);
    System.out.println("user.dir: " + System.getProperty("user.dir"));
    System.out.println("sun.boot.class.path: " + System.getProperty("sun.boot.class.path"));
  }
}
