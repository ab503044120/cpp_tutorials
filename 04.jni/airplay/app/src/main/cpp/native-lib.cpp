#include <jni.h>
#include <string>
#include <pthread.h>
#include <android/log.h>
extern "C"
JNIEXPORT jstring
JNICALL
Java_org_huihui_airplay_MainActivity_stringFromJNI(
    JNIEnv *env,
    jobject /* this */) {
  std::string hello = "Hello from C++";
  pthread_create()
  return env->NewStringUTF(hello.c_str());
}
