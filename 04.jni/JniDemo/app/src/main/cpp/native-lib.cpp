#include <jni.h>
#include <string>
#include <stdlib.h>

extern "C" {
//jni静态方法
JNIEXPORT jstring JNICALL
Java_org_huihui_jnidemo_JniDemo_getStaticString(JNIEnv *env, jclass type) {
    return env->NewStringUTF("hello java");
}
//jni成员方法
JNIEXPORT jstring JNICALL
Java_org_huihui_jnidemo_JniDemo_getString(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("hello java");
}
//调用java静态方法
JNIEXPORT jstring JNICALL
Java_org_huihui_jnidemo_JniDemo_jniCallStaticMethod(JNIEnv *env, jclass type) {
    jmethodID jmethodID = env->GetStaticMethodID(type, "getJniGetStatic", "()Ljava/lang/String;");
    jstring jstr = (jstring) env->CallStaticObjectMethod(type, jmethodID);
    return jstr;
}
//调用java方法
JNIEXPORT jint JNICALL
Java_org_huihui_jnidemo_JniDemo_jniCallMethod(JNIEnv *env, jobject instance) {
    jclass jc = env->GetObjectClass(instance);
    jmethodID id = env->GetMethodID(jc, "getA", "()I");
    return env->CallIntMethod(instance, id);

}

//jni数组排序
int compare(const void *left, const void *right) {
    return *(jint *) left - *(jint *) right;
}
//jni传参进来排序
JNIEXPORT void JNICALL
Java_org_huihui_jnidemo_JniDemo_sort(JNIEnv *env, jobject instance, jintArray arr_) {
    jint *arr = env->GetIntArrayElements(arr_, NULL);

    qsort(arr, env->GetArrayLength(arr_), sizeof(arr), compare);

    env->ReleaseIntArrayElements(arr_, arr, 0);
}
}
