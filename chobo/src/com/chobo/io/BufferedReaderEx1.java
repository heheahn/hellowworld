package com.chobo.io;
import java.io.*;
import java.util.Properties;

public class BufferedReaderEx1 {
  public static void main(String[] args) {
    try {
      Properties p = System.getProperties();
      System.out.println(p.get("sun.jnu.encoding"));
      FileReader fr = new FileReader(args[0]);
      BufferedReader br = new BufferedReader(fr);

      String line = "";

      for ( int ii = 1; (line = br.readLine()) != null; ii++ ) {
        if ( line.indexOf(';') != -1 ) {
          System.out.println(ii + ": " + line);
        }
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
