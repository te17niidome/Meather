package com.example.blucode.meather;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
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
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  LocationListener {

    private OkHttpClient mClient;

    TextView textView;
    ImageButton imageButton2;
    Timer timer;

    public static double Lati;
    public static double Long;
    public static int cnt = 0;
    private LocationManager manager;


    //再生の準備
    MediaPlayer song = new MediaPlayer();
    //音楽情報
    String musicList[][] = {
            {"春",String.valueOf(R.raw.vivaldy_spring),"Clouds","春",""},
            {"フーガト短調",String.valueOf(R.raw.fu_ga),"Clouds","秋",""}};
    List<String> playList = new ArrayList<>();
    String weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        choseMusic("晴れ", "春", "");
        System.out.println(playList);


        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mClient = new OkHttpClient();

        // UIコンポーネント
//        textView = findViewById(R.id.text_view);
        imageButton2 = findViewById(R.id.imageButton2);

        // 定期呼び出し
        timer = new Timer();
        loadweather();

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
                    System.out.println(weatherObject.getString("main"));
                    weather = weatherObject.getString("main");
                    // *******************************************************
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

//    public void getWeather(View view) {
//
//        timer.scheduleAtFixedRate(
//                new TimerTask()
//                {
//                    @Override
//                    public void run()
//                    {
//                        System.out.println("もう終わりだ");
//                        if(cnt >= 1){
//                            loadweather();
//                        }
//                    }
//                }, 10, 10000);
//    }
    //音楽をフィルタリングする
//    public void choseMusic(String weather, String season, String comment ) {
    public void choseMusic(View view){
        timer.scheduleAtFixedRate(
                new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        System.out.println("もう終わりだ");
                        if(cnt >= 1){
                            loadweather();
                        }
                    }
                }, 10, 10000);
        String season;
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        System.out.println(month);
        if((month%12)>=3&&(month%12)<=5){
            season = "春";
        }
          else if((month%12)>=6 && (month%12)<=8){
            season = "夏";
        }
        else if((month%12)>=9 && (month%12)<=11){
            season = "秋";
        }
        else{
            season = "冬";
        }
        System.out.println("季節:"+ season);
//        weather ="Clean";
        System.out.println("天候:" + weather);
        String comment = "";
        System.out.println("テスト");

        //フィルタ後を格納するリスト
        System.out.println(musicList.length);

        for (int i = 0; i < musicList.length; i++) {
            boolean weather_frag = false;    //天気用のフラグ
            boolean season_frag = false;     //季節用のフラグ
            boolean comment_frag = false;    //コメント用のフラグ
            for (int j = 0; j < musicList[i].length; j++) {
                //それぞれを比較して当てはまるか確認
                switch (j) {
                    //天気の比較
                    case 2:
                        if (musicList[i][j].equals(weather))
                            weather_frag = true;
                        break;

                    //季節の比較
                    case 3:
                        if (musicList[i][j].equals(season))
                            season_frag = true;
                        break;

                    //コメントの比較
                    case 4:
                        if (comment == "")    //コメントが無かった場合何もしない
                            break;
                        else if (musicList[i][j].equals(comment))
                            comment_frag = true;
                        break;

                }

            }
            if (comment == "") {                 //コメントがなかった場合はそれ以外のフラグで判断
                if (weather_frag && season_frag) {
                    playList.add(musicList[i][1]);
                }
            }
            //コメントがあった場合はコメントも含めて判断
            else if (comment_frag && weather_frag && season_frag) {
                playList.add(musicList[i][1]);
            }
        }
//        playList.add(String.valueOf(R.raw.osanpo))
        start();
    }

    //音楽を再生
    public void start(){
        song = MediaPlayer.create(this, Integer.parseInt(playList.get(0)));
        song.start();

    }
}