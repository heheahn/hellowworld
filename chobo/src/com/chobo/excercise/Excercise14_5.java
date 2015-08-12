package com.chobo.excercise;
import java.io.*;

public class Excercise14_5 {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Excercise14_5 srcfile dstfile");
			System.exit(0);
		}
		String src = args[0];
		String dst = args[1];
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(src));
			HtmlTagFilterWriter output = new HtmlTagFilterWriter(new FileWriter(dst));
			
			int ch = 0;
			while ((ch=input.read()) != -1) {
				output.write(ch);
			}
			
			input.close();
			output.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}

class HtmlTagFilterWriter extends FilterWriter {
	StringWriter tmp = new StringWriter();
	boolean inTag = false;
	HtmlTagFilterWriter (Writer out) {
		super(out);
	}
	
	public void write(int c) throws IOException {
		if (c == '<') {
			inTag = true;
		} else if (c == '>' && inTag){
			inTag = false;
			tmp = new StringWriter();
			return;
		}
		if (inTag) {
			tmp.write(c);
		} else {
			out.write(c);
		}
	}
	
	public void close() throws IOException {
		tmp.toString();
		super.close();
	}
}
