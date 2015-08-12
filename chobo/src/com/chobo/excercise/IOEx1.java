package com.chobo.excercise;
import java.io.*;
import java.util.Arrays;

public class IOEx1 {
  public static void main(String[] args) {
    byte[] src = {1,2,3,4,5,6,7,8,9,10};
    byte[] dst = null;

    ByteArrayInputStream is = new ByteArrayInputStream(src);
    ByteArrayOutputStream os = new ByteArrayOutputStream();

    int data = 0;
    while ( (data = is.read()) != -1 ) {
      os.write(data);
    }

    dst = os.toByteArray();

    System.out.println("src: " + Arrays.toString(src));
    System.out.println("dst: " + Arrays.toString(dst));
  }
}


