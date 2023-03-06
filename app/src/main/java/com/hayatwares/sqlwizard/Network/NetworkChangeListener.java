package com.hayatwares.sqlwizard.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hayatwares.sqlwizard.Utils.Util;

public class NetworkChangeListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Util.isConnected(context)) {
            Log.e("mynet", "internet is OFF");



        }
        else {
            Log.e("mynet", "internet is ON");
            // UPDATE SHARED PREFERENCES


        }
    }
}
