package com.mohamed.rami.firebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHeandler extends SQLiteOpenHelper {
    private static  final  int  DATABASE_VERSION=1;
    private static  final String DATABASE_Name="product.db";
    private static  final String TABLE="table";
    private static  final String DATABASE_ID="_id";
    private static  final String DATABASE_name="name";
    private static  final String DATABASE_USERMANE="username";
    private static  final String DATABASE_pass="pass";

    public MyDBHeandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_Name    , factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String query="CREATE TABLE " + TABLE + "("
              + DATABASE_Name + " TEXT," + DATABASE_USERMANE + " TEXT,"
              + DATABASE_pass + "))";
       db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
              db.execSQL("DROP TABLE IF EXISTS "+TABLE);
              onCreate(db );
    }

    public void  insert (String name,String username,String pass){
        ContentValues values= new ContentValues();
        values.put(DATABASE_name,name);
        values.put(DATABASE_USERMANE,username);
        values.put(DATABASE_pass,pass);
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE,null,values);
        db.close();

    }
    // select
    public Cursor databaseoutput(String name){
        String dbString="";
        SQLiteDatabase db =getWritableDatabase();
        String query="select * from "+TABLE+" where username = "+name;

       Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("name"))!=null){
            }
        }
             db.close();
        return c;
    }


}
