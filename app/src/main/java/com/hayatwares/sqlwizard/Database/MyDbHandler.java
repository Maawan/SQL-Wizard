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
        c.close();
        return tableName;
    }



    @SuppressLint("Range")
    public boolean checkAndValidateAnswer(String query , int level , int questionNo){
        SQLiteDatabase db = this.getWritableDatabase();
        clearDatabase(db);
        if(level == 0 && questionNo == 0){
            db.execSQL("CREATE TABLE Abc(id INTEGER, name TEXT)");
            db.execSQL("INSERT INTO Abc values(1 , 'Mawan');");
            db.execSQL("INSERT INTO Abc values(2 , 'Karan');");
            db.execSQL("INSERT INTO Abc values(3 , 'Bsdk hai');");
            db.execSQL("INSERT INTO Abc values(4 , 'Lawda bhi hai');");

            Cursor cursor = null;
            try{

                cursor = db.rawQuery(query , null , null);
            }catch (Exception e){
                if(incorrectDialog != null){
                    incorrectDialog.displayDialog("Query is not Properly Written" , "Lawda Sur");
                }

                return false;
            }
            if(cursor == null){
                if(incorrectDialog != null) {
                    incorrectDialog.displayDialog("", "");
                }

                return false;
            }
            StringBuilder userAnsBuilder = new StringBuilder();
            String ansToBe = "[ 1 Mawan ][ 2 Karan ][ 3 Bsdk hai ][ 4 Lawda bhi hai ]";
            int numberOfColums = 0;
            //Cursor tempCursor = cursor;
            ArrayList<String> columnsName = new ArrayList<>();
            try{
                while(true){
                    String curColumnName = cursor.getColumnName(numberOfColums);
                    numberOfColums++;
                    columnsName.add(curColumnName);
                }
            }catch (Exception e){

            }
            System.out.print(numberOfColums + " ");
            while(cursor.moveToNext()){
                StringBuilder curRow = new StringBuilder();
                curRow.append("[ ");
                for(String colName : columnsName){
                    curRow.append(cursor.getString(cursor.getColumnIndex(colName)) + " ");
                }
                curRow.append("]");
                userAnsBuilder.append(new String(curRow));
            }
            cursor.close();
            String userAns = new String(userAnsBuilder);
            System.out.print(userAns + " ");
            if(!userAns.equals(ansToBe)){
                incorrectDialog.displayDialog(userAns , ansToBe);
                cursor.close();
                return false;
            }
            cursor.close();
            return true;
        }else if(level == 0 && questionNo == 1){
            db.execSQL("CREATE TABLE Employee(EmpId INTEGER PRIMARY KEY, EmpName TEXT , Department TEXT , Salary INTEGER)");
            db.execSQL("INSERT INTO Employee values(1 , 'Karan Purohit' , 'Gaand Chatai' , 1500);");
            db.execSQL("INSERT INTO Employee values(2 , 'Shubham' , 'Bakchodi' , 2500);");
            db.execSQL("INSERT INTO Employee values(3 , 'Mohd Mawan' , 'Boring Insaan' , 2000);");
            db.execSQL("INSERT INTO Employee values(4 , 'Faraz Bsdk' , 'Chill Insaan hai Bsdk' , 10000);");
            Cursor cursor = null;
            try{
                //query = "SELECT EmpName FROM Employee where Salary > 2000";
                cursor = db.rawQuery(query , null , null);
            }catch (Exception e){
                if(incorrectDialog != null){
                    incorrectDialog.displayDialog("Query is not Properly Written" , "Lawda Sur");
                }

                return false;
            }
            if(cursor == null){
                if(incorrectDialog != null) {
                    incorrectDialog.displayDialog("", "");
                }

                return false;
            }
            StringBuilder userAnsBuilder = new StringBuilder();
            String ansToBe = "[ Shubham ][ Faraz Bsdk ]";
            int numberOfColums = 0;
            //Cursor tempCursor = cursor;
            ArrayList<String> columnsName = new ArrayList<>();
            try{
                while(true){
                    String curColumnName = cursor.getColumnName(numberOfColums);
                    numberOfColums++;
                    columnsName.add(curColumnName);
                }
            }catch (Exception e){

            }
            System.out.print(numberOfColums + " ");
            while(cursor.moveToNext()){
                StringBuilder curRow = new StringBuilder();
                curRow.append("[ ");
                for(String colName : columnsName){
                    curRow.append(cursor.getString(cursor.getColumnIndex(colName)) + " ");
                }
                curRow.append("]");
                userAnsBuilder.append(new String(curRow));
            }
            cursor.close();
            String userAns = new String(userAnsBuilder);
            System.out.print(userAns + " ");
            Log.e("Okkkk" , userAns);
            if(!userAns.equals(ansToBe)){
                incorrectDialog.displayDialog(userAns , ansToBe);
                cursor.close();
                return false;
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