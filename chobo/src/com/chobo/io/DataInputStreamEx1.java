package com.chobo.io;
import java.io.*;

public class DataInputStreamEx1 {
  public static void main(String[] args) {
    FileInputStream fis = null;
    DataInputStream dis = null;

    try {
      fis = new FileInputStream("fos");
      dis = new DataInputStream(fis);

      System.out.println(dis.readInt());
      System.out.println(dis.readFloat());
      System.out.println(dis.readBoolean());

      dis.close();
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
  }
}

