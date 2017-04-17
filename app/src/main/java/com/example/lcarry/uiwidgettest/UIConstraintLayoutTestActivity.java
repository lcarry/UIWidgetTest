package com.example.lcarry.uiwidgettest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UIConstraintLayoutTestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiconstraint_layout_test);

        Button button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(this);

        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button6:
            {
                showUICustomerActivity();
                Toast.makeText(this,"showUICustomerActivity",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.button8:
            {
                showUIListViewActivity();
                Toast.makeText(this,"showUIListViewActivity",Toast.LENGTH_SHORT).show();
            }
            break;
            default:
                break;
        }
    }

    public void showUICustomerActivity(){
        Intent intent = new Intent(UIConstraintLayoutTestActivity.this,UICustomerActivity.class);
        startActivity(intent);
    }

    public void showUIListViewActivity(){
        Intent intent = new Intent(UIConstraintLayoutTestActivity.this,ListViewActivity.class);
        startActivity(intent);
    }
}
