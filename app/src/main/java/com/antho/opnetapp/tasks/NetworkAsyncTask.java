package com.antho.opnetapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.antho.opnetapp.utils.MyHttpURLConnection;

import java.lang.ref.WeakReference;

public class NetworkAsyncTask extends AsyncTask<String, Void, String> {

    private static final String TAG = "NetworkAsyncTask";

    public interface Listeners {
        void onPreExecute();
        void doInBackground();
        void onPostExecute(String success);
    }

    private final WeakReference<Listeners> callback;

    public NetworkAsyncTask(Listeners callback) {
        this.callback = new WeakReference<>(callback);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.callback.get().onPreExecute();
        Log.e(TAG, "AsynsTask is strated");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        this.callback.get().onPostExecute(s);
        Log.e(TAG, "AsyncTask is finished");
    }

    @Override
    protected String doInBackground(String... url) {
        this.callback.get().doInBackground();
        Log.e(TAG, "AsyncTask doing some big work...");
        return MyHttpURLConnection.startHttpRequest(url[0]);
    }
}