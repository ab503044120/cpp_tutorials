#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring
JNICALL
Java_org_huihui_airplay_MainActivity_stringFromJNI(
    JNIEnv *env,
    jobject /* this */) {
  std::string hello = "Hello from C++";
  return env->NewStringUTF(hello.c_str());
}