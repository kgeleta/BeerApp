package com.vastmoths.beerapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {

    private static boolean oldState = false;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED) || oldState == isAirplaneModeOn(context))
            return;
        String message = isAirplaneModeOn(context) ? "You can drink now, your gf won't call you" : "Don't call your ex";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        oldState = isAirplaneModeOn(context);
    }

    private static boolean isAirplaneModeOn(Context context) {

        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

    }

}
