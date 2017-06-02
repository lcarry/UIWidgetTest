package com.example.lcarry.threadtest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lcarry.threadtest.service.MyIntentService;
import com.example.lcarry.threadtest.service.MyService;
import com.example.lcarry.uiwidgettest.R;

public class ThreadTestActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "ThreadTestActivity";

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder)service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);

        Button btnStart = (Button) findViewById(R.id.button_service_start);
        btnStart.setOnClickListener(this);

        Button btnStop = (Button) findViewById(R.id.button_service_stop);
        btnStop.setOnClickListener(this);

        Button btnBind = (Button) findViewById(R.id.button_service_bind);
        btnBind.setOnClickListener(this);

        Button btnUnbind = (Button) findViewById(R.id.button_service_unbind);
        btnUnbind.setOnClickListener(this);

        Button btnStartIntentService = (Button) findViewById(R.id.button_start_intent_service);
        btnStartIntentService.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_service_start:
            {
                //启动服务
                Intent intentStart = new Intent(this, MyService.class);
                startService(intentStart);

            }
                break;
            case R.id.button_service_stop:
            {
                //停止服务
                Intent intentStop = new Intent(this, MyService.class);
                stopService(intentStop);
            }
            break;
            case R.id.button_service_bind:
            {
                //绑定服务
                Intent intentBind = new Intent(this, MyService.class);
                bindService(intentBind,connection, BIND_AUTO_CREATE);
            }
            break;
            case R.id.button_service_unbind:
            {
                //取消绑定服务
                unbindService(connection);
            }
            break;
            case R.id.button_start_intent_service:
            {
                //启动intent服务
                //打印当前线程ID
                Log.w(TAG,"Thread id is " + Thread.currentThread().getId());

                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
            }
            break;


            default:
                break;
        }
    }
}
