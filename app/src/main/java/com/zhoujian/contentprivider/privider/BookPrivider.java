package com.zhoujian.contentprivider.privider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.zhoujian.contentprivider.db.DbOpenHelper;

/**

  Created by zhoujian on 2016/11/29.

  实现为实现的方法选中BookPrivider————>option+enter----->选择未实现的方法

  要在清单文件中配置

 <provider
          android:name=".privider.BookPrivider"
          android:process="com.zhoujian.contentPrivider:privider"
          android:authorities="com.zhoujian.contentPrivider.bookprivider"
          android:permission="com.zhoujian.contentPrivider.permission"/>


 其中android:authorities="com.zhoujian.contentPrivider.bookprivider"是唯一标识

 通过这个属性外部应用就可以访问这个BookPrivider，因此authorities必须是唯一的，

 外部应用就可以访问这个BookPrivider，也必须加上这个权限（如果要是两个应用，就必须加上权限）

 android:permission="com.zhoujian.contentPrivider.permission"



 */

public class BookPrivider extends ContentProvider
{

    //快捷键：shift+command+u   大小写转换

    private static final String TAG = "BookPrivider";

    //访问的权限

    public static final String AUTHORITIES = "com.zhoujian.contentPrivider.bookprivider";

    //书籍表的uri

    public static  final Uri BOOK_URI = Uri.parse("content://"+AUTHORITIES+"/book");

    //用户表的uri

    public static  final Uri USER_URI = Uri.parse("content://"+AUTHORITIES+"/user");

    //创建Uri匹配器

    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //书籍表的uri code

    private static  final  int BOOK_URI_CODE = 0;

    //用户表的uri code
    private static  final  int USER_URI_CODE = 1;

    //静态代码块

    static
    {
        mUriMatcher.addURI(AUTHORITIES,"book",BOOK_URI_CODE);
        mUriMatcher.addURI(AUTHORITIES,"user",USER_URI_CODE);

    }

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private String table;


    public boolean onCreate()
    {
        //获取当前线程名
        Log.d(TAG, "onCreate()，当前线程==="+Thread.currentThread().getName());
        mContext = getContext();

        initData();

        return true;
    }

    private void initData()
    {
        DbOpenHelper db = new DbOpenHelper(mContext);
        mDatabase = db.getWritableDatabase();

        mDatabase.execSQL("delete from book");

        mDatabase.execSQL("delete from user");

        mDatabase.execSQL("insert into book values(1,'操作系统课程');");
        mDatabase.execSQL("insert into book values(2,'计算机网络');");
        mDatabase.execSQL("insert into book values(3,'计算机系统结构');");

        mDatabase.execSQL("insert into user values(1,'周建',0);");
        mDatabase.execSQL("insert into user values(2,'宋宁宁',1);");

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        //获取当前线程名
        Log.d(TAG, "query()，当前线程==="+Thread.currentThread().getName());

        table = getTableName(uri);

        if (table == null) {

            throw new IllegalArgumentException("不支持该uir");

        }


        return mDatabase.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    private String getTableName(Uri uri) {

        String tableName = null;

        switch (mUriMatcher.match(uri))
        {

            case BOOK_URI_CODE:

                tableName = DbOpenHelper.BOOK_TABLE;

                break;

            case USER_URI_CODE:

                tableName = DbOpenHelper.USER_TABLE;

                break;

        }


        return  tableName;
    }

    @Override
    public String getType(Uri uri)
    {
        Log.d(TAG, "getType()");
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        table = getTableName(uri);
        mDatabase.insert(table,null,values);

        mContext.getContentResolver().notifyChange(uri,null);
        Log.d(TAG, "insert()");
        return uri;
    }

    public int delete(Uri uri, String s, String[] strings)
    {
        table = getTableName(uri);

        int count = mDatabase.delete(table,s,strings);

        if (count>0) {
            mContext.getContentResolver().notifyChange(uri,null);
        }


        Log.d(TAG, "delete()");
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String s, String[] strings)
    {
        Log.d(TAG, "update()");

        table = getTableName(uri);

        int row = mDatabase.update(table,values,s,strings);

        if (row>0) {
            mContext.getContentResolver().notifyChange(uri,null);
        }
        Log.d(TAG, "delete()");

        return row;
    }
}
