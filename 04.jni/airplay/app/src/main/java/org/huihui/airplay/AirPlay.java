package org.huihui.airplay;

public class AirPlay {
    static {
        System.loadLibrary("native-lib");
    }
    public native int init(int port, String hardwareAddr);

    public native boolean start();

    public native boolean stop();

    public native boolean unInit();

    public void onMsg(byte[] bytes, long length) {

    }
}
