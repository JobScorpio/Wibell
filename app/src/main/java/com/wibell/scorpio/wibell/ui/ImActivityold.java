package com.wibell.scorpio.wibell.ui;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wibell.scorpio.wibell.R;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ImActivityold extends AppCompatActivity {
    private TextView cell;
    private ImageButton banged;

    private byte[] buf;
    private int port = 8090;
    private String ip, receiveInfo;
    private static Boolean listenStatus = false;
    private DatagramSocket socket;

    private Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);

        Bundle bundle = this.getIntent().getExtras();
        String ip = bundle.getString("ip");
        String alias = bundle.getString("alias");

        cell = (TextView) findViewById(R.id.textView);
        cell.setText(alias + "呼叫中..");

        this.ip = ip;
        new udpSendThread().start();
//        this.listenStatus = true;
        new udpReceiveThread().start();
        Toast.makeText(ImActivityold.this, ip, Toast.LENGTH_SHORT).show();

        banged = (ImageButton) findViewById(R.id.imageButton);
        banged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImActivityold.this.finish();
            }
        });
    }

    private void stopCellTextView() {
        cell.setText("通话中..");
    }

    // UDP数据发送线程
    public class udpSendThread extends Thread {
        @Override
        public void run() {
            Log.d("UDP数据发送线程", "启动");
            try {
                buf = "cell".getBytes();
//                if (listenStatus == false) {
                    socket = new DatagramSocket(port);
//                }
                InetAddress serverAddr = InetAddress.getByName(ip);
                DatagramPacket outPacket = new DatagramPacket(buf, buf.length, serverAddr, port);
                socket.send(outPacket);
                socket.close();
                Log.d("UDP数据发送线程", "完成");
            } catch (Exception e) {
                Log.d("UDP数据发送线程", e.getMessage().toString());
//                Toast.makeText(ImActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //UDP数据接收线程
    public class udpReceiveThread extends Thread {
        @Override
        public void run() {
            Log.d("UDP数据接收线程", "启动");
            try {
                socket = new DatagramSocket(port);
                listenStatus = true;
                while (listenStatus) {
                    byte[] inBuf = new byte[1024];
                    DatagramPacket inPacket = new DatagramPacket(inBuf, inBuf.length);
                    socket.receive(inPacket);
                    receiveInfo = new String(inPacket.getData(), inPacket.getOffset(), inPacket.getLength());
                    Log.i("UDP接受数据：", receiveInfo);
                    if (receiveInfo.length() > 1) {
                        stopCellTextView();
                    }
                }
            } catch (Exception e) {
//                Toast.makeText(ImActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
