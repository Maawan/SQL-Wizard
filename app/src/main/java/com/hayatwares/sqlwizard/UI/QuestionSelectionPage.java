package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hayatwares.sqlwizard.Adapters.GridAdapter;
import com.hayatwares.sqlwizard.Interfaces.LockedButtonPushed;
import com.hayatwares.sqlwizard.Network.NetworkChangeListener;
import com.hayatwares.sqlwizard.R;
import com.hayatwares.sqlwizard.Utils.Util;

public class QuestionSelectionPage extends AppCompatActivity implements LockedButtonPushed {
    RecyclerView recyclerView;
    int curLevel = -1;
    int totalQuestions = -1;
    int unlockedQuestions = -1;
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
        setContentView(R.layout.activity_question_selection_page);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        curLevel = intent.getIntExtra("level" , 0);
        Toast.makeText(this, "curlevel -> " + curLevel , Toast.LENGTH_SHORT).show();
        if((int)Util.Global_Main_Value > curLevel){
            totalQuestions = Util.getTotalQuestionCount(curLevel);
            unlockedQuestions = Util.getTotalQuestionCount(curLevel);
        }else if((int)Util.Global_Main_Value == curLevel){
            totalQuestions = Util.getTotalQuestionCount(curLevel);
            String s = String.valueOf(Util.Global_Main_Value);
            Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
            if(s.length() > 2){
                int tempLevels = Integer.parseInt(s.substring(2));
                tempLevels = tempLevels/2;
                Toast.makeText(this, "ll "+ tempLevels, Toast.LENGTH_SHORT).show();
                if(tempLevels > totalQuestions){
                    tempLevels = totalQuestions;
                }
                unlockedQuestions = tempLevels;
            }
        }
        recyclerView = findViewById(R.id.recycler);
        Log.e("check",totalQuestions+" "+unlockedQuestions);
        GridAdapter adapter = new GridAdapter(curLevel,totalQuestions , unlockedQuestions,this , this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void pushed() {
        Util.displayLockedDialog(this );
    }
}