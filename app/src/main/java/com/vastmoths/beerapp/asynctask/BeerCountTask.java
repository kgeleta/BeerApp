package com.vastmoths.beerapp.asynctask;

import android.os.AsyncTask;

public class BeerCountTask extends AsyncTask {
    static int beerCounter = 0;

    @Override
    protected Object doInBackground(Object[] objects) {
//        Context context = Context.getApplicationContext();
//        CharSequence text = "Hello toast!";
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
        System.out.println("Servis");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Servis po 5 sec");

        return getBeerCounter();
    }

    public static String getBeerCounter(){
        return Integer.toString(beerCounter);
    }
}
