package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hayatwares.sqlwizard.Network.NetworkChangeListener;
import com.hayatwares.sqlwizard.R;
import com.hayatwares.sqlwizard.Utils.Util;

public class LevelsPage extends AppCompatActivity {

    // NETWORK VALIDATION
    Activity activity;
    NetworkChangeListener network_change =  new NetworkChangeListener();
    RelativeLayout lockedLayout1 , lockedLayout2 , lockedLayout3 , lockedLayout4;
    LinearLayout unlockedLayout1 , unlockedLayout2 , unlockedLayout3 , unlockedLayout4;
    CardView card1 , card2 , card3 , card4;
    View view;
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
        activity = this;
        //startActivity(new Intent(LevelsPage.this , QuestionSelectionPage.class));

        // checking
        // SharedPreferences sharedPreferences = getSharedPreferences("connection", Context.MODE_PRIVATE);
//        int defaultValue = 0; // Default value to be returned if the key doesn't exist
//        String key = "status"; // The key used to store the integer value
//        int retrievedValue = sharedPreferences.getInt(key, defaultValue);
//        String msg = " " + retrievedValue;
//        Log.e("status",msg);
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
        card1 = findViewById(R.id.level1);
        card2 = findViewById(R.id.level2);
        card3 = findViewById(R.id.level3);
        card4 = findViewById(R.id.level4);
        lockedLayout1.setVisibility(View.INVISIBLE);
        unlockedLayout1.setVisibility(View.VISIBLE);
        if(Util.Global_Main_Value < 1){
            lockedLayout2.setVisibility(View.VISIBLE);
            unlockedLayout2.setVisibility(View.INVISIBLE);
        }else{
            lockedLayout2.setVisibility(View.INVISIBLE);
            unlockedLayout2.setVisibility(View.VISIBLE);
        }
        if(Util.Global_Main_Value < 2){
            lockedLayout3.setVisibility(View.VISIBLE);
            unlockedLayout3.setVisibility(View.INVISIBLE);
        }else{
            lockedLayout3.setVisibility(View.INVISIBLE);
            unlockedLayout3.setVisibility(View.VISIBLE);
        }
        if(Util.Global_Main_Value < 3){
            lockedLayout4.setVisibility(View.VISIBLE);
            unlockedLayout4.setVisibility(View.INVISIBLE);
        }else{
            lockedLayout4.setVisibility(View.INVISIBLE);
            unlockedLayout4.setVisibility(View.VISIBLE);
        }
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LevelsPage.this , QuestionSelectionPage.class).putExtra("level" , 0));
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.Global_Main_Value >= 1){

                    startActivity(new Intent(LevelsPage.this , QuestionSelectionPage.class).putExtra("level" , 1));
                }else{
                    Util.displayLockedDialog(activity );
                }
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.Global_Main_Value >= 2){

                    startActivity(new Intent(LevelsPage.this , QuestionSelectionPage.class).putExtra("level" , 2));
                }else{
                    Util.displayLockedDialog(activity );
                }
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.Global_Main_Value >= 3){

                    startActivity(new Intent(LevelsPage.this , QuestionSelectionPage.class).putExtra("level" , 3));
                }else{
                    Util.displayLockedDialog(activity );
                }
            }
        });


    }
}