package com.chobo.io;
import java.io.*;

public class StandardIOEx3 {
  public static void main(String[] args) {
    try {
      FileOutputStream fs = new FileOutputStream("hello.text");
      PrintStream ps = new PrintStream(fs);
      System.setOut(ps);
    }
    catch (FileNotFoundException e) {
    }

    System.out.println("Hello by System.out");
    System.err.println("Hello by System.err");
  }
}
