package com.chobo.io;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileEx4 {
  public static void main(String[] args) {
    String curDir = System.getProperty("user.dir");

    File f = new File(curDir);
    File[] lists = f.listFiles();

    for ( int ii = 0; ii < lists.length; ii++ ) {
      File curFile = lists[ii];
      String fName = curFile.getName();
      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mma");
      String attr = "";
      String size = "";

      if ( curFile.isDirectory() ) {
        attr = "DIR";
      }
      else {
        size = curFile.length() + "";
        attr = curFile.canRead() ? "R" : " ";
        attr += curFile.canWrite() ? "W" : " ";
        attr += curFile.isHidden() ? "H" : " ";
      }
      System.out.printf("%s %3s %6s %s\n",
          sd.format(new Date(curFile.lastModified())), attr, size, fName);
    }
  }
}
