package com.example.lcarry.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lcarry.uiwidgettest.R;

import java.io.File;

public class UINotificationActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uinotification);

        Button btnSendNotice = (Button)findViewById(R.id.button_send_notice);
        btnSendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_send_notice:
            {
                acionClickSendNotify(v);
            }break;
            default:
                break;
        }
    }


    private void acionClickSendNotify(View v){

        //推送通知管理类
        NotificationManager manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

//                Notification notification = new NotificationCompat.Builder(this)
//                        .setContentTitle("This is a Content Title")
//                        .setContentText("This is a Content Text")
//                        .setWhen(System.currentTimeMillis())
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
//                                R.mipmap.ic_launcher))
//                        .build();

//        for (int i = 0; i < 10; i++) {
        int i = 0;

            //设置通知点开intent
            Intent intent = new Intent(this,NotificationActivity.class);
            intent.putExtra("param1","This is a Content " + String.valueOf(i));
            intent.putExtra("param2",i);

//            String strIntent = intent.getStringExtra("param1");
//            int nIntent = intent.getIntExtra("param2",0);
//            Toast.makeText(this,"param1 = " + strIntent + "\n param2 = " + nIntent,Toast.LENGTH_SHORT).show();




            PendingIntent pi = PendingIntent.getActivity(this,i,intent,PendingIntent.FLAG_ONE_SHOT);

            //创建通知
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("This is a Content Title " + i)
                    .setContentText("This is a Content Text "  + i)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                            R.mipmap.ic_launcher))
                    .setContentIntent(pi)
//                    .setAutoCancel(true) //设置通知自动删除
//                    .setSound(Uri.fromFile(new File("system/media/audio/ringtones/Luna.ogg")))//设置声音
//                    .setVibrate(new long[]{0,1000,1000,1000})//设置震动
//                    .setLights(Color.RED,1000,1000)//设置led灯
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .build();

            //发送通知
            manager.notify(i,notification);
//        }
//                manager.notify(1,notification);


    }


}
