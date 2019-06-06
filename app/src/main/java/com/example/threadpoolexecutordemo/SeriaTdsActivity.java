package com.example.threadpoolexecutordemo;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

public class SeriaTdsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView serial_tds;
    TextView show;
    SerialPort serialPort;
    InputStream inputStream;
    OutputStream outputStream;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1000:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seria_tds);
        initView();
        initSerial();
        receiveData();
    }

    private void initView() {
        serial_tds = findViewById(R.id.serial_tds);
        show = findViewById(R.id.show);
        serial_tds.setOnClickListener(this);
    }

    private void initSerial() {
        try {
            serialPort = new SerialPort(new File("/dev/ttyS0"),9600,0);
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.serial_tds:
                try {
                    outputStream.write(Utils.HexToByteArr("FDFDFD"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    boolean flag = true;
    int index = 0;
    private void receiveData() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                while (flag){
                    try {
                        sleep(1000);
                        index = index + 1;
                        Log.d("-------------index",index+"");

                        byte[] buffer = new byte[1024];
//                        int size = inputStream.read(buffer);
//                        Log.d("-------------size",size+"");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null){
            handler.removeCallbacksAndMessages(null);
        }
        flag = false;
    }
}
