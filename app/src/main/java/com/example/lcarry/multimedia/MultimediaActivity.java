package com.example.lcarry.multimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lcarry.broadcastTest.UIBroadcastTestActivity;
import com.example.lcarry.notification.UINotificationActivity;
import com.example.lcarry.uiwidgettest.R;

import java.io.File;
import java.io.IOException;

public class MultimediaActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        Button buttonCamera = (Button)findViewById(R.id.button_camera);
        buttonCamera.setOnClickListener(this);

        Button buttonAudio = (Button)findViewById(R.id.button_audio);
        buttonAudio.setOnClickListener(this);

        Button buttonVideo = (Button)findViewById(R.id.button_video);
        buttonVideo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_camera: {
                //拍照
                showCameraAlbumTestActivity("showCameraAlbumTestActivity");
            }
            break;
            case R.id.button_audio: {
                //音频
                showPlayAudioActivity("showPlayAudioActivity");
            }
            break;
            case R.id.button_video: {
                //视频
                showPlayVideoActivity("showPlayVideoActivity");
            }
            break;

            default:
                break;
        }
    }

    public void showCameraAlbumTestActivity(String strName) {
        Intent intent = new Intent(MultimediaActivity.this, CameraAlbumTestActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }

    public void showPlayAudioActivity(String strName) {
        Intent intent = new Intent(MultimediaActivity.this, PlayAudioActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }

    public void showPlayVideoActivity(String strName) {
        Intent intent = new Intent(MultimediaActivity.this, PlayVideoActivity.class);
        startActivity(intent);
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }

}
