package com.example.multimedia.camera;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.multimedia.R;

public class CameraActivity extends AppCompatActivity {

    Button btnTakePicture;
    Button btnRecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initComponent();
        initListener();

    }

    private void initListener() {
        btnTakePicture.setOnClickListener((v) -> startActivity(new Intent(this,TakePictureActivity.class)));
        btnRecode.setOnClickListener((v)-> startActivity(new Intent(this,RecordActivity.class)));
    }

    private void initComponent() {
        btnTakePicture = findViewById(R.id.btn_take_picture);
        btnRecode = findViewById(R.id.btn_record);
    }
}