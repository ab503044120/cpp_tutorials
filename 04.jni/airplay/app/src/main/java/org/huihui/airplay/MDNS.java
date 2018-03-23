package org.huihui.airplay;

import android.util.Log;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/**
 * Created by huihui on 2018/3/20.
 */

public class MDNS {
    /**
     * The AirTunes/RAOP service type
     */
    static final String AIR_TUNES_SERVICE_TYPE = "_raop._tcp.local.";

    /**
     * The AirTunes/RAOP M-DNS service properties (TXT record)
     */
    static final Map<String, String> AIRTUNES_SERVICE_PROPERTIES = NetworkUtils.map(
            "txtvers", "1",
            "tp", "UDP",
            "ch", "2",
            "ss", "16",
            "sr", "44100",
            "pw", "false",
            "sm", "false",
            "sv", "false",
            "ek", "1",
            "et", "0,1",
            "cn", "0,1",
            "vn", "3");

    public static final String TAG = "MDNS";
    private static List<JmDNS> mJmDNS = new ArrayList<>();

    public static void register(int port, String hostName) {
        try {
            List<NetworkInterface> workInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (final NetworkInterface networkInterface : workInterfaces) {
                //如果网络设备几口是 回送接口 & 点对点接口 & 没有运行 & 虚拟端口，则跳过执行
                if (networkInterface.isLoopback() || networkInterface.isPointToPoint() || !networkInterface.isUp()
                        || networkInterface.isVirtual()) {
                    continue;
                }
                // 不支持组播  跳过
                if (!networkInterface.supportsMulticast()) {
                    continue;
                }

                for (final InetAddress address : Collections.list(networkInterface.getInetAddresses())) {
                    //端口是是ipv4 或者  ipv6 端口
                    if (address instanceof Inet4Address || address instanceof Inet6Address) {
                        String addressStr = address + "";
                        if (!addressStr.contains("192.")) {
                            continue;
                        }
                        try {
                            byte[] hardwareAddress = networkInterface.getHardwareAddress();
                            String strAddress = toHexString(hardwareAddress);
                            final JmDNS jmDNS = JmDNS.create(address, hostName);
                            mJmDNS.add(jmDNS);
                            //构建AirTunes/RAOP （远程音频传输协议）服务
                            final ServiceInfo airTunesServiceInfo = ServiceInfo.create(
                                    AIR_TUNES_SERVICE_TYPE,
                                    strAddress + "@" + hostName,
                                    port,
                                    0,
                                    0,
                                    AIRTUNES_SERVICE_PROPERTIES
                            );
                            jmDNS.registerService(airTunesServiceInfo);
                            Log.d(TAG, "Success to publish service on " + address + ", port: " + port);
                            return;
                        } catch (final Throwable e) {
                            Log.e(TAG, "Failed to publish service on " + address, e);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String toHexString(final byte[] bytes) {
        final StringBuilder s = new StringBuilder();
        for(final byte b: bytes) {
            final String h = Integer.toHexString(0x100 | b);
            s.append(h.substring(h.length() - 2, h.length()).toUpperCase());
        }
        return s.toString();
    }
    public static void unregister() {
        for (JmDNS jmDN : mJmDNS) {
            jmDN.unregisterAllServices();
        }
    }
}
