package com.example.lcarry.uiwidgettest;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UICustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uicustomer);

        //隐藏actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();


    }
}
