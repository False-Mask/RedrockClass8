package com.example.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.multimedia.camera.CameraActivity;
import com.example.multimedia.player.MediaPlayerActivity;
import com.example.multimedia.sound.SoundPoolActivity;
import com.example.multimedia.store.StoreActivity;

public class MainActivity extends AppCompatActivity {

    Button btnMediaPlayer;
    Button btnSoundPool;
    Button btnCamera;
    Button btnMediaStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        initListener();
    }

    private void initListener() {
        btnMediaPlayer.setOnClickListener((v)->{
            startActivity(new Intent(this, MediaPlayerActivity.class));
        });

        btnSoundPool.setOnClickListener((v)->{
            startActivity(new Intent(this, SoundPoolActivity.class));
        });

        btnCamera.setOnClickListener((v)->{
            startActivity(new Intent(this, CameraActivity.class));
        });
        btnMediaStore.setOnClickListener((v)->{
            startActivity(new Intent(this , StoreActivity.class));
        });
    }

    private void initComponent() {
        btnMediaPlayer = findViewById(R.id.btn_media_player);
        btnSoundPool = findViewById(R.id.btn_sound_pool);
        btnCamera = findViewById(R.id.btn_camera);
        btnMediaStore = findViewById(R.id.btn_media_store);
    }
}