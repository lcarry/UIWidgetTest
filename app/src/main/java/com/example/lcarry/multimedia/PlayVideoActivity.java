package com.example.lcarry.multimedia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lcarry.uiwidgettest.R;

import java.io.File;

public class PlayVideoActivity extends AppCompatActivity implements View.OnClickListener{

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);


        videoView = (VideoView)findViewById(R.id.view_video);

        Button buttonPlay = (Button)findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(this);

        Button buttonPause = (Button)findViewById(R.id.button_pause);
        buttonPause.setOnClickListener(this);

        Button buttonStop = (Button)findViewById(R.id.button_replay);
        buttonStop.setOnClickListener(this);


        if (ContextCompat.checkSelfPermission(PlayVideoActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PlayVideoActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        } else {
            //初始化VideoPath
            initVideoPath();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    initVideoPath();
                else {
                    Toast.makeText(this, "拒绝权限将无法使用程序！！！", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_play: {
                //播放
                if (!videoView.isPlaying()){
                    videoView.start();
                }
            }
            break;
            case R.id.button_pause: {
                //暂停
                if (videoView.isPlaying()){
                    videoView.pause();
                }
            }
            break;
            case R.id.button_replay: {
                //重新播放
                if (videoView.isPlaying()){
                    videoView.resume();
                }
            }
            break;
            default:
                break;
        }
    }
    private void initVideoPath() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    "/DCIM/Camera/" +
                            "VID_20170215_191210.mp4");
            videoView.setVideoPath(file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
