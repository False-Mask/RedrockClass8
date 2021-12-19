package com.example.multimedia.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.VideoView;

import com.example.multimedia.R;

public class RecordActivity extends AppCompatActivity {


    public static final int REQUEST_VIDEO_CAPTURE = 1;
    Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

    VideoView vvShow;
    Button btnTake;

    private void dispatchTakeVideoIntent() {
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        initComponent();
        initListener();
    }

    private void initListener() {
        btnTake.setOnClickListener((v)->{
            dispatchTakeVideoIntent();
        });
    }

    private void initComponent() {
        btnTake = findViewById(R.id.btn_take);
        vvShow = findViewById(R.id.vv_show);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            vvShow.setVideoURI(videoUri);
            vvShow.start();
        }
    }
}