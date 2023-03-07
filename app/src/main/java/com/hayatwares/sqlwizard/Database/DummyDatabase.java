package com.hayatwares.sqlwizard.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DummyDatabase extends SQLiteOpenHelper {
    private static final String databaseName = "W";
    private Context context;
    private static final int version = 1;
    public DummyDatabase(Context context){
        super(context , databaseName , null , version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
