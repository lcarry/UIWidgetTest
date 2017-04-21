package com.example.lcarry.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.lcarry.fragmentbestpractice.News;

public class DatabaseProvider extends ContentProvider {

    //数据库操作对象
    private MyDatabaseHelper dbHelper;

    public static final int BOOK_DIR = 0;//查询Book表
    public static final int BOOK_ITEM = 1;//查询Book表单条记录
    public static final int CATECORY_DIR = 2;//查询cagetory表
    public static final int CATECORY_ITEM = 3;//查询cagetory表单条记录

    //Uri权限
    public static final String AUTHORITY = "com.example.lcarry.databasetest.provider";


    //静态uri匹配器
    private static UriMatcher uriMatcher;
    //构造静态urimacher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATECORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATECORY_ITEM);
    }

    public DatabaseProvider() {
    }


    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.

        dbHelper = new MyDatabaseHelper(getContext(),"BookStore.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
//        throw new UnsupportedOperationException("Not yet implemented");


        //查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor =null;

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            {
                cursor = db.query("Book",projection,selection,selectionArgs,
                        null,null,sortOrder);
            }break;
            case BOOK_ITEM:
            {
                String bookid = uri.getPathSegments().get(1);//将URI权限之后的部分以/分割，0则表示路径，1表示bookid
                cursor = db.query("Book",projection,"id = ?",new String[]{ bookid },
                        null,null,sortOrder);
            }break;
            case CATECORY_DIR:
            {
                cursor = db.query("Category",projection,selection,selectionArgs,
                        null,null,sortOrder);
            }break;
            case CATECORY_ITEM:
            {
                String categoryId = uri.getPathSegments().get(1);//将URI权限之后的部分以/分割，0则表示路径，1表示bookid
                cursor = db.query("Book",projection,"id = ?",new String[]{ categoryId },
                        null,null,sortOrder);
            }break;
            default:
                break;
        }

        return  cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
//        throw new UnsupportedOperationException("Not yet implemented");


        //插入数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Uri uriReturn =null;

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
            {
                long newBookId = db.insert("Book",null,values);
                uriReturn = Uri.parse("content://" + AUTHORITY +"/book/" +
                newBookId);
            }break;
            case CATECORY_DIR:
            case CATECORY_ITEM:
            {
                long newCategoryId = db.insert("Cagetory",null,values);
                uriReturn = Uri.parse("content://" + AUTHORITY +"/cagetory/" +
                        newCategoryId);
            }break;
            default:
                break;
        }

        return uriReturn;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");

        //插入数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int updateRows = 0;

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            {
                updateRows = db.update("Book",values,selection,selectionArgs);
            }break;
            case BOOK_ITEM:
            {
                String bookId = uri.getPathSegments().get(1);
                updateRows = db.update("Book",values,"id = ?", new String[]{bookId});
            }break;
            case CATECORY_DIR:
            {
                updateRows = db.update("Category",values,selection,selectionArgs);
            }break;
            case CATECORY_ITEM:
            {
                String categoryId = uri.getPathSegments().get(1);
                updateRows = db.update("Category",values,"id = ?", new String[]{categoryId});
            }break;
            default:
                break;
        }

        return  updateRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        //插入数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int deletRows = 0;

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            {
                deletRows = db.delete("Book",selection,selectionArgs);
            }break;
            case BOOK_ITEM:
            {
                String bookId = uri.getPathSegments().get(1);
                deletRows = db.delete("Book","id = ?", new String[]{bookId});
            }break;
            case CATECORY_DIR:
            {
                deletRows = db.delete("Category",selection,selectionArgs);
            }break;
            case CATECORY_ITEM:
            {
                String categoryId = uri.getPathSegments().get(1);
                deletRows = db.delete("Category","id = ?", new String[]{categoryId});
            }break;
            default:
                break;
        }

        return  deletRows;
    }



    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        String type = null;

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            {
                type = "vnd.android.cursor.dir/vnd." + AUTHORITY +".book";
            }break;
            case BOOK_ITEM:
            {
                type = "vnd.android.cursor.item/vnd." + AUTHORITY +".book";
            }break;
            case CATECORY_DIR:
            {
                type = "vnd.android.cursor.dir/vnd." + AUTHORITY +".category";
            }break;
            case CATECORY_ITEM:
            {
                type = "vnd.android.cursor.item/vnd." + AUTHORITY +".category";
            }break;
            default:
                break;

        }


        return type;

    }
}
