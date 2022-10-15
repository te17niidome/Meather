package com.example.blucode.meather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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

    TextView textView;
    Button button01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClient = new OkHttpClient();

        // UIコンポーネント
        textView = findViewById(R.id.text_view);
        button01 = findViewById(R.id.button01);

    }

    private void loadweather(){
        // 接続先
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=32.8&lon=130.7&appid=4f5e1a41f86e7d4b46349a18c76a7c1e";

        //リクエストを作成
        Request request = new Request.Builder().url(url).build();
        Call call = mClient.newCall(request);

//        System.

        // リクエストを非同期実行
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "処理失敗", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                System.out.println(body);

                JSONObject json = null;
                try {
                    json = new JSONObject(body);

                    // *******************************************************
                    JSONArray weatherArray = json.getJSONArray("weather");
                    System.out.println(weatherArray);
                    System.out.println(weatherArray.getJSONObject(0));
                    JSONObject weatherObject = weatherArray.getJSONObject(0);
                    System.out.println(weatherObject.getString("id"));
                    // *******************************************************
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void addWeather(){
//        textView = findViewById(R.id.text_view);
        textView.setText("HELLO HELLO HELLO");
    }

    public void getWeather(View view) {

        loadweather();
//        System.out.println("どんえーん");
        addWeather();

        //TextViewに表示
//        textView.setText("HELLO HELLO HELLO");
    }
}