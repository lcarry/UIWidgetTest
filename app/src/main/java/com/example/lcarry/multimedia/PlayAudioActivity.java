package com.example.lcarry.multimedia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lcarry.uiwidgettest.R;

import java.io.File;
import java.io.IOException;

public class PlayAudioActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);

        Button buttonPlay = (Button)findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(this);

        Button buttonPause = (Button)findViewById(R.id.button_pause);
        buttonPause.setOnClickListener(this);

        Button buttonStop = (Button)findViewById(R.id.button_stop);
        buttonStop.setOnClickListener(this);


        if (ContextCompat.checkSelfPermission(PlayAudioActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PlayAudioActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        } else {
            //初始化MediaPlayer
            initMediaPlayer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_play: {
                //播放
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
            }
            break;
            case R.id.button_pause: {
                //暂停
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
            break;
            case R.id.button_stop: {
                //停止
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    initMediaPlayer();
                else {
                    Toast.makeText(this, "拒绝权限将无法使用程序！！！", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    "/Music/" +
                    "Jive_Cool.mp3");
            mediaPlayer.setDataSource(file.getPath());//指定音频路径
            mediaPlayer.prepare();//播放器进入准备状态
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
