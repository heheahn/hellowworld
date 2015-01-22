package com.chobo.io;
import java.io.*;

public class FileReaderEx1 {
  public static void main(String[] args) {
    try {
      String f = "hi.txt";
      FileInputStream fis = new FileInputStream(f);
      FileReader fr = new FileReader(f);

      int data = 0;

      while ( (data = fis.read()) != -1 ) {
        System.out.print((char)data);
      }
      System.out.println();
      fis.close();

      while ( (data = fr.read()) != -1 ) {
        System.out.print((char)data);
      }
      System.out.println();
      fr.close();
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
  }
}
