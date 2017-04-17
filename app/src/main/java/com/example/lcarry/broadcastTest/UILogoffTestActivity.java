package com.example.lcarry.broadcastTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lcarry.broadcastTest.base.BaseActivity;
import com.example.lcarry.uiwidgettest.R;

public class UILogoffTestActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uilogoff_test);


        Button buttonLogoff = (Button)findViewById(R.id.button_logoff);
        buttonLogoff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_logoff: {
                Toast.makeText(UILogoffTestActivity.this,
                        "sendBroadcast here.",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent("com.example.lcarry.broadcastTest.FORCE_OFFLINE");
                intent.putExtra("param1","OFFLINE_BROADCAST");
                //发送标准广播
                sendBroadcast(intent);
            }
            break;
            default:
                break;
        }
    }
}
