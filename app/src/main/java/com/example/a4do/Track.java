package com.example.a4do;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class Track extends AppCompatActivity {
    TextView numLivraison,LastEvent ;
    private OkHttpClient okHttpClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        numLivraison = findViewById(R.id.numLivraison);
        LastEvent= findViewById(R.id.LastEvent);
        Intent intent = getIntent();
        String name = intent.getStringExtra("nroLivraison");
        numLivraison.setText(name);
        this.okHttpClient = new OkHttpClient();

        get(name);
    }
    public void get(String nroLivraison){

        //get Request
        Request myGetRequest = new Request.Builder()
                .url("https://api-eu.dhl.com/track/shipments?trackingNumber=00340434292135100094")
                .get()
                .addHeader("x-rapidapi-host", "apidojo-17track-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "8fa6e9cbd0msh0f6d64f2e92480dp122188jsn2ee48f6b6699")
                .build();

        okHttpClient.newCall(myGetRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                //le retour est effectué dans un thread différent
                final String text = response.body().string();
                final int statusCode = response.code();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LastEvent.setText(text);
                    }
                });
            }
        });
    }
}