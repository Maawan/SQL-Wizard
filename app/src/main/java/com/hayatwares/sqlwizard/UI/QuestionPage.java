package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.hayatwares.sqlwizard.R;

public class QuestionPage extends AppCompatActivity {
    TextView question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);
        getSupportActionBar().hide();
        question = findViewById(R.id.questionTextView);
        String q = "Ram is a policeman, he is working on a high profile case, \n" +
                "help him to list all the criminal files who were filed after 2017\n"
                +"A Sample of Record is given below. Ram just need names of Criminals";
        question.setText(q);

    }
}