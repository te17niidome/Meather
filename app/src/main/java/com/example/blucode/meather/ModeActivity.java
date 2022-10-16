package com.example.blucode.meather;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button helpbtn,kankoubtn,gakusyubtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        findViewById(R.id.helpbtn).setOnClickListener(this);
        findViewById(R.id.main_btn).setOnClickListener(this);
        findViewById(R.id.demo_btn).setOnClickListener(this);
    }
    public void onClick(View view){
        Intent intent;
        Bundle mode = new Bundle();
        switch(view.getId()){
            case R.id.helpbtn :
                new AlertDialog.Builder(this)
                        .setTitle("説明")
                        .setMessage("")
                        .setPositiveButton("OK", null)
                        .show();
                break;
            case R.id.main_btn:
                mode.putString("mode","1");
                intent = new Intent(this ,MainActivity.class);
                intent.putExtras(mode);
                startActivity(intent);
                break;
            case R.id.demo_btn:
                mode.putString("mode","2");
                intent = new Intent(this , DemoActivity.class);
                intent.putExtras(mode);
                startActivity(intent);
                break;
        }
    }
    public void onBackPressed(){
        // 戻るボタンが押された時
        Intent intent = new Intent(this,TitleActivity.class);
        startActivity(intent);
    }
}