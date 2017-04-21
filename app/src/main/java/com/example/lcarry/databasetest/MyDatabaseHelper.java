package com.example.lcarry.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ${lcarry} on 2017/4/19.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREAT_BOOK =
            "create table Book("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + "author text,"
                    + "price real,"
                    + "pages integer,"
                    + "name text)";

    public static final String CREAT_CATEGORY =
            "create table Category("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + "category_name text,"
                    + "category_code integer)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //createTable(db,false);
        db.execSQL(CREAT_BOOK);
        db.execSQL(CREAT_CATEGORY);

        //内部提示，提供给外部做内容提供器时，不显示
        Toast.makeText(mContext,"Create database succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }

    private void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Book\" (" + //
                "\"id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: pid
                "\"author\" TEXT," + // 1: 作者
                "\"price\" REAL," + // 2: 价格
                "\"pages\" INTEGER," + // 3: 页数
                "\"name\" TEXT);"); // 4: 书名
    }
}
