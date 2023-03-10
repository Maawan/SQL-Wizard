package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hayatwares.sqlwizard.Adapters.GridAdapter;
import com.hayatwares.sqlwizard.Interfaces.LockedButtonPushed;
import com.hayatwares.sqlwizard.R;
import com.hayatwares.sqlwizard.Utils.Util;

public class QuestionSelectionPage extends AppCompatActivity implements LockedButtonPushed {
    RecyclerView recyclerView;
    int curLevel = -1;
    int totalQuestions = -1;
    int unlockedQuestions = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_selection_page);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        curLevel = intent.getIntExtra("level" , 0);
        //Toast.makeText(this, "curlevel -> " + curLevel , Toast.LENGTH_SHORT).show();
        if((int)Util.Global_Main_Value > curLevel){
            totalQuestions = Util.getTotalQuestionCount(curLevel);
            unlockedQuestions = Util.getTotalQuestionCount(curLevel);
        }else if((int)Util.Global_Main_Value == curLevel){
            totalQuestions = Util.getTotalQuestionCount(curLevel);
            String s = String.valueOf(Util.Global_Main_Value);
            if(s.length() > 2){
                int tempLevels = Integer.parseInt(s.substring(2));
                //Toast.makeText(this, "ll "+ tempLevels, Toast.LENGTH_SHORT).show();
                if(tempLevels > totalQuestions){
                    tempLevels = totalQuestions;
                }
                unlockedQuestions = tempLevels;
            }
        }
        recyclerView = findViewById(R.id.recycler);
        GridAdapter adapter = new GridAdapter(totalQuestions , unlockedQuestions,this , this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void pushed() {
        Util.displayLockedDialog(this );
    }
}