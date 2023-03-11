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
            db.execSQL("CREATE TABLE Abc(id INTEGER, name TEXT)");
            db.rawQuery("INSERT INTO Abc values(1 , 'Mawan');" , null , null);
            db.rawQuery("INSERT INTO Abc values(2 , 'Karan');" , null , null);
            db.rawQuery("INSERT INTO Abc values(3 , 'Bsdk hai');" , null , null);
            db.rawQuery("INSERT INTO Abc values(4 , 'Lawda bhi hai');" , null , null);
            ArrayList<String> ansToBe = new ArrayList<>();
            ansToBe.add("Mawan");
            ansToBe.add("Karan");
            ansToBe.add("Bsdk hai");
            ansToBe.add("Lawda bhi hai");
            Cursor cursor = null;
            try{
                cursor = db.rawQuery(query , null , null);
            }catch (Exception e){
                return false;
            }
            if(cursor == null) return false;
            ArrayList<String> userAns = new ArrayList<>();
            while(cursor.moveToNext()){
                userAns.add(cursor.getString(cursor.getColumnIndex("name")));
            }
            for(int i = 0 ; i < Math.min(userAns.size() , ansToBe.size()) ; i++){
                if(!userAns.get(i).equals(ansToBe.get(i))){
                    cursor.close();
                    return false;
                }
            }
            cursor.close();
            return true;
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