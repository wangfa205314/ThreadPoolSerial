package com.example.threadpoolexecutordemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 线程池demo
 */
public class MainActivity extends AppCompatActivity {

    private TextView thread;
    private TextView doubleserial;
    private TextView serial;
    private TextView seriaTDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread = findViewById(R.id.thread);
        doubleserial = findViewById(R.id.doubleserial);
        serial = findViewById(R.id.serial);
        seriaTDS = findViewById(R.id.seriaTDS);


        thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ThreadActivity.class));
            }
        });

        doubleserial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DoubleSerialActivity.class));
            }
        });
        serial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SerialActivity.class));
            }
        });
        seriaTDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SeriaTdsActivity.class));
            }
        });
    }

}
