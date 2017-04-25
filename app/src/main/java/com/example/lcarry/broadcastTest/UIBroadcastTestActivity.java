package com.example.lcarry.broadcastTest;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lcarry.contactstest.ContactsTestActivity;
import com.example.lcarry.databasetest.DataBaseOperateActivity;
import com.example.lcarry.datapersistance.filepersistance.UIFilePersistenceActivity;
import com.example.lcarry.multimedia.MultimediaActivity;
import com.example.lcarry.notification.UINotificationActivity;
import com.example.lcarry.uiwidgettest.R;

public class UIBroadcastTestActivity extends AppCompatActivity implements View.OnClickListener {

    private IntentFilter mIntentFilter;
    private LocalReceiver mLocalReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uibroadcast_test);



        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);


        //本地广播设置
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("com.example.lcarry.broadcastTest.LOCAL_BROADCAST");
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver,mIntentFilter);//这一步进行本地广播注册


        Button buttonLogoff = (Button)findViewById(R.id.button_logoffTest);
        buttonLogoff.setOnClickListener(this);

        Button buttonFilePersistance = (Button)findViewById(R.id.button_filePersistence);
        buttonFilePersistance.setOnClickListener(this);

        Button buttonCall = (Button)findViewById(R.id.button_call);
        buttonCall.setOnClickListener(this);

        Button buttonReadContacts = (Button)findViewById(R.id.button_read_contacts);
        buttonReadContacts.setOnClickListener(this);


        Button buttonDatabase = (Button)findViewById(R.id.button_database);
        buttonDatabase.setOnClickListener(this);


        Button buttonSendNotice = (Button)findViewById(R.id.button_send_notice);
        buttonSendNotice.setOnClickListener(this);

        Button buttonMultimedia = (Button)findViewById(R.id.button_multimedia);
        buttonMultimedia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                //发送标准广播
                Intent intent = new Intent("com.example.lcarry.broadcastTest.MY_BROADCAST");
                intent.putExtra("param1","MY_BROADCAST");

                //发送标准广播
                sendBroadcast(intent);

                //发送顺序广播
                //sendOrderedBroadcast(intent,null);

                //发送本地广播（只有当前应用内部接收发送）
                Intent intentLocal = new Intent("com.example.lcarry.broadcastTest.LOCAL_BROADCAST");
                intentLocal.putExtra("param1","LOCAL_BROADCAST");
                mLocalBroadcastManager.sendBroadcast(intentLocal);

            }
            break;
            case R.id.button_logoffTest: {
                showUILogoffTestActivity("showUILogoffTestActivity");
            }
            break;
            case R.id.button_filePersistence: {
                showUIFilePersistenceActivity("showUIFilePersistenceActivity");
            }
            break;
            case R.id.button_call: {
                //拨打电话，运行时权限
                if (ContextCompat.checkSelfPermission(UIBroadcastTestActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(UIBroadcastTestActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},1);
                } else
                    showCallPhone("showCallPhone");
            }
            break;
            case R.id.button_read_contacts: {
                showContactsTestActivity("showContactsTestActivity");
            }
            break;
            case R.id.button_database: {
                showDatabaseOperateActivity("showDatabaseOperateActivity");
            }
            break;

            case R.id.button_send_notice: {
                showUINotificationActivity("showUINotificationActivity");
            }
            break;
            case R.id.button_multimedia: {
                showUIMultimediaActivity("showUIMultimediaActivity");
            }
            break;


            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            String strParam1 = intent.getStringExtra("param1");
            Toast.makeText(context,"received in local broadcast .param1=" + strParam1,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 1:
            {
                if (grantResults.length >0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    showCallPhone("showCallPhone");
                else
                    Toast.makeText(UIBroadcastTestActivity.this,
                            "You Denied The Permission",Toast.LENGTH_SHORT).show();
            }
            break;
            default:
                break;
        }

    }

    public void showUILogoffTestActivity(String strName) {
        Intent intent = new Intent(UIBroadcastTestActivity.this, UILogoffTestActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }

    public void showUIFilePersistenceActivity(String strName) {
        Intent intent = new Intent(UIBroadcastTestActivity.this, UIFilePersistenceActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }

    public void showCallPhone(String strName) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:13810590092"));
            startActivity(intent);
            Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    public void showContactsTestActivity(String strName) {
        Intent intent = new Intent(UIBroadcastTestActivity.this, ContactsTestActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }


    public void showDatabaseOperateActivity(String strName) {
        Intent intent = new Intent(UIBroadcastTestActivity.this, DataBaseOperateActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }
    public void showUINotificationActivity(String strName) {
        Intent intent = new Intent(UIBroadcastTestActivity.this, UINotificationActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }

    public void showUIMultimediaActivity(String strName) {
        Intent intent = new Intent(UIBroadcastTestActivity.this, MultimediaActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }

}
