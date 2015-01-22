package com.chobo.io;
import java.io.*;

public class PipedReaderWriter {
  public static void main(String[] args) {
    InputThread it = new InputThread("InputThread");
    OutputThread ot = new OutputThread("OutputThread");

    it.connect(ot.getOutput());

    it.start();
    ot.start();
  }
}

class InputThread extends Thread {
  PipedReader pr = new PipedReader();
  StringWriter sw = new StringWriter();

  InputThread(String name) {
    super(name);
  }

  public void run() {
    int data = 0;

    try {
      while ( (data = pr.read()) != -1 ) {
        sw.write((char)data);
      }
      System.out.println(getName() + " recieved: " + sw.toString());
      sw.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public PipedReader getInput() {
    return pr;
  }

  public void connect(PipedWriter p) {
    try {
      pr.connect(p);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}

class OutputThread extends Thread {
  PipedWriter pw = new PipedWriter();

  OutputThread(String name) {
    super(name);
  }

  public void run() {
    String msg = "하이 헬로우!!!";
    System.out.println(getName() + " sent: " +msg);

    try {
      pw.write(msg);
      pw.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

  }

  public PipedWriter getOutput() {
    return pw;
  }
}
