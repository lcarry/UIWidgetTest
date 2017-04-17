package com.example.lcarry.broadcastTest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");

        String strParam1 = intent.getStringExtra("param1");
        Toast.makeText(context,"received in MyBroadcastReceiver.param1=" + strParam1,Toast.LENGTH_SHORT).show();

        //可以拦截顺序广播
//        abortBroadcast();
    }
}
