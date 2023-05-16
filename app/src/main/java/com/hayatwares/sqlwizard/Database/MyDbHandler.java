package com.hayatwares.sqlwizard.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.hayatwares.sqlwizard.Interfaces.DisplayIncorrectDialog;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    private DisplayIncorrectDialog incorrectDialog;
    private static String DATABASE_NAME = "Karan_BSDK_KA_HAI_MAA_KA_LAUDA_HAI";
    public MyDbHandler(Context context , DisplayIncorrectDialog incorrectDialog) {
        super(context, DATABASE_NAME, null, 1);
        this.incorrectDialog = incorrectDialog;
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
//            db.execSQL("CREATE TABLE Abc(id INTEGER, name TEXT)");
//            db.rawQuery("INSERT INTO Abc values(1 , 'Mawan');" , null , null);
//            db.rawQuery("INSERT INTO Abc values(2 , 'Karan');" , null , null);
//            db.rawQuery("INSERT INTO Abc values(3 , 'Bsdk hai');" , null , null);
//            db.rawQuery("INSERT INTO Abc values(4 , 'Lawda bhi hai');" , null , null);
//
//            Cursor cursor = null;
//            Cursor cursor1 = null;
//            try{
//               // query = "SELECT id FROM abc;";
//
//                cursor = db.rawQuery(query , null , null);
//                //cursor1 = db.rawQuery(query , null , null);
//            }catch (Exception e){
//                if(incorrectDialog != null){
//                    incorrectDialog.displayDialog("Query is not Properly Written" , "Lawda Sur");
//                }
//
//                return false;
//            }
//            if(cursor == null){
//                if(incorrectDialog != null) {
//                    incorrectDialog.displayDialog("", "");
//                }
//
//                return false;
//            }
//            StringBuilder userAnsBuilder = new StringBuilder();
//            String ansToBe = "[ 1 Mawan ][ 2 Karan ][ 3 Bsdk hai ][ 4 Lawda bhi hai ]";
//            int numberOfColums = 0;
//            //Cursor tempCursor = cursor;
//            ArrayList<String> columnsName = new ArrayList<>();
////            try{
////                while(true){
////                    String curColumnName = cursor.getColumnName(numberOfColums);
////                    numberOfColums++;
////                    columnsName.add(curColumnName);
////                }
////            }catch (Exception e){
////
////            }
//            System.out.print(numberOfColums + " ");
////            if(cursor == null){
////                System.out.println("Null Kr diya bsdk ne");
////            }
//            cursor.moveToFirst();
//            while(cursor.moveToNext()){
////                StringBuilder curRow = new StringBuilder();
////                curRow.append("[ ");
////                for(String colName : columnsName){
////                    curRow.append(cursor1.getString(cursor1.getColumnIndex(colName)) + " ");
////                }
////                curRow.append("]");
////                userAnsBuilder.append(new String(curRow));
//                System.out.println(cursor.getString(cursor.getColumnIndex("name")));
//                userAnsBuilder.append(cursor.getString(cursor.getColumnIndex("name")));
//            }
//            cursor.close();
//            String userAns = new String(userAnsBuilder);
//            System.out.print(userAns + " ");
//            if(!userAns.equals(ansToBe)){
//                incorrectDialog.displayDialog(userAns + " " + query , ansToBe);
//                cursor.close();
//                return false;
//            }
//            cursor.close();
//            return true;
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
            String s = DatabaseUtils.dumpCursorToString(cursor);
            System.out.println(s);
            while(cursor.moveToNext()){
                userAns.add(cursor.getString(cursor.getColumnIndex("name")));
                //userAns.add(cur)
            }

            if(userAns.size() == 0) {
                cursor.close();
                return false;
            }
            for(int i = 0 ; i < Math.min(userAns.size() , ansToBe.size()) ; i++){
                if(!userAns.get(i).equals(ansToBe.get(i))){
                    cursor.close();
                    return false;
                }
            }
            cursor.close();
            return true;
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