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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJump1 = findViewById(R.id.btn_jump_1);

        btnJump1.setOnClickListener((v) -> {

                    if (PackageManager.PERMISSION_GRANTED == ActivityCompat
                            .checkSelfPermission(this, Manifest.permission.CALL_PHONE)) {
                        final Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
                        startActivity(intent);
                    } else {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                            goSetting();
                        }else {
                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        }
                    }
                }
        );

    }

    private void goSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}