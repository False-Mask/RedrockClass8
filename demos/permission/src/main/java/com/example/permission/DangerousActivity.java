package com.example.permission;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;

public class DangerousActivity extends AppCompatActivity {

    Button btnCall;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangerous);
        initComponent();
        initListener();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initListener() {
        btnCall.setOnClickListener((v)->{

            if (PackageManager.PERMISSION_GRANTED == ActivityCompat
                    .checkSelfPermission(this, Manifest.permission.CALL_PHONE)) {
                final Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
                startActivity(intent);
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                    goSetting();
                } else {
                   ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void goSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void initComponent() {
        btnCall = findViewById(R.id.btn_call);
    }
}