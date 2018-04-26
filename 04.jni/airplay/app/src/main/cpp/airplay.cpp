#include <jni.h>
#include <pthread.h>
#include <android/log.h>

void *threadFun(void *) {
  __android_log_print(ANDROID_LOG_ERROR,"haha","nihao");
//  return (void*) 1;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_org_huihui_airplay_AirPlay_start(JNIEnv *env, jobject instance) {

}
extern "C"
JNIEXPORT jboolean JNICALL
Java_org_huihui_airplay_AirPlay_stop(JNIEnv *env, jobject instance) {

}
extern "C"
JNIEXPORT jboolean JNICALL
Java_org_huihui_airplay_AirPlay_unInit(JNIEnv *env, jobject instance) {

}
extern "C"
JNIEXPORT jint JNICALL
Java_org_huihui_airplay_AirPlay_init(JNIEnv *env,
                                     jobject instance,
                                     jint port,
                                     jstring hardwareAddr_) {
  const char *hardwareAddr = env->GetStringUTFChars(hardwareAddr_, 0);
  pthread_t thread;

  // Create a new thread
  int result = pthread_create(&thread, NULL, threadFun, NULL);
  env->ReleaseStringUTFChars(hardwareAddr_, hardwareAddr);
}

