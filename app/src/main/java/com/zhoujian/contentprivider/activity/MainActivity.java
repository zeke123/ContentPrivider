package com.zhoujian.contentprivider.activity;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.zhoujian.contentprivider.R;


/**

 ContentPrivider：内容提供者

 ContentPrivider是Android中提供的专门用于不同应用间进行数据共享的方式

 当我们需要操作其他应用程序的一些数据，例如我们需要操作系统里的媒体库、通讯录等，这时我们就可能通过ContentProvider来满足我们的需求了。

 ContentPrivider可实现进程间通信

 ContentPrivider的底层是Binder


 */


public class MainActivity extends Activity
{

    private Uri uri;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uri = Uri.parse("content://com.zhoujian.contentPrivider.bookprivider");
        mCursor = getContentResolver().query(uri, null, null, null, null);
        mCursor = getContentResolver().query(uri, null, null, null, null);
        mCursor = getContentResolver().query(uri, null, null, null, null);

    }
}
