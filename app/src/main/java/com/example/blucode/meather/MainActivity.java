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
    MediaPlayer song = MediaPlayer.create(this,R.raw.);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<String> playList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playList = choseMusic("晴れ", "春", "");
        System.out.println(playList);
//        start(playList);
        try {
            song.reset();
            song.setDataSource(this,Uri.parse("C:\\Users\\yukuk\\AndroidStudioProjects\\Meather\\app\\src\\main\\res\\raw\\osanpo.mp3"));
            song.prepare();
            song.start();
        } catch (Exception e) {
            System.out.println("エラー");
        }


    }

    //音楽をフィルタリングする
    public List<String> choseMusic(String weather, String season, String comment) {
        System.out.println("テスト");
        //音楽情報
        String musicList[][] = {{"お散歩", "C:\\Users\\yukuk\\AndroidStudioProjects\\Meather\\app\\src\\main\\res\\raw\\osanpo.mp3", "晴れ", "春", ""},
                {"旅立ちの時", "C:\\Users\\yukuk\\AndroidStudioProjects\\Meather\\app\\src\\main\\res\\raw\\tabidachi_no_toki.mp3", "晴れ", "春", ""}};
        //フィルタ後を格納するリスト
        List<String> playList = new ArrayList<>();
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
                if (weather_frag && season_frag)
                    playList.add(musicList[i][1]);
            }
            //コメントがあった場合はコメントも含めて判断
            else if (comment_frag && weather_frag && season_frag)
                playList.add(musicList[i][1]);
        }
//        playList.add(String.valueOf(R.raw.osanpo));
        return playList;
    }

    //音楽を再生
//    public void start(List<String> playList){
////        System.out.println(playList.get(0).getClass().getSimpleName());
////        int i = 0;
////        while(true) {
////            song = MediaPlayer.create(getApplicationContext(), Integer.parseInt(playList.get(i)));
////            song.start();
////            try{
////                if(playList.get(i+1) == null){
////                    break;
////                }
////                else{
////                    i++;
////                }
////            }catch(IndexOutOfBoundsException e){
////                System.out.println("プレイリスト終了");
////            }
//        try{
//            song = new MediaPlayer();
//            song.setDataSource(playList.get(0));
//            song.prepare();
//            song.start();
////            song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
////                @Override
////                public void onCompletion(MediaPlayer song) {
////                    song.stop();
////                    song.reset();
////                    try {
////                        song.setDataSource(playList.get(1));
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                    song.start();
////                }
////            });
//
//        }catch(Exception e){
//            System.out.println("エラー");
//        }


//    }
}