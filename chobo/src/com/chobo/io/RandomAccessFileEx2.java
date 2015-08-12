package com.chobo.io;
import java.io.*;

public class RandomAccessFileEx2 {
  public static void main(String[] args) {
    int[] scores = { 1,2,3,4,5,6,7,8,9};
    try {
      RandomAccessFile raf = new RandomAccessFile("score", "rw");

      for ( int ii = 0; ii < scores.length; ii++ ) {
        raf.writeInt(scores[ii]);
      }

      raf.seek(0);
      while (true) {
        System.out.println(raf.readInt());
      }

    }
    catch (EOFException e) {
    }
    catch (IOException e) {
    }
  }
}
