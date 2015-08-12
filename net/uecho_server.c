#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define BUF_SIZE 30
void error_handing(char * message);

int main(int argc, const char *argv[])
{
  int serv_sock;
  char message[BUF_SIZE];
  int str_len;
  socklen_t clnt_addr_sz;
  struct sockaddr_in serv_addr, clnt_addr;

  if (argc != 2) {
    printf("Usage: %s <port>\n", argv[0]);
    exit(0);
  }

  serv_sock = socket(PF_INET, SOCK_DGRAM, 0);
  if (serv_sock == -1) {
    error_handing("UDP socket creation error");
  }

  memset(&serv_addr, 0, sizeof(serv_addr));
  serv_addr.sin_family = AF_INET;
  serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
  serv_addr.sin_port = htons(atoi(argv[1]));

  if (bind(serv_sock, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) == -1) {
    error_handing("bind() error!");
  }

  while (1) {
    clnt_addr_sz = sizeof(clnt_addr);
    str_len = recvfrom(serv_sock, message, BUF_SIZE, 0, (struct sockaddr*)&clnt_addr, &clnt_addr_sz);
    printf("msg: %s", message);
    sendto(serv_sock, message, str_len, 0, (struct sockaddr*)&clnt_addr, clnt_addr_sz);
  }
  close(serv_sock);

  return 0;
}

void error_handing(char * message) {
  fputs(message, stdout);
  fputc('\n', stderr);
  exit(1);
}
