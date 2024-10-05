
#include "busybox.h"
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <pthread.h>
#include <errno.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <strings.h>
#define UNIXSTR_PATH "/data/data/com.pangbai.dowork/files/tmp/shellRec"
off_t get_file_size(int fd);
int sockfd=0;
struct sockaddr_un servaddr;

int dowork_main(int argc, char *argv[])MAIN_EXTERNALLY_VISIBLE;

int dowork_main(int argc, char *argv[]) {

    return 0;
}


static void printError(char *name)
{
    printf("%s: %s\n", name, strerror(errno));
    exit(0);
}

int msize=0;
int progress=0;
long filesize=0;
pthread_mutex_t lock;
bool islock=false;
int progressRead( ){
    
// if(islock)pthread_mutex_lock(&lock);;
int count=(msize*100 / filesize);
       if (progress!=count){
     progress=count;
 //    printf("total = %d\n", progress);
   if(sockfd!=0){
     sendinfor(itoa(progress));
	 }
	 }

//if(islock) pthread_mutex_unlock(&lock);

}


static long getFileSize(FILE *fp)
{
    long fileSize = 0;
    fseek(fp, 0, SEEK_END);
    fileSize = ftell(fp);
    fseek(fp, 0, SEEK_SET);
    return fileSize;
}



off_t get_file_size(int fd)
{
	int ret;
	struct stat file_stat;


	ret = fstat(fd, &file_stat);	// 获取文件状态
	if (ret == -1) {
		printf("Get file stat failed");
		
		return -1;
	}
	
	return file_stat.st_size;
}


//初始化通道
int initSocket(){
sockfd = socket(AF_LOCAL, SOCK_STREAM, 0);
bzero(&servaddr, sizeof(servaddr));
servaddr.sun_family = AF_LOCAL;
strcpy(servaddr.sun_path, UNIXSTR_PATH);
int res=connect(sockfd, (struct sockaddr *)&servaddr, sizeof(servaddr));
        if(res!=0){
	printf("shellSend链接错误\n");
        return -1;
		}
return 0;
}

//发送信息
int sendinfor(char *infor){
int res=write(sockfd, infor, strlen(infor));
return res;
}

