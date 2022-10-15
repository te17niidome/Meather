package com.example.blucode.meather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Loc_TestActivity extends AppCompatActivity implements View.OnClickListener, LocationListener{
    private TextView textView;
    public static double Lati;
    public static double Long;
    private LocationManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loca_test);
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



    }
    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, this);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1, this);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        double VerLati = (double)((int)(Lati*1000)/1000.00);
        double VerLong = (double)((int)(Long*1000)/1000.00);
        //textView.setText(String.valueOf(VerLati)+","+String.valueOf(VerLong));
        System.out.println(VerLati);
        System.out.println(VerLong);
    }
    public void onBackPressed(){
        // 戻るボタンが押された時
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}