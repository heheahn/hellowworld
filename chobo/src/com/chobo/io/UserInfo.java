package com.chobo.io;

class UserInfo implements java.io.Serializable {
  String name;
  String passwd;
  int age = 0;

  UserInfo() {
    this("Unkown", "1111", 0);
  }

  UserInfo (String name, String passwd, int age) {
    this.name = name;
    this.passwd = passwd;
    this.age = age;
  }

  public String toString() {
    return "[" + name + "," + passwd + "," + age + "]";
  }

}
