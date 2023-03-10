package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import com.hayatwares.sqlwizard.Database.MyDbHandler;
import com.hayatwares.sqlwizard.Network.NetworkChangeListener;
import com.hayatwares.sqlwizard.R;

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
        MyDbHandler db = new MyDbHandler(MainActivity.this);
        startActivity(new Intent(MainActivity.this, LevelsPage.class));
    }
}