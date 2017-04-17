package com.example.lcarry.teacherlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.lcarry.teacherlist.Teacher;
import com.example.lcarry.teacherlist.TeacherAdapter;
import com.example.lcarry.uiwidgettest.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<Teacher> teachers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

//        //设置水平滚动
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //设置瀑布流布局
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(manager);


        teachers = Teacher.getAllTeachers();
        TeacherAdapter adapter = new TeacherAdapter(teachers);
        recyclerView.setAdapter(adapter);
    }
}
