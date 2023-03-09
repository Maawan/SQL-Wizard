package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.view.View;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import com.hayatwares.sqlwizard.R;
import com.hayatwares.sqlwizard.Utils.Autofill;
import com.hayatwares.sqlwizard.Utils.SpaceTokenizer;

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








        // AUTOCOMPLETE IMPLEMENTATION
        MultiAutoCompleteTextView editText = findViewById(R.id.autoComplete);
        // SET THE ADAPTER TO AUTO SUGGEST COMPLETION
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.suggestion_text_layout, Autofill.sqliteKeywords);
        editText.setAdapter(adapter);
        // SET THE TOKENIZER TO BREAK THE INPUT INTO TOKENS
        editText.setTokenizer(new SpaceTokenizer());
        // SET THRESHOLD
        editText.setThreshold(2);

        // KEYBOARD MANAGEMENT
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER){
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                return false;
            }
        });

        // KEYBOARD HIDE
        View rootView = findViewById(R.id.check);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                Log.e("background","touched");
                return false;
            }
        });

    }
}