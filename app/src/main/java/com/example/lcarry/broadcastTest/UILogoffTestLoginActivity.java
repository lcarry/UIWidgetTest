package com.example.lcarry.broadcastTest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lcarry.broadcastTest.base.BaseActivity;
import com.example.lcarry.uiwidgettest.R;

public class UILogoffTestLoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText mEditTextAccount;
    private EditText mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uilogoff_test_login);


        Button buttonLogin = (Button)findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(this);
        mEditTextAccount = (EditText)findViewById(R.id.edit_account);
        mEditTextPassword = (EditText)findViewById(R.id.edit_password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login: {
                //登陆按钮
                String account = mEditTextAccount.getText().toString();
                String password =  mEditTextPassword.getText().toString();
                //账号admin，密码123456
//                if (account.equals("admin") && password.equals("123456")){
                    Intent intent = new Intent(UILogoffTestLoginActivity.this, UILogoffTestActivity.class);
                    startActivity(intent);
                    finish();

//                } else {
//                    //用户名密码错误
//                    Toast.makeText(UILogoffTestLoginActivity.this,
//                            "account or password is invalid.",Toast.LENGTH_SHORT).show();
//                }
            }
            break;
            default:
                break;
        }
    }
}
