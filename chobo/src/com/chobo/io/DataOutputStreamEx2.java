package com.chobo.io;
import java.io.*;
import java.util.Arrays;

public class DataOutputStreamEx2 {
  public static void main(String[] args) {
    ByteArrayOutputStream bos = null;
    DataOutputStream dos = null;

    byte[] result = null;

    try {
      bos = new ByteArrayOutputStream();
      dos = new DataOutputStream(bos);

      dos.writeInt(2);
      dos.writeFloat(20.0f);
      dos.writeBoolean(true);

      result = bos.toByteArray();

      String[] hex = new String[result.length];

      for ( int ii = 0; ii < result.length; ii++ ) {
        if ( result[ii] < 0 ) {
          hex[ii] = Integer.toHexString(result[ii] + 256);
        }
        else {
          hex[ii] = Integer.toHexString(result[ii]);
        }
      }

      System.out.println("int: " + Arrays.toString(result));
      System.out.println("hex: " + Arrays.toString(hex));

      dos.close();
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }

  }
}
