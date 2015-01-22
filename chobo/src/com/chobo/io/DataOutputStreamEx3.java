package com.chobo.io;
import java.io.*;

public class DataOutputStreamEx3 {
  public static void main(String[] args) {
    int[] score = {50, 60, 20, 90};

    try {
      FileOutputStream fos = new FileOutputStream("score");
      DataOutputStream dos = new DataOutputStream(fos);

      for ( int ii = 0; ii < score.length; ii++ ) {
        dos.writeInt(score[ii]);
      }

      dos.close();
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
  }
}
