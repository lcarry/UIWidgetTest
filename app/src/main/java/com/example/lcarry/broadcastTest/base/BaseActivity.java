package com.example.lcarry.broadcastTest.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.lcarry.broadcastTest.UILogoffTestLoginActivity;
import com.example.lcarry.broadcastTest.util.ActivityCollector;
import com.example.lcarry.uiwidgettest.R;

/**
 * Created by ${lcarry} on 2017/4/7.
 */

public class BaseActivity extends AppCompatActivity {

    public static final String TAG = "BaseActivity";

    private ForceOfflineReceiver forceOfflineReceiver;

    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,getClass().getSimpleName());

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //窗体置顶时，可以接收强制下线广播消息
        //localBroadcastManager = LocalBroadcastManager.getInstance(BaseActivity.this);

        Toast.makeText(this,
        "BaseActivity onResume.",Toast.LENGTH_SHORT).show();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.lcarry.broadcastTest.FORCE_OFFLINE");
        forceOfflineReceiver = new ForceOfflineReceiver();
        //localBroadcastManager.registerReceiver(forceOfflineReceiver,intentFilter);
        registerReceiver(forceOfflineReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,
                "BaseActivity onPause.",Toast.LENGTH_SHORT).show();
        //窗体没有置顶时，不可以接收强制下线广播消息
        if (forceOfflineReceiver != null && localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.logoff_err_desc);
            builder.setTitle(R.string.logoff_err_title);
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //销毁所有活动
                    ActivityCollector.finishAll();
                    //重启登陆
                    Intent intent = new Intent(context, UILogoffTestLoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
