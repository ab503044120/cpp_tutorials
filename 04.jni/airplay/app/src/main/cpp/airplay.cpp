#include <jni.h>

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


  env->ReleaseStringUTFChars(hardwareAddr_, hardwareAddr);
}