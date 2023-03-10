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

import com.hayatwares.sqlwizard.R;

public class Util {
    public static float Global_Main_Value = 0.23f;

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
            return 34;
        }else if(level == 1){
            return 13;
        }else if(level == 2){
            return 43;
        }else if(level == 3) return 8;
        return -1;
    }
    public static void displayLockedDialog(Activity contex){
        AlertDialog.Builder builder = new AlertDialog.Builder(contex);
        ViewGroup viewGroup = contex.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.locked_question_layout, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
