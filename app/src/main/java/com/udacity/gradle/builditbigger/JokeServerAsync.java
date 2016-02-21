package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.tyagiabhinav.jokeserver.myApi.MyApi;

import java.io.IOException;

/**
 * Created by abhinavtyagi on 20/02/16.
 */
public class JokeServerAsync extends AsyncTask<JokeReceiver, Void, String> {
    private static MyApi myApiService = null;
    private boolean showProgressDialog = false;
    private JokeReceiver jokeReceiver;
    private Context context;
    private ProgressDialog progressDialog;

    public JokeServerAsync(Context context, boolean showProgressDialog){
        this.context = context;
        this.showProgressDialog = showProgressDialog;
        if(showProgressDialog) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please Wait");
        }
    }

    public JokeServerAsync(){
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(showProgressDialog && progressDialog!=null) {
            progressDialog.show();
        }
    }

    @Override
    protected String doInBackground(JokeReceiver... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        jokeReceiver = params[0];

        try {
            return myApiService.showMyJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        jokeReceiver.onReceived(result);
        if(showProgressDialog && progressDialog!=null) {
            progressDialog.dismiss();
        }
    }
}
