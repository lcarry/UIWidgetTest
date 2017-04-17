package com.example.lcarry.teacherlist;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lcarry.uiwidgettest.R;

import java.util.List;

/**
 * Created by lcarry on 2017/3/31.
 */

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {

    private List<Teacher> mTeachers;

    public TeacherAdapter(List<Teacher> teachers) {
        mTeachers = teachers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_item,parent,false);
//        ViewHolder viewHolder = new ViewHolder(view);

        //常量holder
        final ViewHolder viewHolder = new ViewHolder(view);
        //点击列表项
        viewHolder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Teacher teacher = mTeachers.get(position);
                Toast.makeText(v.getContext(),"You Clicked View" + teacher.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        //点击图片
        viewHolder.imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Teacher teacher = mTeachers.get(position);
                Toast.makeText(v.getContext(),"You Clicked image" + teacher.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        //点击文字
        viewHolder.textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Teacher teacher = mTeachers.get(position);
                Toast.makeText(v.getContext(),"You Clicked text" + teacher.getName(),Toast.LENGTH_SHORT).show();
            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Teacher teacher = mTeachers.get(position);
        holder.imageView.setImageResource(teacher.getImgId());
        holder.textView.setText(teacher.getName());
    }

    @Override
    public int getItemCount() {
        return mTeachers.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView)itemView.findViewById(R.id.teacher_small_imageView);
            textView = (TextView)itemView.findViewById(R.id.teacher_name_textView);
        }
    }
}
