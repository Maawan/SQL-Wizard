package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hayatwares.sqlwizard.Adapters.GridAdapter;
import com.hayatwares.sqlwizard.Interfaces.LockedButtonPushed;
import com.hayatwares.sqlwizard.R;

public class QuestionSelectionPage extends AppCompatActivity implements LockedButtonPushed {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_selection_page);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recycler);
        GridAdapter adapter = new GridAdapter(20 , 5,this , this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void pushed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = this.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.locked_question_layout, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}