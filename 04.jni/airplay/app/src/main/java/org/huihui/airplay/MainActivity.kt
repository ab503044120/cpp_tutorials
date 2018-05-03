package org.huihui.airplay

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MDNS.register(6000, "huihui")
        Thread(Runnable {
            try {
                val datagramSocket = DatagramSocket(80)
                val address = InetAddress.getByName("192.168.199.222")
                val p = DatagramPacket(byteArrayOf(1, 2, 3), 3, address, 80)
                while (true) {
                    datagramSocket.send(p)
                    Thread.sleep(1000)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
        val airPlay = AirPlay()
        airPlay.init(80, "111")
        airPlay.start()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
}
