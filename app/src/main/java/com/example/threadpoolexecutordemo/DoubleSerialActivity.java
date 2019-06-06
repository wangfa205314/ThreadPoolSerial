package com.example.threadpoolexecutordemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

import static com.example.threadpoolexecutordemo.Utils.HexToByteArr;

public class DoubleSerialActivity extends AppCompatActivity implements View.OnClickListener {

    //串口
    SerialPort mSerialPort;
    InputStream mInputStream = null;
    StringBuilder sb;
    Thread receiveThread;
    byte[] messageBytes;
    int index = 0;
    OutputStream outputStream = null;
    String message = null;

    TextView openSerial1;
    TextView openSerial2;
    TextView closeSerial1;
    TextView closeSerial2;

    Handler handler2 = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
//                if (!TextUtils.isEmpty(message) && message.length() == 8 ) {
//                    Toast.makeText(DoubleSerialActivity.this, "刷卡成功", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(DoubleSerialActivity.this, "刷卡失敗，請重新刷卡", Toast.LENGTH_SHORT).show();
//                }


            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_double);

        initView();
        initSerial();
        receiveThread();
    }
    public void initView(){
        openSerial1 = findViewById(R.id.openSerial1);
        openSerial2 = findViewById(R.id.openSerial2);
        closeSerial1 = findViewById(R.id.closeSerial1);
        closeSerial2 = findViewById(R.id.closeSerial2);
        openSerial1.setOnClickListener(this);
        openSerial2.setOnClickListener(this);
        closeSerial1.setOnClickListener(this);
        closeSerial2.setOnClickListener(this);
    }

    public void initSerial() {
        try {
            mSerialPort = new SerialPort(new File("/dev/" + "ttyS0"), 9600, 0);

            mInputStream = mSerialPort.getInputStream();
            outputStream = mSerialPort.getOutputStream();

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveThread() {
        sb = new StringBuilder();
        receiveThread = new Thread() {
            @Override
            public void run() {
                while (true) {

                }
            }
        };
        receiveThread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.openSerial1://打开串口1
                try {
                    outputStream.write(HexToByteArr("AFFF0101DF"));//继电器1 打开
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.openSerial2://打开串口2
                try {
                    outputStream.write(HexToByteArr("AFFF0201DF"));//继电器1 打开
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.closeSerial1://关闭串口1
                try {
                    outputStream.write(HexToByteArr("AFFF0102DF"));//继电器1 打开
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.closeSerial2://关闭串口2
                try {
                    outputStream.write(HexToByteArr("AFFF0202DF"));//继电器1 打开
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}