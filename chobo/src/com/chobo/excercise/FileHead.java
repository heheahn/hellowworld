package com.chobo.excercise;
import java.io.*;

public class FileHead {
  public static void main(String[] args) {
    if ( args.length < 2 ) {
      System.out.println("USAGE: java FileHead filename 10");
      System.exit(0);
    }

    String fileName = args[0];
    String data = "";

    int cnt = Integer.parseInt(args[1]);

    try {
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);

      for ( int ii = 0; (data = br.readLine()) != null; ii++ ) {
        if ( ii >= cnt ) {
          break;
        }
        System.out.println( (ii + 1) + ": " + data);
      }
      br.close();
    }
    catch (IOException e) {
    }
  }
}
