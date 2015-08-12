#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
void error_handling(char *mesage);

int main(int argc, const char *argv[])
{
  char * addr = "127.232.123.79";
  struct sockaddr_in addr_inet;

  if (inet_aton(addr, &addr_inet.sin_addr)) {
    printf("Network ordered interger addr: %#x \n", addr_inet.sin_addr.s_addr);
  }
  else {
    printf("Conversion error! \n");
  }

  return 0;
}

void error_handling(char *message) {
  fputs(message, stderr);
  fputc('\n', stderr);
  exit(1);
}
