package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.hayatwares.sqlwizard.Database.MyDbHandler;
import com.hayatwares.sqlwizard.Network.NetworkChangeListener;
import com.hayatwares.sqlwizard.R;
import com.hayatwares.sqlwizard.Utils.Util;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    // NETWORK VALIDATION
    NetworkChangeListener network_change =  new NetworkChangeListener();
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(network_change, filter);
        super.onStart();
    }
    @Override
    protected void onStop() {
        unregisterReceiver(network_change);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CREATE DEFAULT DATABASE
        MyDbHandler db = new MyDbHandler(MainActivity.this , null);

        // SHARED PREFRENCES

        SharedPreferences getShared=getSharedPreferences("Global",MODE_PRIVATE);
        String values = getShared.getString("Value","0.2f");
        float value = Float.valueOf(values);
        Log.e("dflt"," "+value);
        if(value==0.2f)
        {
            Log.e("insert","here inserted");
            getShared = getSharedPreferences("Global", MODE_PRIVATE);
            SharedPreferences.Editor editor = getShared.edit();
            editor.putString("Value","0.2f");
            editor.apply();
        }
        else
        {
            Util.Global_Main_Value = value;
        }

        startActivity(new Intent(MainActivity.this, LevelsPage.class));

    }
}