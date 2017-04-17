package com.example.lcarry.fragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lcarry.uiwidgettest.R;

/**
 * Created by ${lcarry} on 2017/4/13.
 */

public class rightFragment extends Fragment {

    private View mView;
    private static final String TAG = "RightFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        debutPrint("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        debutPrint("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        debutPrint("onCreateView");
        mView = inflater.inflate(R.layout.right_fragment,container,false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        debutPrint("onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        debutPrint("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        debutPrint("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        debutPrint("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        debutPrint("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        debutPrint("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        debutPrint("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        debutPrint("onDetach");
    }

    //打印结果
    public void debutPrint(String strContent){
        Log.w(TAG,strContent);
//        Toast.makeText(mView.getContext(),"RightFragment:" + strContent,Toast.LENGTH_SHORT).show();
    }
}
