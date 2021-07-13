package com.example.practice28;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.ToggleButton;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button btnPlay, btnStop;
    // 오디오 리소스를 사용하기 위한 객체
    MediaPlayer myPlayer = new MediaPlayer();
    ToggleButton btnStartStop;
    // 비디오 리소스를 사용하기 위한 객체
    VideoView myVideo;
    MediaController myControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button)findViewById(R.id.main_button1);
        btnStop = (Button)findViewById(R.id.main_button2);
        btnPlay.setOnClickListener(new ButtonClickListener());
        btnStop.setOnClickListener(new ButtonClickListener());

        // 비디오 위젯 ID를 할당받아 정의
        myVideo = (VideoView)findViewById(R.id.main_videoView);
        myControl = new MediaController(MainActivity.this);
        // 비디오에 컨트롤러 장착
        myVideo.setMediaController(myControl);

        btnStartStop = (ToggleButton)findViewById(R.id.main_toggleButton);
        btnStartStop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) { // play
                    // 비디오의 URI를 지정
                    // 리소스의 경로로 URI 지정
                    // "android.resource://현재패키지이름/R.raw.파일이름.mp4"
                    Uri videoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.beach_again);
                    myVideo.setVideoURI(videoUri);
                    myVideo.start();
                } else { // stop
                    myVideo.pause();
                }
            }
        });
    }

    // Activity가 종료(화면에 사라짐)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myPlayer.stop();
    }

    public class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_button1:
                    // 플레이 중이면 멈춤
                    if(myPlayer.isPlaying()) {
                        myPlayer.stop();
                    }

                    // 오디오 파일(Resources)을 지정
                    // 오디오의 경로(URI), 리소스ID(소문자, _ 사용가능)
                    myPlayer = MediaPlayer.create(MainActivity.this, R.raw.hello_little_girl);
                    myPlayer.setLooping(false); // 반복 재생
                    myPlayer.start();;
                    break;
                    
                case R.id.main_button2:
                    myPlayer.stop();
                    break;
            }
        }
    }
}