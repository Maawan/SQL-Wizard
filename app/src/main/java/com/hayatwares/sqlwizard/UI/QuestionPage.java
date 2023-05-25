package com.hayatwares.sqlwizard.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.widget.Toast;

import com.hayatwares.sqlwizard.Database.MyDbHandler;
import com.hayatwares.sqlwizard.Interfaces.DisplayIncorrectDialog;
import com.hayatwares.sqlwizard.Models.Question;
import com.hayatwares.sqlwizard.Network.NetworkChangeListener;
import com.hayatwares.sqlwizard.R;
import com.hayatwares.sqlwizard.Utils.Autofill;
import com.hayatwares.sqlwizard.Utils.SpaceTokenizer;
import com.hayatwares.sqlwizard.Utils.Util;

import org.w3c.dom.Text;

public class QuestionPage extends AppCompatActivity implements DisplayIncorrectDialog {
    TextView question;
    int curLevel = -1;
    int curQuestion = -1;

    Question curQuestionObject;
    TextView levelView,problemView;
    ImageView questionImage;
    private static final String A = "Level ";
    private static final String B = "Problem ";
    private RelativeLayout submitQueryBtn;
    private MyDbHandler dbHandler;
    private MultiAutoCompleteTextView queryEditText;

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
        setContentView(R.layout.activity_question_page);
        getSupportActionBar().hide();
        question = findViewById(R.id.questionTextView);
        Intent intent = getIntent();
        curLevel = intent.getIntExtra("level" , 0);
        queryEditText = findViewById(R.id.autoComplete);
        curQuestion = intent.getIntExtra("questionNo" , 0);
        submitQueryBtn = findViewById(R.id.submitQueryBtn);
        Toast.makeText(this, curLevel + " " + curQuestion, Toast.LENGTH_SHORT).show();

        curQuestionObject = new Question(curLevel , curQuestion ,this );


        dbHandler = new MyDbHandler(QuestionPage.this , this);
        levelView = findViewById(R.id.levelTextView);
        problemView = findViewById(R.id.problemTextView);

        levelView.setText(A + (curLevel + 1));
        problemView.setText(B + (curQuestion+1));
        question.setText(curQuestionObject.getQuestionStatement());
        questionImage = findViewById(R.id.questionImage);
        questionImage.setImageResource(getResources().getIdentifier(curQuestionObject.getImagePath(), "drawable", getPackageName()));
        //Toast.makeText(this, "fioermnf " + dbHandler.checkAndValidateAnswer("SELECT name FROM Abc;",curLevel , curQuestion) + " jkewnf", Toast.LENGTH_SHORT).show();
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
                return false;
            }
        });

        submitQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("submmit","submmit");
                String q = queryEditText.getText().toString();
                if(!q.equals("")){



                    if(dbHandler.checkAndValidateAnswer(q,curLevel , curQuestion))
                    {
                        Log.e("ji","ji");
                        displayCorrectAnsDialog();
                    }


                }else{
                    displayDialog("Query is Empty..." , "Query is Empyt");
                    System.out.println("OOps ! Query is Empty ");

                }

            }
        });

    }

    @Override
    public void displayDialog(String userAns, String expectedAns) {
        Util.displayIncorrectAnsDialog(QuestionPage.this , userAns , expectedAns);
    }
    public void displayCorrectAnsDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = this.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_correct_ans, viewGroup, false);
        Button nextQuestion = (Button) dialogView.findViewById(R.id.nextQuestionBtn);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // important ChodBhangda *DO NOT TOUCH*
        // if equal increment it.
        SharedPreferences getShared=getSharedPreferences("Global",MODE_PRIVATE);
        String values = getShared.getString("Value","0.2f");
        float value = Float.valueOf(values);
        int level = (int) ((value - (int)value) * 10);
        Log.e("outer",level +" "+ value);
        if(curLevel==(int)value && curQuestion==((level/2)-1))
        {

            Toast.makeText(this, "in here", Toast.LENGTH_SHORT).show();
            value = value + 0.2f;
            Util.Global_Main_Value = value;
            String Value = Float.toString(value);
            Log.e("updated","imcremeting level passed");
            getShared = getSharedPreferences("Global", MODE_PRIVATE);
            SharedPreferences.Editor editor = getShared.edit();
            editor.putString("Value",Value);
            editor.apply();

            // just doubel ckeck no need
            getShared=getSharedPreferences("Global",MODE_PRIVATE);
            values = getShared.getString("Value","0.2f");
            value = Float.valueOf(values);
            Log.e("check",values);
        }
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if(Util.Global_Main_Value==(int)Util.Global_Main_Value && curLevel+1==(int)Util.Global_Main_Value)
                {
                    // yeh wala intent tab fire hoga jab level ke sare sub-questions complete ho jayenge.
                    if(Util.Global_Main_Value==curLevel+1)
                    {
                        SharedPreferences getShared=getSharedPreferences("Global",MODE_PRIVATE);
                        String values = getShared.getString("Value","0.2f");
                        float value = Float.valueOf(values);
                        value = value + 0.2f;
                        Util.Global_Main_Value = value;
                        String Value = Float.toString(value);
                        Log.e("updated","imcremeting level passed");
                        getShared = getSharedPreferences("Global", MODE_PRIVATE);
                        SharedPreferences.Editor editor = getShared.edit();
                        editor.putString("Value",Value);
                        editor.apply();
                        Log.e("next Check",Value);
                    }
                    Intent intent = new Intent(QuestionPage.this, LevelsPage.class);
                    startActivity(intent);
                }
                else
                {
                    // ni tho yeh question selection ka intent.
                    Intent intent = new Intent(QuestionPage.this, QuestionSelectionPage.class);
                    startActivity(intent);
                }

            }
        });
    }
}