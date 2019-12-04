package com.vastmoths.beerapp.broadcast;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DisturbReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("DUPAAAAAAAAAAAAAAAAAAAAA");


        if(intent.getAction().equals(NotificationManager.ACTION_INTERRUPTION_FILTER_CHANGED)) {

            System.out.println("Dupa broadcast");

//            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            if(mNotificationManager.getCurrentInterruptionFilter() == NotificationManager.INTERRUPTION_FILTER_ALARMS) {
//                //do your stuff
//            } else if (mNotificationManager.getCurrentInterruptionFilter() == NotificationManager.INTERRUPTION_FILTER_NON) {
//                //....
//            } else if (mNotificationManager.getCurrentInterruptionFilter() == NotificationManager.INTERRUPTION_FILTER_ALL) {
//
//            } else if (mNotificationManager.getCurrentInterruptionFilter() == NotificationManager.INTERRUPTION_FILTER_PRIORUTY) {
//
//            } else if (mNotificationManager.getCurrentInterruptionFilter() == NotificationManager.INTERRUPTION_FILTER_UNKNOWN) {
//
//            }
        }

    }
}
