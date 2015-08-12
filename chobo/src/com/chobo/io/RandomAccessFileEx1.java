package com.chobo.io;
import java.io.*;

public class RandomAccessFileEx1 {
  public static void main(String[] args) {
    try {
      RandomAccessFile raf = new RandomAccessFile("randomf", "rw");
      System.out.println("point: " + raf.getFilePointer());
      raf.writeInt(100);
      System.out.println("point: " + raf.getFilePointer());
      raf.writeLong(100L);
      System.out.println("point: " + raf.getFilePointer());
    }
    catch (IOException e) {
    }
  }
}
