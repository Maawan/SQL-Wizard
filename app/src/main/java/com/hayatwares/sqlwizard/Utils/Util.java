package com.hayatwares.sqlwizard.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.hayatwares.sqlwizard.R;

public class Util {
    public static float Global_Main_Value = 0.0f;

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) return true;
                }
            }
        }
        return false;
    }

    public static int getTotalQuestionCount(int level){
        if(level == 0){
            return 46;
        }else if(level == 1){
            return 12;
        }else if(level == 2){
            return 64;
        }else if(level == 3) return 10;
        return -1;
    }
    public static void displayLockedDialog(Activity contex){
        AlertDialog.Builder builder = new AlertDialog.Builder(contex);
        ViewGroup viewGroup = contex.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_locked_question, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public static void displayIncorrectAnsDialog(Activity context , String userAnsStr, String expectedAns){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup viewGroup = context.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_incorrect_ans, viewGroup, false);
        TextView userAns = (TextView) dialogView.findViewById(R.id.userAns);
        userAns.setText(userAnsStr + "");
        TextView expectedAnsView = (TextView) dialogView.findViewById(R.id.expectedAns);
        expectedAnsView.setText(expectedAns);


        CardView closeButton = (CardView) dialogView.findViewById(R.id.closeDialogButton);

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
    private static String getStringFromResources(Context context , String name){
        try{
            int resourceId = context.getResources().getIdentifier(name, "string", context.getPackageName());
            return context.getResources().getString(resourceId);
        }catch (Exception e){
            return "";
        }
    }

    public static String getQuestionStatement(int level , int question , Context context){
        return getStringFromResources(context , "Q_"+level+"_"+question);
    }
    public static String getHint1(int level , int question , Context context){
        return getStringFromResources(context , "H1_"+level+"_"+question);
    }
    public static String getHint2(int level , int question , Context context){
        return getStringFromResources(context , "H2_"+level+"_"+question);
    }public static String getImage(int level , int question , Context context){
        return getStringFromResources(context , "I_"+level+"_"+question);
    }

}
