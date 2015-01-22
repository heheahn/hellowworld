package com.chobo.io;
import java.io.*;

public class BufferdOutputStreamEx1 {
  public static void main(String[] args) {
    try {
      FileOutputStream fos = new FileOutputStream("abc");
      BufferedOutputStream bos = new BufferedOutputStream(fos, 5);

      for ( int ii = '1'; ii < '9'; ii++ ) {
        bos.write(ii);
      }

      bos.close();
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
  }
}
