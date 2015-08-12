package com.chobo.excercise;
import java.io.*;
import java.util.Arrays;

public class IOEx4 {
  public static void main(String[] args) {
    byte[] src = {0,1,2,3,4,5,6,7,8,9};
    byte[] dst = null;

    ByteArrayInputStream is = new ByteArrayInputStream(src);
    ByteArrayOutputStream os = new ByteArrayOutputStream();

    byte[] tmp = new byte[4];

    try {
      while ( is.available() > 0 ) {
        int len = is.read(tmp);
        os.write(tmp, 0, len);
      }
    }
    catch (IOException e) {
    }

    dst = os.toByteArray();

    System.out.println("src " + Arrays.toString(src));
    System.out.println("tmp " + Arrays.toString(tmp));
    System.out.println("dst " + Arrays.toString(dst));
  }
}
