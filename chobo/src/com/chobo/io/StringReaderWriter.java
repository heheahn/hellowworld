package com.chobo.io;
import java.io.*;

public class StringReaderWriter {
  public static void main(String[] args) {
    String msg = "가나다라!!!!";
    StringReader sr = new StringReader(msg);
    StringWriter sw = new StringWriter();

    int data = 0;

    try {
      while ( (data = sr.read()) != -1 ) {
        System.out.println((char)data);
        sw.write(data);
      }
      sr.close();
      sw.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("string: " + msg);
    System.out.println("writer: " + sw.toString());
    System.out.println("writer buffer: " + sw.getBuffer().toString());

  }
}
