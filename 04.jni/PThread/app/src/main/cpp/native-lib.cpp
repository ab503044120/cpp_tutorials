#include <jni.h>
#include <string>
#include <android/log.h>
#include <pthread.h>
#include <unistd.h>
#include "string.h"
#include "signal.h"
#include <malloc.h>

void *log(void *pvoid);
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
  __android_log_print(ANDROID_LOG_ERROR, "pthread", "主进程:%d", getpid());
  int pid = fork();
  __android_log_print(ANDROID_LOG_ERROR, "pthread", "创建的进程:%d", pid);
  __android_log_print(ANDROID_LOG_ERROR, "pthread", "当前进程:%d", getpid());
  if (pid == 0) {
    pthread_t pthread;
    int thread_id = pthread_create(&pthread, NULL, log, NULL);
    __android_log_print(ANDROID_LOG_ERROR, "pthread", "在进程:%d,创建线程:%d成功", getpid(), thread_id);
  }
//  FILE *file = fopen(strPath, "a+");
//  char *str = (char *) malloc(50 * sizeof(char));
//  sprintf(str, "当前进程:%d--父进程:%d\n\r\0", getpid(), getppid());
//  fwrite(str, sizeof(char), strlen(str), file);
//  fclose(file);
//  free(str);
}

void *log(void *pvoid) {
  FILE *file = fopen(strPath, "a+");
  while (1) {
    char *str = (char *) malloc(50 * sizeof(char));
    sprintf(str, "当前进程:%d--父进程:%d\n\r", getpid(), getppid());
    fwrite(str, sizeof(char), strlen(str), file);
    fflush(file);
    __android_log_print(ANDROID_LOG_ERROR, "pthread", "当前进程:%d--父进程:%d", getpid(), getppid());
    free(str);
    sleep(1);
  }
  return (void *) 100;
}