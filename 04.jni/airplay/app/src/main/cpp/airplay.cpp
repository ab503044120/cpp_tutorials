#include <jni.h>
#include <pthread.h>
#include <android/log.h>
// va_list, vsnprintf
#include <stdarg.h>

// errno
#include <errno.h>

// strerror_r, memset
#include <string.h>

// socket, bind, getsockname, listen, accept, recv, send, connect
#include <sys/types.h>
#include <sys/socket.h>

// sockaddr_un
#include <sys/un.h>

// htons, sockaddr_in
#include <netinet/in.h>

// inet_ntop
#include <arpa/inet.h>

// close, unlink
#include <unistd.h>

// offsetof
#include <stddef.h>


void log(const char *str) {
  __android_log_print(ANDROID_LOG_ERROR, "airplay", "%s", str);
}

void *SocketServerThread(void *) {
  int tcpSocket = socket(PF_INET, SOCK_STREAM, 0);
  struct sockaddr_in address;

  // Address to bind socket
  memset(&address, 0, sizeof(address));
  address.sin_family = PF_INET;

  // Bind to all addresses
  address.sin_addr.s_addr = htonl(INADDR_ANY);

  // Convert port to network byte order
  address.sin_port = htons(8000);

  if (-1 == bind(tcpSocket, (struct sockaddr *) &address, sizeof(address))) {
    log("erro");
  }

  log("sucess");

  if (-1 == listen(tcpSocket, 4)) {
    // Throw an exception with error number
    log("erro Listen");
  }
  log("sucess Listen");
  socklen_t addressLength = sizeof(address);

  // Blocks and waits for an incoming client connection
  // and accepts it

  int clientSocket = accept(tcpSocket,
                            (struct sockaddr *) &address,
                            &addressLength);

  // If client socket is not valid
  if (-1 == clientSocket) {
    // Throw an exception with error number
    log("erro accept");
  }
  log("sucess accept");
  char buffer[10];
  ssize_t recvSize;
  ssize_t sentSize;
  while (true) {
    if (recv(clientSocket, buffer, 10 - 1, 0) > 0) {
      log(buffer);
    } else {
      log("disconnect");
      break;
    }
  }

  return (void *) 1;
}

void *socketClientThread(void *) {
  int tcpSocket = socket(PF_INET, SOCK_STREAM, 0);
  struct sockaddr_in address;

  // Address to bind socket
  memset(&address, 0, sizeof(address));
  address.sin_family = PF_INET;

  // Bind to all addresses
  address.sin_addr.s_addr = htonl(INADDR_ANY);

  // Convert port to network byte order
  address.sin_port = htons(8000);

  if (-1 == bind(tcpSocket, (struct sockaddr *) &address, sizeof(address))) {
    log("erro");
  }

  struct sockaddr_in their_addr;

  // Address to bind socket
  memset(&their_addr, 0, sizeof(their_addr));
  their_addr.sin_family = PF_INET;

  // Bind to all addresses
  inet_pton(AF_INET, "192.168.137.1", &their_addr.sin_addr);

  // Convert port to network byte order
  their_addr.sin_port = htons(9000);

  while (connect(tcpSocket, (struct sockaddr *) &their_addr, sizeof(their_addr)) == -1) {
    log("connecting...");
    sleep(1);

  }
  log("connected");
  char buffer[10];
  ssize_t recvSize;
  while (1) {
    ssize_t ret = read(tcpSocket, buffer, 10);
    if (ret == -1) {
      break;
    }
    if (ret != 0) {
      log(buffer);
    }
    memset(buffer, 0, sizeof(char) * 10);
  }
  close(tcpSocket);
  return (void *) 1;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_org_huihui_airplay_AirPlay_start(JNIEnv *env, jobject instance) {
  pthread_t thread;
  // Create a new thread
  int result = pthread_create(&thread, NULL, socketClientThread, NULL);
  return true;
}
extern "C"
JNIEXPORT jboolean JNICALL
Java_org_huihui_airplay_AirPlay_stop(JNIEnv *env, jobject instance) {
  return true;

}
extern "C"
JNIEXPORT jboolean JNICALL
Java_org_huihui_airplay_AirPlay_unInit(JNIEnv *env, jobject instance) {
  return true;
}
extern "C"
JNIEXPORT jint JNICALL
Java_org_huihui_airplay_AirPlay_init(JNIEnv *env,
                                     jobject instance,
                                     jint port,
                                     jstring hardwareAddr_) {
  log("init");
  return 1;
}

