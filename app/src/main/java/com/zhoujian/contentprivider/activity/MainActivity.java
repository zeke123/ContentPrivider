package com.zhoujian.contentprivider.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.zhoujian.contentprivider.R;
import com.zhoujian.contentprivider.bean.Book;
import com.zhoujian.contentprivider.bean.User;


/**

 ContentPrivider：内容提供者

 ContentPrivider是Android中提供的专门用于不同应用间进行数据共享的方式

 当我们需要操作其他应用程序的一些数据，例如我们需要操作系统里的媒体库、通讯录等，这时我们就可能通过ContentProvider来满足我们的需求了。

 ContentPrivider可实现进程间通信

 ContentPrivider的底层是Binder

 */


public class MainActivity extends Activity
{


    public static final String TAG = "MainActivity";

    private Uri bookUri;
    private Uri userUri;
    private Cursor bookCursor;
    private int mInt;
    private String mString;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            //书籍的url
            bookUri = Uri.parse("content://com.zhoujian.contentPrivider.bookprivider/book");

            ContentValues values = new ContentValues();
            values.put("_id",4);
            values.put("name","市场营销学");
            //插入书籍
            getContentResolver().insert(bookUri,values);
            //查询书籍
            bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
            Book book = null;
            while (bookCursor.moveToNext())
            {
                book = new Book();
                mInt = bookCursor.getInt(0);
                book.setBookId(mInt);
                mString = bookCursor.getString(1);
                book.setBookName(mString);

                Log.d(TAG, book.toString());
            }
            bookCursor.close();


        }catch (Exception e){
            e.printStackTrace();
        }


        //用户url
        userUri = Uri.parse("content://com.zhoujian.contentPrivider.bookprivider/user");


        //查询用户
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name","sex"}, null, null, null);
        User user = null;
        while (userCursor.moveToNext())
        {
            user = new User();
            int userId = userCursor.getInt(0);
            user.setUserId(userId);

            String userName = userCursor.getString(1);
            user.setUserName(userName);

            int isMale = userCursor.getInt(2);
            user.setIsMale(isMale);

            Log.d(TAG, user.toString());
        }
        userCursor.close();








    }
}