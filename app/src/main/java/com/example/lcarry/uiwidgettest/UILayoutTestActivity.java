package com.example.lcarry.uiwidgettest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UILayoutTestActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uilayout_test);
        Button button = (Button) findViewById(R.id.button_ui_layout);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_ui_layout:
            {
                showUIRelativeActivity();
                Toast.makeText(this,"showUIRelativeActivity",Toast.LENGTH_SHORT).show();
            }
            break;
            default:
                break;
        }
    }

    public void showUIRelativeActivity(){
        Intent intent = new Intent(UILayoutTestActivity.this,UIRelativeActivity.class);
        startActivity(intent);
    }
}
