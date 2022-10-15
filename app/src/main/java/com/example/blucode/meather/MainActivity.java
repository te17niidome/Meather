package com.example.blucode.meather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
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

public class MainActivity extends AppCompatActivity implements  LocationListener {

    private OkHttpClient mClient;

    TextView textView;
    Button button01;

    public static double Lati;
    public static double Long;
    public static int cnt = 0;
    private LocationManager manager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mClient = new OkHttpClient();

        // UIコンポーネント
        textView = findViewById(R.id.text_view);
        button01 = findViewById(R.id.button01);
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);
        new Handler().postDelayed(new Runnable() {
            // Runnable型のインスタンス化と定義
            @Override
            public void run() {

                // 遅らせて実行したい処理

            }
        }, 3000);
    }
    protected void onStop() {
        super.onStop();

        if (manager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            manager.removeUpdates(this);
        }
    }
    @Override
    public void onLocationChanged(Location location) {

        Lati = location.getLatitude();
        Long = location.getLongitude();
        cnt++;
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    public void onBackPressed(){
        // 戻るボタンが押された時
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void loadweather(){
        double VerLati = (double)((int)(Lati*1000)/1000.00);
        double VerLong = (double)((int)(Long*1000)/1000.00);
        //textView.setText(String.valueOf(VerLati)+","+String.valueOf(VerLong));
        System.out.println(VerLati);
        System.out.println(VerLong);
        // 接続先
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+VerLati+"&lon="+VerLong+"&appid=4f5e1a41f86e7d4b46349a18c76a7c1e";

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
        if(cnt >= 1){
            loadweather();
            addWeather();
        }

//        System.out.println("どんえーん");


        //TextViewに表示
//        textView.setText("HELLO HELLO HELLO");
    }
}