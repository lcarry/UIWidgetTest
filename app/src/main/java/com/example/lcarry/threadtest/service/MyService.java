package com.example.lcarry.threadtest.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.lcarry.threadtest.ThreadTestActivity;
import com.example.lcarry.uiwidgettest.R;

public class MyService extends Service {

    private final static String TAG = "MyService";

    private DownloadBinder downloadBinder = new DownloadBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //服务创建时调用
        Log.w(TAG,"onCreate executed");

        //前台服务
        Intent intent = new Intent(this, ThreadTestActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        //创建通知
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is a Content Title ")
                .setContentText("This is a Content Text ")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1,notification);



    }

    @Override
    public int onStartCommand(Intent intent, /*@IntDef(value = {Service.START_FLAG_REDELIVERY, Service.START_FLAG_RETRY}, flag = true)*/
    int flags, int startId) {

        //服务启动的时候调用
        Log.w(TAG,"onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //服务销毁的时候调用
        Log.w(TAG,"onDestroy executed");
        super.onDestroy();
    }


    public  class DownloadBinder extends Binder {

        public void startDownload(){
            Log.w(TAG,"startDownload executed");
        }

        public int getProgress(){
            Log.w(TAG,"getProgress executed");
            return 0;
        }
    }

}
