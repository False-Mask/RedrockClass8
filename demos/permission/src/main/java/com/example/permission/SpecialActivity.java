package com.example.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SpecialActivity extends AppCompatActivity {

    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    String specialPermission = Manifest.permission.MANAGE_EXTERNAL_STORAGE;

    Button btnWrite;
    Button btnRead;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

        initComponent();
        initListener();
        checkPermission();

    }

    private void initListener() {
        btnWrite.setOnClickListener((v) -> {
            File file = Environment.getExternalStorageDirectory();
            File newFile = new File(
                    file, "Permission Demo"
            );
            try {
                if (newFile.createNewFile()) {
                    Toast.makeText(this, "文件创建成功\n名称为Permission Demo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "文件创建失败", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }

        });

        btnRead.setOnClickListener((v) -> {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File file = new File(externalStorageDirectory, "Test Read");
            StringBuilder builder = new StringBuilder();
            if (file.exists()) {
                try {
                    InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
                    int cha;
                    while ((cha = reader.read()) != -1) {
                        if (((char) cha) != '\r') {
                            builder.append((char) cha);
                        }
                    }
                    reader.close();
                    Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Test Read文件不存在", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission)
                        == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(permissions, 1);
                }
            }
        }

        else if (Build.VERSION_CODES.R == Build.VERSION.SDK_INT) {
            if (ActivityCompat.checkSelfPermission(this, specialPermission)
                    == PackageManager.PERMISSION_DENIED) {
                //使用后会发现没有任何的作用，这个权限不会发出弹窗，系统会默认拒绝这个权限。
                //requestPermissions(new String[]{specialPermission}, 2);
                //这时候就得引导用户到设置界面，同意我们的权限、
                guideUser();
            }
        }
    }

    private void guideUser() {
        new AlertDialog.Builder(this)
                .setTitle("敏感权限申请")
                .setMessage("由于Android 11操作系统，对存储权限要求严格，需要用户点击设置赋予权限。")
                .setPositiveButton("确认", (dialog, which) -> {
                    goSetting();
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    Toast.makeText(this, "我谢谢您嘞！", Toast.LENGTH_SHORT).show();
                }).create().show();
    }

    private void goSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void initComponent() {
        btnWrite = findViewById(R.id.btn_write);
        btnRead = findViewById(R.id.btn_read);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            String state  = grantResults[i] == PackageManager.PERMISSION_GRANTED ? "允许":"拒绝";
            Toast.makeText(this, permissions[i]+":"+state, Toast.LENGTH_SHORT).show();
        }
    }
}