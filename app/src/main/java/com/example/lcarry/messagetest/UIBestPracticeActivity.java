package com.example.lcarry.messagetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lcarry.messagetest.Msg;
import com.example.lcarry.messagetest.MsgAdapter;
import com.example.lcarry.uiwidgettest.R;

import java.util.ArrayList;
import java.util.List;

public class UIBestPracticeActivity extends AppCompatActivity {

    //定义几项变量
    private List<Msg> mMsgList;
    private EditText mInput;
    private Button mSend;
    private RecyclerView mMsgRecyclerView;
    private MsgAdapter mMsgAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uibest_practice);

        //初始化控件
        mMsgList = new ArrayList<>();
        mInput = (EditText) findViewById(R.id.edit_text);
        mSend = (Button) findViewById(R.id.send_button);
        mMsgRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mMsgAdapter = new MsgAdapter(mMsgList);

        //初始化几条消息
        Msg.getAllMsgs(mMsgList);

        //设置adapter
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mMsgRecyclerView.setLayoutManager(manager);
        mMsgRecyclerView.setAdapter(mMsgAdapter);
//        mMsgRecyclerView.scrollToPosition(mMsgList.size() - 1);

        //发送按钮点击事件
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strInput = mInput.getText().toString();
//                Toast.makeText(v.getContext(),"input message：" + strInput,Toast.LENGTH_SHORT).show();
                //发送消息不能为空
//                if (strInput.isEmpty()) {

                if (!"".equals(strInput)) {

                    if (mSend.getText().equals("Send")) {
                        //发送消息
                        Msg msgSend = new Msg(strInput, Msg.TYPE_SEND);
                        mMsgList.add(msgSend);
                    } else if (mSend.getText().equals("Receive")) {
                        //接收消息
                        Msg msgReceive = new Msg(strInput, Msg.TYPE_RECEIVED);
                        mMsgList.add(msgReceive);
                    }

                    mMsgAdapter.notifyItemInserted(mMsgList.size() - 1);/*插入新增消息*/
                    //刷新RecyclerView显示
                    mMsgRecyclerView.smoothScrollToPosition(mMsgList.size() - 1);/*将页面定位到最后一行消息*/
                    mInput.setText("");/*置空消息*/

                    if (mSend.getText().equals("Send")) {
                        //发送消息
                        mSend.setText("Receive");/*修改按钮作用*/
                    } else if (mSend.getText().equals("Receive")) {
                        //接收消息
                        mSend.setText("Send");/*修改按钮作用*/
                    }
                } else {
                    Toast.makeText(v.getContext(),"Please input message",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
