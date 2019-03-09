package com.example.hll_mall.android_handler_learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setThreadLocalValueInMultipThread();
    }


    /**
     * 检测不同线程下读取主线程这个值的结果
     * 发现每个线程都不一样
     * 这正是ThreadLocal的特性
     */
    private void setThreadLocalValueInMultipThread() {
        mBooleanThreadLocal.set(true);
        Log.d(TAG, "[MainThread]mBooleanThreadLocal=" + mBooleanThreadLocal.get());
        new Thread("Thread#1") {
            @Override
            public void run() {
                mBooleanThreadLocal.set(false);
                Log.d(TAG, "[Thread#1]mBooleanThreadLocal=" + mBooleanThreadLocal.get());
            }
        }.start();

        new Thread("Thread#2") {
            @Override
            public void run() {
                Log.d(TAG, "[Thread#2]mBooleanThreadLocal=" + mBooleanThreadLocal.get());
            }
        }.start();
    }


}
