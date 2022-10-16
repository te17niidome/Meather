package com.example.blucode.meather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class TitleActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

    }

    /*
    public void onClick(View view) {

        Intent intent = new Intent(this , SelectActivity.class);
        startActivity(intent);
    }
    */
    public boolean onTouchEvent(MotionEvent event){
        Intent intent = new Intent(this , ModeActivity.class);
        startActivity(intent);
        return true;
    }
    public void onBackPressed(){
        // 戻るボタンが押された時
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        TitleActivity.this.startActivity(homeIntent);
    }

}