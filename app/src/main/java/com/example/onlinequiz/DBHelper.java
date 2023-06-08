package com.example.onlinequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "onlinequiz.db";

    public DBHelper(Context context) {
        super(context, "onlinequiz.dp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create table users (TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("Drop table if exists users");
    }

    public boolean insertData(String username, String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result=myDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public boolean checkusername(String username){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor =myDB.rawQuery("Select * from users where username =?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkuserpass(String username, String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor =myDB.rawQuery("Select * from users where username =? and password=?",new String[]{username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
