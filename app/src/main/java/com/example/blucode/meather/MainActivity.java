package com.example.blucode.meather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClient = new OkHttpClient();
    }

    private void loadweather(){
        // 接続先
        String url = "https://api.openweathermap.org/data/2.5/weather";

        //リクエストを作成
        Request request = new Request.Builder().url(url).build();
        Call call = mClient.newCall(request);

        // リクエストを非同期実行
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "処理失敗", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();

                JSONObject json = null;
                try {
                    json = new JSONObject(body);
                    // *******************************************************
                    JSONArray colorArray = json.getJSONArray("colorsArray");
                    // *******************************************************
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
}