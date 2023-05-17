package com.hayatwares.sqlwizard.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "Karan_BSDK_KA_HAI_MAA_KA_LAUDA_HAI";
    public MyDbHandler(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @SuppressLint("Range")
    public ArrayList<String> clearDatabase(SQLiteDatabase db){
        ArrayList<String> tableName = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        while (c.moveToNext()) {
            tableName.add(c.getString(c.getColumnIndex("name")));
        }
        //tableName.add("Abc");
        for(String s : tableName){
            Log.e("ppppppp" , s);
            if(!s.equals("android_metadata")){
                deleteDatabase(s , db);
            }

        }

        return tableName;
    }



    @SuppressLint("Range")
    public boolean checkAndValidateAnswer(String query , int level , int questionNo){
        SQLiteDatabase db = this.getWritableDatabase();
        clearDatabase(db);
        if(level == 0 && questionNo == 0){
            return false;
        }else if(level == 0 && questionNo == 1){
            db.execSQL("CREATE TABLE entries(id INTEGER PRIMARY KEY, name TEXT , city TEXT)");
            db.rawQuery("INSERT INTO entries VALUES(1 , 'Karan' , 'Delhi') " , null , null);
            db.rawQuery("INSERT INTO entries VALUES(2 , 'Aman' , 'Delhi') " , null , null);
            db.rawQuery("INSERT INTO entries VALUES(3 , 'Raj' , 'Chennai') " , null , null);
            db.rawQuery("INSERT INTO entries VALUES(4 , 'Ram' , 'Delhi') " , null , null);
            ArrayList<String> ansToBe = new ArrayList<>();
            ansToBe.add("Mawan");
            ansToBe.add("Karan");
            ansToBe.add("Bsdk hai");
            ansToBe.add("Lawda bhi hai");
            Cursor cursor = null;
            query = "SELECT id FROM entries;";
            try{
                cursor = db.rawQuery(query , null , null);
            }catch (Exception e){
                return false;
            }
            Log.e("COunt ", String.valueOf(cursor.getColumnCount()));
            int count = cursor.getColumnCount();
            System.out.println(count + " ");

        }
        return false;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private void deleteDatabase(String tableName , SQLiteDatabase db){
        String query = "DROP TABLE" + " IF EXISTS " + tableName + ";";
        db.execSQL(query); ;
    }


}