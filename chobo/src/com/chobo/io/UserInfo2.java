package com.chobo.io;
import java.io.*;

class SuperUserInfo {
  String name;
  String passwd;

  SuperUserInfo() {
    this("Unkown", "1111");
  }

  SuperUserInfo (String name, String passwd) {
    this.name = name;
    this.passwd = passwd;
  }

  public String toString() {
    return "[" + name + "," + passwd + "]";
  }

}

class UserInfo2 extends SuperUserInfo implements java.io.Serializable {
  int age = 0;

  UserInfo2() {
    this("Unkown", "1111", 0);
  }

  UserInfo2(String name, String passwd, int age) {
    super(name, passwd);
    this.age = age;
  }

  public String toString() {
    return "[" + name + "," + passwd + "," + age + "]";
  }

  private void writeObject(ObjectOutputStream os) throws IOException {
    os.writeUTF(name);
    os.writeUTF(passwd);
    os.defaultWriteObject();
  }

  private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
    name = is.readUTF();
    passwd = is.readUTF();
    is.defaultReadObject();
  }

}
