package com.example.threadpoolexecutordemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

public class SerialActivity extends AppCompatActivity implements View.OnClickListener {

    //单串口  ComA.sendHex("afff0202df");
    TextView openSerial;
    TextView closeSerial;
    SerialPort serialPort;
    InputStream inputStream;
    OutputStream outputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial);
        initView();
        initData();
        initSerial();
    }

    private void initView() {
        openSerial = findViewById(R.id.openSerial);
        closeSerial = findViewById(R.id.closeSerial);
        openSerial.setOnClickListener(this);
        closeSerial.setOnClickListener(this);
    }

    private void initData() {
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
        switch (v.getId()) {
            case R.id.openSerial:
                try {
                    outputStream.write(Utils.HexToByteArr("AFFF0101DF"));//打开串口
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.closeSerial:
                try {
                    outputStream.write(Utils.HexToByteArr("AFFF0202DF"));//关闭串口
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }



}
