package com.example.rishabhja.trainingapp;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by rishabh.ja on 11/08/16.
 */
public class NetworkAsyncTask extends AsyncTask<Void, Void, String> {

    private final MainActivity mainActivity;
    private String __url;
    private String request;

    public NetworkAsyncTask(String __url,String request,MainActivity mainActivity){
        this.__url=__url;
        this.request=request;
        this.mainActivity=mainActivity;
    }

    protected void onPreExecute(){
        ImageView imageView = (ImageView) mainActivity.findViewById(R.id.imageView);
        imageView.setVisibility(View.VISIBLE);

        TextView textView=(TextView) mainActivity.findViewById(R.id.textView2);
        textView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(__url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            String urlParameters=request;

            // Send post request
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            //Get response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String _response=response.toString();
            return _response;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String result){
        ImageView imageView = (ImageView) mainActivity.findViewById(R.id.imageView);
        imageView.setVisibility(View.INVISIBLE);

        TextView textView=(TextView) mainActivity.findViewById(R.id.textView2);
        textView.setVisibility(View.VISIBLE);

        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String shorturl=jsonObject.optString("shorturl").toString();
        textView.setText("The shortened url is : " +shorturl);
    }
}
