package com.example.lcarry.fragmenttest;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lcarry.fragmentbestpractice.UIFragmentBestPracticeActivity;
import com.example.lcarry.uiwidgettest.R;

public class UIFragmentTestActivity extends AppCompatActivity implements View.OnClickListener {

    private Fragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uifragment_test);
        Button button = (Button)findViewById(R.id.button_left_fragment);
        button.setOnClickListener(this);
        replaceRightFragment(new rightFragment());

        //新闻页面实例按钮
        Button buttonNews = (Button)findViewById(R.id.button_left_fragment_News);
        buttonNews.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_left_fragment:
            {
                if (null != mFragment)
                {
                    Toast.makeText(v.getContext(),
                            String.valueOf(mFragment.getId()),Toast.LENGTH_SHORT).show();
                    if (mFragment.getId() == R.layout.another_right_fragment) {
                        replaceRightFragment(new rightFragment());
                        Toast.makeText(v.getContext(),"ReplaceTORightFragment",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                replaceRightFragment(new AnotherRightFragment());
                Toast.makeText(v.getContext(),"ReplaceTOAnotherRightFragment",Toast.LENGTH_SHORT).show();
            }break;
            case R.id.button_left_fragment_News:
            {
                showUIFragmentBestPractice("showUIFragmentBestPracticeActivity");
            }break;
            default:
                break;
        }
    }

    //替换fragment
    public void replaceRightFragment(Fragment fragment){
//        mFragment = fragment;
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = manager.beginTransaction();
//        fragmentTransaction.replace(R.id.right_layout,fragment);
////        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
    }

    public void showUIFragmentBestPractice(String strName){
        Intent intent = new Intent(UIFragmentTestActivity.this,UIFragmentBestPracticeActivity.class);
        startActivity(intent);
        Toast.makeText(this,strName,Toast.LENGTH_SHORT).show();
    }
}
