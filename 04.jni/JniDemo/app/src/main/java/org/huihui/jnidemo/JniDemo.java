package org.huihui.jnidemo;

/**
 * Created by Administrator on 2017/9/20.
 */

public class JniDemo {
    static {
        System.loadLibrary("native-lib");
    }

    int a;
    String b;
    int[] c = {1,9,3,5,567,3242,44,2};

    public native String getString();

    public static native String getStaticString();

    public native void sort(int[] arr);

    public native int jniCallMethod();

    public static native String jniCallStaticMethod();

    public int getA() {
        return a;
    }

    public static String getJniGetStatic() {
        return "123";
    }
}
