package com.wibell.scorpio.wibell.ui;

import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wibell.scorpio.wibell.R;
import com.wibell.scorpio.wibell.utils.AudioRecordUtils;

import java.net.DatagramSocket;

public class ImActivity extends AppCompatActivity {
    private TextView cell;
    private ImageButton banged;

    private byte[] buf;
    private int port = 8090;
    private String ip, receiveInfo;
    private static Boolean listenStatus = false;
    private DatagramSocket socket;

    private Vibrator vibrator;

    private AudioRecordUtils audioRecordUtils;

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

        audioRecordUtils = new AudioRecordUtils();
        audioRecordUtils.FileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        audioRecordUtils.FileName += "/AudioRecord.3gp";

        audioRecordUtils.startRecord();

        Toast.makeText(ImActivity.this, ip, Toast.LENGTH_SHORT).show();

        banged = (ImageButton) findViewById(R.id.imageButton);
        banged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecordUtils.stopRecord();
                ImActivity.this.finish();
            }
        });
    }

    private void stopCellTextView() {
        cell.setText("通话中..");
    }


}
