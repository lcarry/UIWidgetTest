package com.example.lcarry.uiwidgettest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lcarry.broadcastTest.UIBroadcastTestActivity;
import com.example.lcarry.fragmenttest.UIFragmentTestActivity;
import com.example.lcarry.messagetest.UIBestPracticeActivity;
import com.example.lcarry.teacherlist.RecyclerViewActivity;

public class UIRelativeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uirelative);
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
            {
                showUIConstraintLayoutActivity();
                Toast.makeText(this,"showUIConstraintLayoutActivity",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.button2:
            {
                showUIRecyclerViewActivity();
                Toast.makeText(this,"showUIRecyclerViewActivity",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.button3:
            {
                showUIBestPracticeActivity();
                Toast.makeText(this,"showUIBestPracticeActivity",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.button4:
            {
                showUIFragmentTestActivity();
                Toast.makeText(this,"showUIFragmentTestActivity",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.button5:
            {
                showUIBroadcastTestActivity();
                Toast.makeText(this,"showUIBroadcastTestActivity",Toast.LENGTH_SHORT).show();
            }
            break;
            default:
                break;
        }
    }

    public void showUIConstraintLayoutActivity(){
        Intent intent = new Intent(UIRelativeActivity.this,UIConstraintLayoutTestActivity.class);
        startActivity(intent);
    }

    public void showUIRecyclerViewActivity(){
        Intent intent = new Intent(UIRelativeActivity.this,RecyclerViewActivity.class);
        startActivity(intent);
    }

    public void showUIBestPracticeActivity(){
        Intent intent = new Intent(UIRelativeActivity.this,UIBestPracticeActivity.class);
        startActivity(intent);
    }

    public void showUIFragmentTestActivity(){
        Intent intent = new Intent(UIRelativeActivity.this,UIFragmentTestActivity.class);
        startActivity(intent);
    }


    public void showUIBroadcastTestActivity(){
        Intent intent = new Intent(UIRelativeActivity.this,UIBroadcastTestActivity.class);
        startActivity(intent);
    }
}
