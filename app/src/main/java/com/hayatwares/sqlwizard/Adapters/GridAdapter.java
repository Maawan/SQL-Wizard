package com.hayatwares.sqlwizard.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hayatwares.sqlwizard.Interfaces.LockedButtonPushed;
import com.hayatwares.sqlwizard.R;
import com.hayatwares.sqlwizard.UI.MainActivity;
import com.hayatwares.sqlwizard.UI.QuestionPage;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridHolder> {
    float questions;
    int totalQuestions;
    Context context;
    LockedButtonPushed pushedInterface;
    public GridAdapter(int totalQuestion , float unlockedQuestions , Context context , LockedButtonPushed pushedInterface){
        this.totalQuestions = totalQuestion;
        this.pushedInterface = pushedInterface;
        this.questions = unlockedQuestions;
        this.context = context;
    }

    @NonNull
    @Override
    public GridHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_selection_layout, parent, false);
        return new GridHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridHolder holder, @SuppressLint("RecyclerView") int position) {
        String quesNo = String.valueOf(position + 1);
        holder.questionNo.setText(quesNo);
        if(position+1 > questions){
            holder.lockLayout.setVisibility(View.VISIBLE);
            holder.questionNo.setVisibility(View.INVISIBLE);
        }else{
            holder.lockLayout.setVisibility(View.INVISIBLE);
            holder.questionNo.setVisibility(View.VISIBLE);
        }
        holder.wholeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position+1 > questions){
                    pushedInterface.pushed();
                }else{
                    Intent intent = new Intent(context , QuestionPage.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return totalQuestions;
    }

    static class GridHolder extends RecyclerView.ViewHolder {
        private TextView questionNo;
        private RelativeLayout lockLayout;
        private CardView wholeLayout;
        public GridHolder(@NonNull View itemView) {
            super(itemView);
            questionNo = itemView.findViewById(R.id.number);
            lockLayout = itemView.findViewById(R.id.lockLayout);
            wholeLayout = itemView.findViewById(R.id.layoutId);
        }
    }
}
