package com.zhoujian.contentprivider.privider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

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

    private static final String TAG = "BookPrivider";

    public boolean onCreate()
    {
        //获取当前线程名
        Log.d(TAG, "onCreate()，当前线程==="+Thread.currentThread().getName());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1)
    {
        //获取当前线程名
        Log.d(TAG, "query()，当前线程==="+Thread.currentThread().getName());
        return null;
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
        Log.d(TAG, "insert()");
        return null;
    }

    public int delete(Uri uri, String s, String[] strings)
    {
        Log.d(TAG, "delete()");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String s, String[] strings)
    {
        Log.d(TAG, "update()");
        return 0;
    }

}
