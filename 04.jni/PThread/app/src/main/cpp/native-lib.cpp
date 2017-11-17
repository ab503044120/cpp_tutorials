#include <jni.h>
#include <string>
#include <android/log.h>
#include <pthread.h>
#include <unistd.h>
#include "string.h"

void log();

extern "C"
JNIEXPORT jstring
JNICALL
Java_org_huihui_pthread_MainActivity_stringFromJNI(
    JNIEnv *env,
    jobject /* this */) {
  std::string hello = "Hello";
  return env->NewStringUTF(hello.c_str());
}

static const char *strPath;

extern "C"
JNIEXPORT void JNICALL
Java_org_huihui_pthread_MainActivity_startThread(JNIEnv *env, jobject instance, jstring path) {
  strPath = env->GetStringUTFChars(path, NULL);
  __android_log_print(ANDROID_LOG_ERROR, "native", "主进程:%d", getpid());
  int pid = fork();
  __android_log_print(ANDROID_LOG_ERROR, "native", "创建的进程:%d", pid);
  __android_log_print(ANDROID_LOG_ERROR, "native", "当前进程:%d", getpid());
  if (pid == 0) {
    pthread_t pthread;
    pthread_create(&pthread, NULL, (void *(*)(void *)) log, NULL);
    __android_log_print(ANDROID_LOG_ERROR, "native", "在进程:%d,创建线程成功", getpid());
  }
}

void log() {
  FILE* file = fopen(strPath,"rw");
  while (true) {
    char* str;
    sprintf(str,"当前进程:%d--父进程:%d\n\r", getpid(), getppid());
    fwrite(str, sizeof(char),strlen(str),file);
    __android_log_print(ANDROID_LOG_ERROR, "native", "当前进程:%d--父进程:%d", getpid(), getppid());
    usleep(1000000);
  }
}