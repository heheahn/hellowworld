package com.chobo.io;
import java.io.*;
import java.util.ArrayList;

public class SerialEx1 {
  public static void main(String[] args) {
    String fileName = "UserInfo.ser";

    try {
      FileOutputStream fos = new FileOutputStream(fileName);
      BufferedOutputStream bos = new BufferedOutputStream(fos);

      ObjectOutputStream oos = new ObjectOutputStream(bos);

      UserInfo2 u1 = new UserInfo2("tom", "1234", 15);
      UserInfo2 u2 = new UserInfo2("jim", "4567", 20);

      ArrayList<UserInfo2> list = new ArrayList<UserInfo2>();
      list.add(u1);
      list.add(u2);

      oos.writeObject(u1);
      oos.writeObject(u2);
      oos.writeObject(list);

      oos.close();
    }
    catch (IOException e) {
    }
  }
}
