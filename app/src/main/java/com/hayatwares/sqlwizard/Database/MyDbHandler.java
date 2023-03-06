package com.hayatwares.sqlwizard.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context)
    {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //CREATE TABLE QUERY
        String create = "CREATE TABLE IF NOT EXISTS " + Params.TABLE_NAME + "(" + Params.KEY_Level_ID + " INTEGER PRIMARY KEY,"
                + Params.KEY_Level_QUESTION1 + " TEXT, " + Params.KEY_Level_QUESTION2 + " TEXT, " + Params.KEY_Level_QUESTION3
                + " TEXT, " + Params.KEY_Level_QUESTION4 + " TEXT, " + Params.KEY_Level_QUESTION5 + " TEXT, " + Params.KEY_CODE
                + " TEXT, " + Params.KEY_LEVEL_HINT1 + " TEXT, " + Params.KEY_LEVEL_HINT2 + " TEXT, " + Params.KEY_LEVEL_HINT3
                + " TEXT, " + Params.KEY_LEVEL_HINT4 + " TEXT, " + Params.KEY_LEVEL_HINT5 + " TEXT, " + Params.KEY_LEVEL_ANS1
                + " TEXT, " + Params.KEY_LEVEL_ANS2 + " TEXT, " + Params.KEY_LEVEL_ANS3 + " TEXT, " + Params.KEY_LEVEL_ANS4
                + " TEXT, " + Params.KEY_LEVEL_ANS5 + " TEXT, " + Params.KEY_NAME + " TEXT" + ")";

        //RUN QUERY
        db.execSQL( create );
    }

    public void addLevel(Entry entry)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // CREATING ATTRIBUTES
        values.put(Params.KEY_Level_ID,entry.getId());
        values.put(Params.KEY_Level_QUESTION1,entry.getQuest1());
        values.put(Params.KEY_Level_QUESTION2,entry.getQuest2());
        values.put(Params.KEY_Level_QUESTION3,entry.getQuest3());
        values.put(Params.KEY_Level_QUESTION4,entry.getQuest4());
        values.put(Params.KEY_Level_QUESTION5,entry.getQuest5());
        values.put(Params.KEY_CODE,entry.getCode());
        values.put(Params.KEY_LEVEL_HINT1,entry.getHint1());
        values.put(Params.KEY_LEVEL_HINT2,entry.getHint2());
        values.put(Params.KEY_LEVEL_HINT3,entry.getHint3());
        values.put(Params.KEY_LEVEL_HINT4,entry.getHint4());
        values.put(Params.KEY_LEVEL_HINT5,entry.getHint5());
        values.put(Params.KEY_LEVEL_ANS1,entry.getAns1());
        values.put(Params.KEY_LEVEL_ANS2,entry.getAns2());
        values.put(Params.KEY_LEVEL_ANS3,entry.getAns3());
        values.put(Params.KEY_LEVEL_ANS4,entry.getAns4());
        values.put(Params.KEY_LEVEL_ANS5,entry.getAns5());
        values.put(Params.KEY_NAME,entry.getName());

        // INSERTING IN THE TABLE
        db.insert(Params.TABLE_NAME, null, values);
        db.close();
    }

    public Entry getLevel(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        // RAW QUERY
        String select = "SELECT * FROM " + Params.TABLE_NAME + " WHERE ID = " + id ;
        Cursor cursor = db.rawQuery(select,null);

        // EXTRACTING DATA

        Entry row = new Entry();
        if(cursor.moveToFirst())
        {
            row.setId(Integer.parseInt(cursor.getString(0)));
            row.setQuest1(cursor.getString(1));
            row.setQuest2(cursor.getString(2));
            row.setQuest3(cursor.getString(3));
            row.setQuest4(cursor.getString(4));
            row.setQuest5(cursor.getString(5));
            row.setCode(cursor.getString(6));
            row.setHint1(cursor.getString(7));
            row.setHint2(cursor.getString(8));
            row.setHint3(cursor.getString(9));
            row.setHint4(cursor.getString(10));
            row.setHint5(cursor.getString(11));
            row.setAns1(cursor.getString(12));
            row.setAns2(cursor.getString(13));
            row.setAns3(cursor.getString(14));
            row.setAns4(cursor.getString(15));
            row.setAns5(cursor.getString(16));
            row.setName(cursor.getString(17));
        }
        db.close();
        return row;
    }

    public void deleteLevel(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_Level_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int modifyLevel(int id, Entry entry)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // UPDATE VALUES or ATTRIBUTES
        values.put(Params.KEY_Level_ID,id);
        values.put(Params.KEY_Level_QUESTION1,entry.getQuest1());
        values.put(Params.KEY_Level_QUESTION2,entry.getQuest2());
        values.put(Params.KEY_Level_QUESTION3,entry.getQuest3());
        values.put(Params.KEY_Level_QUESTION4,entry.getQuest4());
        values.put(Params.KEY_Level_QUESTION5,entry.getQuest5());
        values.put(Params.KEY_CODE,entry.getCode());
        values.put(Params.KEY_LEVEL_HINT1,entry.getHint1());
        values.put(Params.KEY_LEVEL_HINT2,entry.getHint2());
        values.put(Params.KEY_LEVEL_HINT3,entry.getHint3());
        values.put(Params.KEY_LEVEL_HINT4,entry.getHint4());
        values.put(Params.KEY_LEVEL_HINT5,entry.getHint5());
        values.put(Params.KEY_LEVEL_ANS1,entry.getAns1());
        values.put(Params.KEY_LEVEL_ANS2,entry.getAns2());
        values.put(Params.KEY_LEVEL_ANS3,entry.getAns3());
        values.put(Params.KEY_LEVEL_ANS4,entry.getAns4());
        values.put(Params.KEY_LEVEL_ANS5,entry.getAns5());
        values.put(Params.KEY_NAME,entry.getName());

        // RETURNS NO OF AFFECTED ROWS
        return db.update(Params.TABLE_NAME, values, Params.KEY_Level_ID + "=?", new String[]{String.valueOf(id)});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}