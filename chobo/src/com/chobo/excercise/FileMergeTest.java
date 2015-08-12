package com.chobo.excercise;
import java.io.*;

public class FileMergeTest {
  public static void main(String[] args) {
    if ( args.length == 0 ) {
      System.out.println("USAGE: java FileMergeTest finename1 filename2 ...");
      System.exit(0);
    }

    FileReader fr = null;
    BufferedReader br = null;
    try {
      FileWriter fw = new FileWriter("merged");
      BufferedWriter bw = new BufferedWriter(fw);

      File current = null;
      int data = 0;
      for ( int ii = 0; ii < args.length; ii++ ) {

        current = new File(args[ii]);
        if ( current.exists() ) {
          fr = new FileReader(current);
          br = new BufferedReader(fr);
          while ( (data = br.read()) != -1 ) {
            bw.write(data);
          }
          br.close();
        }
      }
      bw.close();
    }
    catch (IOException e) {
    }

  }
}
