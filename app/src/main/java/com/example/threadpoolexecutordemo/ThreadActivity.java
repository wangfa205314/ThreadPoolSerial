package com.example.threadpoolexecutordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    //线程池
    TextView cachethreadPool;
    TextView fixedthreadPool;
    TextView scheduledthreadPool;
    TextView singlethreadExecutor;
    ExecutorService cachedThreadPool;
    ExecutorService fixedThreadPool;
    ScheduledExecutorService scheduledThreadPool;
    ExecutorService singleThreadExecutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        initView();
        iniThreadPool();
    }

    private void initView() {
        cachethreadPool = findViewById(R.id.cacheThreadPool);
        fixedthreadPool = findViewById(R.id.fixedThreadPool);
        scheduledthreadPool = findViewById(R.id.scheduledThreadPool);
        singlethreadExecutor = findViewById(R.id.singleThreadExecutor);

        cachethreadPool.setOnClickListener(this);
        fixedthreadPool.setOnClickListener(this);
        scheduledthreadPool.setOnClickListener(this);
        singlethreadExecutor.setOnClickListener(this);
    }

    public void iniThreadPool(){
        cachedThreadPool = Executors.newCachedThreadPool();
        fixedThreadPool = Executors.newFixedThreadPool(5);
        scheduledThreadPool = Executors.newScheduledThreadPool(5);
        singleThreadExecutor = Executors.newSingleThreadExecutor();
    }

    int index = 0;
    boolean flag = true;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cacheThreadPool:
                cachedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        while (flag) {
                            index +=1;
                            try {
                                Thread.sleep(1000);
                                Log.d(">>cacheThreadPool=",index+"");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                break;

            case R.id.fixedThreadPool:
                fixedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        while (flag){
                            try {
                                index += 1;
                                Thread.sleep(1000);
                                Log.d(">>fixedThreadPool=",index+"");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                break;

            case R.id.scheduledThreadPool:
                scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        index += 1;
                        Log.d(">>scheduledThreadPool=",index+"\nthread="+Thread.currentThread().getId());
                    }
                }, 0, 100, TimeUnit.MILLISECONDS);
                break;

            case R.id.singleThreadExecutor:
                singleThreadExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        while (flag) {
                            try {
                                System.out.println(index);
                                Thread.sleep(2000);
                                index += 1;
                                Log.d(">>singleThreadExecutor=",index+"");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
        if (!cachedThreadPool.isShutdown()){
            cachedThreadPool.shutdown();
        }
        if (!fixedThreadPool.isShutdown()){
            fixedThreadPool.shutdown();
        }
        if (!scheduledThreadPool.isShutdown()){
            scheduledThreadPool.shutdown();
        }
        if (!singleThreadExecutor.isShutdown()){
            singleThreadExecutor.shutdown();
        }
    }
}
