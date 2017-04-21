package com.example.lcarry.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lcarry.uiwidgettest.R;

public class DataBaseOperateActivity extends AppCompatActivity  implements View.OnClickListener{

    private MyDatabaseHelper myDatabaseHelper;

    private static final int DB_VERSION = 2;//1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_operate);

        myDatabaseHelper = new MyDatabaseHelper(this,"BookStore.db",null,DB_VERSION);

        Button buttonCreateDb = (Button)findViewById(R.id.button_create_database);
        buttonCreateDb.setOnClickListener(this);

        Button buttonAddData = (Button)findViewById(R.id.button_insert_data);
        buttonAddData.setOnClickListener(this);

        Button buttonUpdateData = (Button)findViewById(R.id.button_update_data);
        buttonUpdateData.setOnClickListener(this);

        Button buttonDeleteData = (Button)findViewById(R.id.button_delete_data);
        buttonDeleteData.setOnClickListener(this);

        Button buttonQueryData = (Button)findViewById(R.id.button_query_data);
        buttonQueryData.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_create_database:
            {
                myDatabaseHelper.getWritableDatabase();
            }
            break;
            case R.id.button_insert_data:
            {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                //第一组数据
                values.put("name","The Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();

                //第二组数据
                values.put("name","The Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book",null,values);
                Toast.makeText(this,"Insert succeeded",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.button_update_data:
            {

                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();


                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name = ?",new String[] {"The Da Vinci Code"});
                Toast.makeText(this,"Update succeeded",Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.button_delete_data:
            {

                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                db.delete("Book","pages > ?",new String[] {"500"});
                Toast.makeText(this,"Delete succeeded",Toast.LENGTH_SHORT).show();

            }
            break;

            case R.id.button_query_data:
            {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();

                Cursor cursor = db.query("Book",null,null,null,null,null,null,null);

                if (cursor.moveToFirst())
                {
                    do {
                        //遍历cursor对象
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.w("DataBaseOperateActivity","book name is \n"+ name);
                        Log.w("DataBaseOperateActivity","book author is \n"+ author);
                        Log.w("DataBaseOperateActivity","book pages is \n" + pages);
                        Log.w("DataBaseOperateActivity","book price is \n" + price);
                    } while (cursor.moveToNext());
                }


                cursor.close();
                Toast.makeText(this,"Delete succeeded",Toast.LENGTH_SHORT).show();

            }
            break;




            default:
                break;
        }
    }
}
