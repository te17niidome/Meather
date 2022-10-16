package com.example.blucode.meather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
public class Map extends AppCompatActivity implements OnMapReadyCallback {
    public double Lati2;
    public double Long2;
    public double Lati;
    public double Long;
    private GoogleMap map_ = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // マップを取得して初期化の検知を登録
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync( this );
        Intent intent = getIntent();
        String InLati = intent.getStringExtra("InLati");
        String InLong = intent.getStringExtra("InLong");
        if (InLati == null){
            Lati2=0;
        }
        else{
            Lati2 = Double.parseDouble(InLati);
        }
        if(InLong==null){
            Long2=0;
        }
        else{
            Long2 = Double.parseDouble(InLong);
        }
        Lati=0;
        Long=0;
    }

    public void onMapReady( GoogleMap googleMap )
    {
        map_ = googleMap;
        LatLng Koshi = new LatLng(32.885972, 130.78975);
        LatLng User;
        Marker user;

        if(Lati2 == 0 || Long2 == 0){
            User = new LatLng(32.885972, 130.78975);
        }
        else{
            User = new LatLng(Lati2,Long2);
            user = googleMap.addMarker(new MarkerOptions().position(User)
                    .title("現在地").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
            user.showInfoWindow();
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(User));

    }
    public class MapsMarkerActivity extends AppCompatActivity
            implements OnMapReadyCallback {
        // Include the OnCreate() method here too, as described above.
        @Override
        public void onMapReady(GoogleMap googleMap) {

        }
    }
    @Override
    public void onBackPressed(){
        // 戻るボタンが押された時
        Intent intent = new Intent(this,ModeActivity.class);
        startActivity(intent);
    }
}
