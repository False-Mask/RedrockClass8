package com.example.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    Button btnJump1;
    Button btnJump2;
    Button btnJump3;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        initListener();

    }

    private void initListener() {
        btnJump1.setOnClickListener((v) -> startActivity(new Intent(this, NormalActivity.class)));
        btnJump2.setOnClickListener((v) -> startActivity(new Intent(this, DangerousActivity.class)));
        btnJump3.setOnClickListener((v) -> startActivity(new Intent(this, SpecialActivity.class)));
    }

    private void initComponent() {
        btnJump1 = findViewById(R.id.btn_jump_1);
        btnJump2 = findViewById(R.id.btn_jump_2);
        btnJump3 = findViewById(R.id.btn_jump_3);
    }
}