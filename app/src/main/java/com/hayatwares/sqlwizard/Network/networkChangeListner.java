package com.hayatwares.sqlwizard.Network;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class networkChangeListner extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!common.isConnected(context)) {
            Log.e("mynet", "internet is OFF");
            // UPDATE SHARED PREFERENCES


        }
        else {
            Log.e("mynet", "internet is ON");
            // UPDATE SHARED PREFERENCES


        }
    }
}
