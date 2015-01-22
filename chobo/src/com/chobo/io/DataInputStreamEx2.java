package com.chobo.io;
import java.io.*;

public class DataInputStreamEx2 {
  public static void main(String[] args) {
    int sum = 0;
    int score = 0;
    DataInputStream dis = null;

    try {
      FileInputStream fis = new FileInputStream("score");
      dis = new DataInputStream(fis);

      while ( true ) {
        score = dis.readInt();
        sum += score;
      }
    }
    catch ( EOFException e ) {
      System.out.println("총합은 " + sum + "입니다");
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
    finally {
      if ( dis != null ) {
        try {
          dis.close();
        }
        catch ( IOException e ) {
          e.printStackTrace();
        }
      }
    }
  }
}
