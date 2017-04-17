package com.example.lcarry.broadcastTest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lcarry.broadcastTest.base.BaseActivity;
import com.example.lcarry.fragmentbestpractice.UIFragmentBestPracticeActivity;
import com.example.lcarry.fragmenttest.UIFragmentTestActivity;
import com.example.lcarry.uiwidgettest.R;

public class UIBroadcastTestActivity extends BaseActivity implements View.OnClickListener {

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

    public void showUILogoffTestActivity(String strName) {
        Intent intent = new Intent(UIBroadcastTestActivity.this, UILogoffTestActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }
}
