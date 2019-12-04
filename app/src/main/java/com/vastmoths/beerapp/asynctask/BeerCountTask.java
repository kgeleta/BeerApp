package com.vastmoths.beerapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class BeerCountTask extends AsyncTask {
    private Context context;

    public BeerCountTask(Context context) {
        this.context = context;
    }

    @Override
    protected Integer doInBackground(Object... objects) {
        publishProgress(1);

        int timeCount = 0;
        while (timeCount < 100) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeCount += 1;
        }

        return 1;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        Toast.makeText(context,"Drinking starts!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Toast.makeText(context,"Drinking ended.", Toast.LENGTH_LONG).show();
    }
}
