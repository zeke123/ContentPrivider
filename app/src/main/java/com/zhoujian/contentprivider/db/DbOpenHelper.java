package com.zhoujian.contentprivider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhoujian on 2016/11/30.
 */

public class DbOpenHelper extends SQLiteOpenHelper
{
    //数据库名
    private static final String DB_NAME="provider.db";

    //书籍表名
    public static final String BOOK_TABLE = "book";

    //用户表名
    public static final String  USER_TABLE = "user";

    //数据库版本
    private static final int DB_VERSION = 1;



    public DbOpenHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {

        //执行创建书籍表的sql语句(一个字段 name)
        database.execSQL("CREATE TABLE IF NOT EXISTS " + BOOK_TABLE + "(_id INTEGER PRIMARY KEY," + "name TEXT)");
        //执行创建用户表的sql语句（两个字段  name 和 sex）
        database.execSQL("CREATE TABLE IF NOT EXISTS "
                + USER_TABLE + "(_id INTEGER PRIMARY KEY," + "name TEXT,"
                + "sex INT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1)
    {

    }
}
