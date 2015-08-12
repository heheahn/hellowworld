package com.chobo.io;
import java.io.*;
import java.util.ArrayList;

public class SerialEx2 {
  public static void main(String[] args) throws Exception {
    String fileName = "UserInfo.ser";

    try {
      FileInputStream fos = new FileInputStream(fileName);
      BufferedInputStream bos = new BufferedInputStream(fos);

      ObjectInputStream oos = new ObjectInputStream(bos);

      UserInfo2 u1 = (UserInfo2)oos.readObject();
      UserInfo2 u2 = (UserInfo2)oos.readObject();
      ArrayList list = (ArrayList)oos.readObject();

      oos.close();

      System.out.println(u1);
      System.out.println(u2);
      System.out.println(list);
    }
    catch (IOException e) {
    }
  }
}
