package org.huihui.airplay;

public class AirPlay {
    public native int init(int port, String hardwareAddr);

    private native boolean start();

    public native boolean stop();

    private native boolean unInit();

    public void onMsg(byte[] bytes, long length) {

    }
}
