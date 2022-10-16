package com.example.blucode.meather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    private Handler handler;
    private SplashHandler spHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
    @Override
    protected void onResume() {
        super.onResume();

        // 2秒後に設定する
        handler = new Handler();
        spHandler = new SplashHandler();
        handler.postDelayed(spHandler, 2500);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Intent intent = null;
        handler.removeCallbacks(spHandler);
    }

    // 一定時間後にスプラッシュ画面からスタート画面に自動遷移するためのサブクラス
    class SplashHandler implements Runnable {
        @Override
        public void run() {
            //画面遷移処理
            Intent intent = new Intent(SplashActivity.this, ModeActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();       //アクティビティを破棄。
        }
    }
}