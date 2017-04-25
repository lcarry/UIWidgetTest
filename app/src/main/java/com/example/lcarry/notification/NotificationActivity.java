package com.example.lcarry.notification;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lcarry.uiwidgettest.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);



        Intent intent = getIntent();
        String strIntent = intent.getStringExtra("param1");
        int nIntent = intent.getIntExtra("param2",0);
        TextView textView = (TextView) findViewById(R.id.text_notification);
        textView.setText(strIntent);

        Toast.makeText(this,"param2 = " + nIntent,Toast.LENGTH_SHORT).show();
        //点击通知进入本页面，通知消失
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(nIntent);
    }
}
