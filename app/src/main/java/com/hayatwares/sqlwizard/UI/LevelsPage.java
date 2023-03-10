package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hayatwares.sqlwizard.Network.NetworkChangeListener;
import com.hayatwares.sqlwizard.R;
import com.hayatwares.sqlwizard.Utils.Util;

public class LevelsPage extends AppCompatActivity {

    // NETWORK VALIDATION
    NetworkChangeListener network_change =  new NetworkChangeListener();
    RelativeLayout lockedLayout1 , lockedLayout2 , lockedLayout3 , lockedLayout4;
    LinearLayout unlockedLayout1 , unlockedLayout2 , unlockedLayout3 , unlockedLayout4;
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
        setContentView(R.layout.activity_levels_page);
        getSupportActionBar().hide();
        init();
        //startActivity(new Intent(LevelsPage.this , QuestionSelectionPage.class));
    }
    private void init(){
        lockedLayout1 = findViewById(R.id.lockedLayout1);
        lockedLayout2 = findViewById(R.id.lockedLayout2);
        lockedLayout3 = findViewById(R.id.lockedLayout3);
        lockedLayout4 = findViewById(R.id.lockedLayout4);
        unlockedLayout1 = findViewById(R.id.unlockedLayout1);
        unlockedLayout2 = findViewById(R.id.unlockedLayout2);
        unlockedLayout3 = findViewById(R.id.unlockedLayout3);
        unlockedLayout4 = findViewById(R.id.unlockedLayout4);
        lockedLayout1.setVisibility(View.INVISIBLE);
        unlockedLayout1.setVisibility(View.VISIBLE);
        if(Util.Global_Main_Value < 2){
            lockedLayout2.setVisibility(View.VISIBLE);
            unlockedLayout2.setVisibility(View.INVISIBLE);
        }else{
            lockedLayout2.setVisibility(View.INVISIBLE);
            unlockedLayout2.setVisibility(View.VISIBLE);
        }
        if(Util.Global_Main_Value < 3){
            lockedLayout3.setVisibility(View.VISIBLE);
            unlockedLayout3.setVisibility(View.INVISIBLE);
        }else{
            lockedLayout3.setVisibility(View.INVISIBLE);
            unlockedLayout3.setVisibility(View.VISIBLE);
        }
        if(Util.Global_Main_Value < 4){
            lockedLayout4.setVisibility(View.VISIBLE);
            unlockedLayout4.setVisibility(View.INVISIBLE);
        }else{
            lockedLayout4.setVisibility(View.INVISIBLE);
            unlockedLayout4.setVisibility(View.VISIBLE);
        }
        unlockedLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LevelsPage.this , QuestionSelectionPage.class));
            }
        });
        unlockedLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.Global_Main_Value >= 2){

                    startActivity(new Intent(LevelsPage.this , QuestionSelectionPage.class));
                }
            }
        });
        unlockedLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.Global_Main_Value >= 3){

                    startActivity(new Intent(LevelsPage.this , QuestionSelectionPage.class));
                }
            }
        });
        unlockedLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.Global_Main_Value >= 4){

                    startActivity(new Intent(LevelsPage.this , QuestionSelectionPage.class));
                }
            }
        });


    }
}