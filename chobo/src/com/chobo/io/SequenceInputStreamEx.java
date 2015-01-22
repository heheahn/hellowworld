package com.chobo.io;
import java.io.*;
import java.util.*;

public class SequenceInputStreamEx {
  public static void main(String[] args) {
    byte[] arr1 = {1,2,3};
    byte[] arr2 = {4,5,6};
    byte[] arr3 = {7,8,9};
    byte[] out = null;

    Vector v = new Vector();

    v.add(new ByteArrayInputStream(arr1));
    v.add(new ByteArrayInputStream(arr2));
    v.add(new ByteArrayInputStream(arr3));

    SequenceInputStream sis = new SequenceInputStream(v.elements());
    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    int data = 0;
    try {
      while ( (data = sis.read()) != -1 ) {
        bos.write(data);
      }
    }
    catch ( IOException e ) {
    }
    out = bos.toByteArray();

    System.out.println("arr1: " + Arrays.toString(arr1));
    System.out.println("arr2: " + Arrays.toString(arr2));
    System.out.println("arr3: " + Arrays.toString(arr3));
    System.out.println("out: " + Arrays.toString(out));

  }
}
