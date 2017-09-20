//
// Created by Administrator on 2017/9/20.
//
#include <jni.h>
#include <iostream>

#define JNIREG_CLASS "org/huihui/jnidemo/JniMap"//指定要注册的类

//jstring  native_hello(JNIEnv *env, jclass clazz);
JNIEXPORT jstring JNICALL native_hello(JNIEnv *env, jclass clazz);
/**
* Table of methods associated with a single class.
*/
static JNINativeMethod gMethods[] = {
        {"hello", "()Ljava/lang/String;", (void *) native_hello},//绑定
};

/*
* Register several native methods for one class.
*/
static int registerNativeMethods(JNIEnv *env, const char *className,
                                 JNINativeMethod *gMethods, int numMethods) {
    jclass clazz;
    clazz = env->FindClass(className);
    if (clazz == NULL) {
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}


/*
* Register native methods for all classes we know about.
*/
static int registerNatives(JNIEnv *env) {
    if (!registerNativeMethods(env, JNIREG_CLASS, gMethods,
                               sizeof(gMethods) / sizeof(gMethods[0])))
        return JNI_FALSE;

    return JNI_TRUE;
}

/*
* Set some test stuff up.
*
* Returns the JNI version on success, -1 on failure.
*/
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    jint result = -1;

    if (vm->GetEnv( (void **) &env, JNI_VERSION_1_4) != JNI_OK) {
        return -1;
    }
    if (!registerNatives(env)) {//注册
        return -1;
    }
    /* success -- return valid version number */
    result = JNI_VERSION_1_4;

    return result;
}

JNIEXPORT jstring JNICALL native_hello(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("hello world returned.");
}