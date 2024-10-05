#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <strings.h>
#define UNIXSTR_PATH "/data/data/com.pangbai.dowork/files/tmp/shellRec"
#define LISTENQ 5
#define BUFFER_SIZE 256

int main(int argc,char* argv[])
{
	//int cnt = sizeof(**argv) / sizeof(argv[0]);
	if(argc==1){
	printf("shellSend无数据输入\n");
        return -1;
	}
	int sockfd;
	struct sockaddr_un servaddr;

	sockfd = socket(AF_LOCAL, SOCK_STREAM, 0);
	
	bzero(&servaddr, sizeof(servaddr));
	servaddr.sun_family = AF_LOCAL;
	strcpy(servaddr.sun_path, UNIXSTR_PATH);

	int res=connect(sockfd, (struct sockaddr *)&servaddr, sizeof(servaddr));
        if(res!=0){
	printf("shellSend链接错误\n");
        return -1;}

        if(write(sockfd, argv[1], strlen(argv[1]))==-1){

	printf("shellSend发送错误\n");
	return -1;
	}

	close(sockfd);
	
	return 0;
}

