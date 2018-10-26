package com.example.copao.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.net.URL;

public class HelloService extends Service {

    private String serviceTag = "HELLO_SERVICE";
    private String asyncTag = "ASYNC_TAG";
    private boolean isRunning  = false;



    public HelloService() {

    }

    @Override
    public void onCreate() {
        Log.i(serviceTag, "Service onCreate");

        isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(serviceTag, "Service onStartCommand");

        //Creating new thread for my service
        //Always write your long running tasks in a separate thread, to avoid ANR
        new Thread(new Runnable() {
            @Override
            public void run() {

                DonwloaderAsync donwloaderTask = new DonwloaderAsync();
                donwloaderTask.execute();

                //Stop service once it finishes its task
                stopSelf();
            }
        }).start();

        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(serviceTag, "Service onBind");
        return null;
    }

    public class DonwloaderAsync extends AsyncTask <URL, Integer, String>{

        @Override
        protected String doInBackground(URL... urls) {
            Log.d(asyncTag, "doInBackground");
            return null;
        }

        @Override
        protected void onPreExecute() {
            Log.d(asyncTag, "onPreExecute");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(asyncTag, "onPostExecute");
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(asyncTag, "onProgressUpdate");
            Log.d(asyncTag, values.toString());
            super.onProgressUpdate(values);
        }
    }


    @Override
    public void onDestroy() {

        isRunning = false;

        Log.i(serviceTag, "Service onDestroy");
    }
}
