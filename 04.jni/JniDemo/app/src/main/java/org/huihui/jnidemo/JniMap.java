package org.huihui.jnidemo;

/**
 * Created by Administrator on 2017/9/20.
 */

public class JniMap {
    static {
        System.loadLibrary("jni_map");
    }

    public native static String hello();
}
