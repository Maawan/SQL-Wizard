package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hayatwares.sqlwizard.Database.MyDbHandler;
import com.hayatwares.sqlwizard.Database.Params;
import com.hayatwares.sqlwizard.Network.NetworkChangeListener;
import com.hayatwares.sqlwizard.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // NETWORK VALIDATION
    NetworkChangeListener network_change =  new NetworkChangeListener();
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(network_change, filter);
        super.onStart();
    }
    @Override
    protected void onStop()
    {
        unregisterReceiver(network_change);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CREATE DEFAULT DATABASE
        MyDbHandler db = new MyDbHandler(MainActivity.this);

        // STRING TABLE DESCRIPTION CHECK
        String create = "CREATE TABLE IF NOT EXISTS " + Params.TABLE_NAME + "(" + Params.KEY_Level_ID + " INTEGER PRIMARY KEY,"
                + Params.KEY_Level_QUESTION1 + " TEXT, " + Params.KEY_Level_QUESTION2 + " TEXT, " + Params.KEY_Level_QUESTION3
                + " TEXT, " + Params.KEY_Level_QUESTION4 + " TEXT, " + Params.KEY_Level_QUESTION5 + " TEXT, " + Params.KEY_CODE
                + " TEXT, " + Params.KEY_LEVEL_HINT1 + " TEXT, " + Params.KEY_LEVEL_HINT2 + " TEXT, " + Params.KEY_LEVEL_HINT3
                + " TEXT, " + Params.KEY_LEVEL_HINT4 + " TEXT, " + Params.KEY_LEVEL_HINT5 + " TEXT, " + Params.KEY_LEVEL_ANS1
                + " TEXT, " + Params.KEY_LEVEL_ANS2 + " TEXT, " + Params.KEY_LEVEL_ANS3 + " TEXT, " + Params.KEY_LEVEL_ANS4
                + " TEXT, " + Params.KEY_LEVEL_ANS5 + " TEXT, " + Params.KEY_NAME + " TEXT, " + Params.KEY_SAMPLE_PICTURE1
                + " TEXT, " + Params.KEY_SAMPLE_PICTURE2 + " TEXT, " + Params.KEY_SAMPLE_PICTURE3 + " TEXT, " + Params.KEY_SAMPLE_PICTURE4
                + " TEXT, " + Params.KEY_SAMPLE_PICTURE5 + " TEXT" + ")";

        Log.e("create",create);

        // POPULATE DEFAULT DATABASE

        // CODE TO CHECK FOR ANY UPDATES (ANNOUNCEMENTS, CONTESTS AND POLICY UPDATE)

        // DISPLAY UPDATES IN A CARD VIEW IF ANY

        // LAUNCH MENU PAGE AUTOMATICALLY
        startActivity(new Intent(MainActivity.this, LevelsPage.class));
    }
}