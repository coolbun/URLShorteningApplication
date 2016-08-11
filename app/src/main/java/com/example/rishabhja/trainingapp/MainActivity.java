package com.example.rishabhja.trainingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void loadingButtonClick(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        NetworkAsyncTask networkRequest = (NetworkAsyncTask) new NetworkAsyncTask("http://www.nindalf.com/shorten", "longurl=" + editText.getText(),this).execute();
    }
}
