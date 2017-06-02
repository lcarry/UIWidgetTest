package com.example.lcarry.threadtest.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ${lcarry} on 2017/5/5.
 */

public class MyIntentService extends IntentService {

    private final static String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");//调用父类带参数的构造函数
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打印当前线程ID
        Log.w(TAG,"Thread id is " + Thread.currentThread().getId());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(TAG,"onDestroy executed");
    }
}
