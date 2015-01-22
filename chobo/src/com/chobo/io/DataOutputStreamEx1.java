package com.chobo.io;
import java.io.*;

public class DataOutputStreamEx1 {
  public static void main(String[] args) {
    try {
      FileOutputStream fos = new FileOutputStream("fos");
      DataOutputStream dos = new DataOutputStream(fos);

      dos.writeInt(2);
      dos.writeFloat(2.1f);
      dos.writeBoolean(true);

      dos.close();
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
  }
}
