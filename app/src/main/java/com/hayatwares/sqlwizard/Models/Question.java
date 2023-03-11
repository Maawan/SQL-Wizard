package com.hayatwares.sqlwizard.Models;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.hayatwares.sqlwizard.Database.MyDbHandler;
import com.hayatwares.sqlwizard.Utils.Util;

import java.util.ArrayList;

public class Question {
    private int level;
    private int questionNumber;
    private String questionStatement;
    private String hint1;
    private String hint2;
    private String imagePath;
    private Context context;

    public Question(int level, int questionNumber , Context context) {
        this.level = level;
        this.questionNumber = questionNumber;
        this.context = context;
        this.questionStatement = Util.getQuestionStatement(level , questionNumber , context);
        this.hint1 = Util.getHint1(level , questionNumber , context);
        this.hint2 = Util.getHint2(level , questionNumber , context);
        this.imagePath = Util.getImage(level , questionNumber , context);
    }

    public int getLevel() {
        return level;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionStatement() {
        return questionStatement;
    }

    public String getHint1() {
        return hint1;
    }

    public String getHint2() {
        return hint2;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Context getContext() {
        return context;
    }


}
