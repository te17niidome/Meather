package com.example.blucode.meather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClient = new OkHttpClient();
    }
}