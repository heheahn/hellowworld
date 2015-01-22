package com.chobo.io;
import java.io.*;

public class FileConversion {
  public static void main(String[] args) {
    try {
      FileReader fr = new FileReader(args[0]);
      FileWriter fw = new FileWriter(args[1]);

      int data = 0;

      while ( (data = fr.read()) != -1 ) {
        if ( data != '\n' && data != ' ' ) {
          fw.write((char)data);
        }
      }
      fr.close();
      fw.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
