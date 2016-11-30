package com.zhoujian.contentprivider.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhoujian on 2016/11/30.
 */

public class Book implements Parcelable
{

    private  int bookId;
    private String bookName;


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    @Override
    public String toString()
    {
        return "Book{" + "bookId=" + bookId + ", bookName='" + bookName + '\'' + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public Book() {
    }

    protected Book(Parcel in) {
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
