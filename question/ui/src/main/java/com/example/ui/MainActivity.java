package com.example.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    Button btnRefresh1;
    Button btnRefresh2;
    Button btnRefresh3;
    TextView tvShow;

    static  final Handler handler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();

        initListener();
    }

    private void initListener() {
        btnRefresh1.setOnClickListener((v)->{
            new Thread(()->{
                runOnUiThread(()->{
                    tvShow.setText("这是第一种刷新UI方式");
                });
            }).start();
        });

        btnRefresh2.setOnClickListener((v)->{
            new Thread(()->{
                v.post(()->{
                    tvShow.setText("这是第二种刷新UI方式");
                });
            }).start();
        });

        btnRefresh3.setOnClickListener((v)->{
            new Thread(()->{
                handler.post(()->{
                    tvShow.setText("这是第三种刷新UI方式");
                });
            }).start();
        });
    }

    private void initComponent() {
        btnRefresh1 = findViewById(R.id.btn_refresh1);
        btnRefresh2 = findViewById(R.id.btn_refresh2);
        btnRefresh3 = findViewById(R.id.btn_refresh3);
        tvShow = findViewById(R.id.tv_show);
    }
}