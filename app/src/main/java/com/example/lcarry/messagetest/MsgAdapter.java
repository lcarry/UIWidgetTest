package com.example.lcarry.messagetest;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lcarry.uiwidgettest.R;

import java.util.List;

/**
 * Created by ${lcarry} on 2017/4/13.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> mMsgList;

    //内部类viewholder
    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftTextView;
        TextView rightTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            leftLayout = (LinearLayout) itemView.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) itemView.findViewById(R.id.right_layout);
            leftTextView = (TextView) itemView.findViewById(R.id.left_msg);
            rightTextView = (TextView) itemView.findViewById(R.id.right_msg);
        }
    }

    public MsgAdapter(List<Msg> mMsgList) {
        this.mMsgList = mMsgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);

        final ViewHolder viewHolder = new ViewHolder(view);

        //点击接收的信息内容
        viewHolder.leftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Msg msg = mMsgList.get(position);
                Toast.makeText(v.getContext(),"You Clicked Receiveed Msg",Toast.LENGTH_SHORT).show();
            }
        });
        //点击发送的信息内容
        viewHolder.rightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Msg msg = mMsgList.get(position);
                Toast.makeText(v.getContext(),"You Clicked Sent Msg",Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        switch (msg.getType()) {
            //接收的信息，左侧气泡显示，右侧气泡gone
            case Msg.TYPE_RECEIVED:
            {
                holder.leftLayout.setVisibility(View.VISIBLE);
                holder.rightLayout.setVisibility(View.GONE);
                holder.leftTextView.setText(msg.getContent());
            }
            break;
            //发送的信息，左侧气泡gone，右侧气泡显示
            case Msg.TYPE_SEND:
            {
                holder.leftLayout.setVisibility(View.GONE);
                holder.rightLayout.setVisibility(View.VISIBLE);
                holder.rightTextView.setText(msg.getContent());
            }
            break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
