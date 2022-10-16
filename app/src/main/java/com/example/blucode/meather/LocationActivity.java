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
import android.widget.ImageButton;
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

public class LocationActivity extends AppCompatActivity implements  LocationListener {

    private OkHttpClient mClient;

    TextView textView;
    ImageButton imageButton2;

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
//        textView = findViewById(R.id.text_view);
        imageButton2 = findViewById(R.id.imageButton2);
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

    public void onClick(){
        double VerLati = (double)((int)(Lati*1000)/1000.00);
        double VerLong = (double)((int)(Long*1000)/1000.00);
        //textView.setText(String.valueOf(VerLati)+","+String.valueOf(VerLong));
        System.out.println(VerLati);
        System.out.println(VerLong);
        Bundle data = new Bundle();
        Bundle InLati = new Bundle();
        Bundle InLong = new Bundle();
        Intent intent = new Intent(this,Map.class);
        InLati.putString("InLati",String.valueOf(Lati));
        InLong.putString("InLong",String.valueOf(Long));
        intent.putExtras(InLati);
        intent.putExtras(InLong);
        startActivity(intent);
    }






}