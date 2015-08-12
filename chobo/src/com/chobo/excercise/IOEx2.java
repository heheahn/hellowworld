package com.chobo.excercise;
import java.io.*;
import java.util.Arrays;

public class IOEx2 {
  public static void main(String[] args) {
    byte[] src = {1,2,3,4,5,6,7,8,9,10};
    byte[] dst = null;

    ByteArrayInputStream is = new ByteArrayInputStream(src);
    ByteArrayOutputStream os = new ByteArrayOutputStream(0);

    byte[] tmp = new byte[10];

    is.read(tmp, 0, 10);
    os.write(tmp, 5, 5);

    dst = os.toByteArray();

    System.out.println("src: " + Arrays.toString(src));
    System.out.println("tmp: " + Arrays.toString(tmp));
    System.out.println("dst: " + Arrays.toString(dst));

  }
}
