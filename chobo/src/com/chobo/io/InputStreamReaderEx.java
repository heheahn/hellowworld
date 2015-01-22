package com.chobo.io;
import java.io.*;

public class InputStreamReaderEx {
  public static void main(String[] args) {
    try {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);

      String line = "";
      System.out.print("encoding: " + isr.getEncoding());
      do {
        System.out.println("If end, Type q!");
        line = br.readLine();
        System.out.println("typed: " + line);
      }
      while (!line.equalsIgnoreCase("q"));

      br.close();
      System.out.println("end");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
