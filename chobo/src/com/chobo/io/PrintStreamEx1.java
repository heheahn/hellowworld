package com.chobo.io;
import java.util.Date;

public class PrintStreamEx1 {
  public static void main(String[] args) {
    int i = 65;
    float f = 1234.5678f;

    Date d = new Date();

    System.out.printf("문자 %c의 코드는 %d\n", i, i);
    System.out.printf("%d는 8진수로 %o, 16진수로 %x\n", i, i, i);
    System.out.printf("%d%d%d\n", 100, 90, 80);
    System.out.println();
    System.out.printf("%s%-5s%5s\n", "123", "123", "123");
    System.out.printf("%-8.1f%8.1f %e\n", f,f,f);
    System.out.println();
    System.out.printf("오늘은 %tY년 %tm월 %td일 입니다.\n", d,d,d,d);
    System.out.printf("지금은 %tH시 %tM분 %sS초 입니다.\n", d,d,d,d);
    System.out.printf("지금은 %1$tH %1$tM분 %1$tS초 입니다.\n", d);
  }
}
