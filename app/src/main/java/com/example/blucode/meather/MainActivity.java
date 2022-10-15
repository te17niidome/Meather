package com.example.blucode.meather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //再生の準備
    MediaPlayer song = new MediaPlayer();
    //音楽情報
    String musicList[][] = {{"お散歩", String.valueOf(R.raw.osanpo), "晴れ", "春", "","60"},
            {"旅立ちの時", String.valueOf(R.raw.tabidachi_no_toki), "晴れ", "春", "","189"}};
    List<String> playList = new ArrayList<>();
    List<Integer> playTime = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choseMusic("晴れ", "春", "");
        System.out.println(playList);
        start();
    }

    //音楽をフィルタリングする
    public void choseMusic(String weather, String season, String comment) {
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
                    playTime.add(Integer.parseInt(musicList[i][5]));
                }
            }
            //コメントがあった場合はコメントも含めて判断
            else if (comment_frag && weather_frag && season_frag) {
                playList.add(musicList[i][1]);
                playTime.add(Integer.parseInt(musicList[i][5]));
            }
        }
//        playList.add(String.valueOf(R.raw.osanpo))

    }

    //音楽を再生
    public void start(){
        for(int i=0; i<playList.size(); i++) {
            song = MediaPlayer.create(this, Integer.parseInt(playList.get(i)));
            song.start();
            try{
                Thread.sleep(playTime.get(i)*10*10*10);
            }catch (Exception e){
                System.out.println("エラー");
            }

        }

    }
}