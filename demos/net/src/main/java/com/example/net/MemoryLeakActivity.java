package com.example.net;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MemoryLeakActivity extends AppCompatActivity {

    Button btnRequest;
    TextView tvShow;

    NetUtils.Cancel job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);
        initComponent();
        initListener();
    }

    private void initListener() {
        btnRequest.setOnClickListener((v)->{
            requestNet(v);
        });
    }

    private void requestNet(View v) {
        job = NetUtils.request("https://www.wanandroid.com/banner/json", new HashMap<>(), "get", new NetUtils.NetResult() {
            @Override
            public void onSuccess(String str) {
                v.post(()->{
                    tvShow.setText(str);
                    Log.e(MemoryLeakActivity.this.toString(), "onSuccess: " );
                });
            }

            @Override
            public void onError(Throwable throwable) {
                v.post(()-> Toast.makeText(MemoryLeakActivity.this, throwable.toString(), Toast.LENGTH_SHORT).show());

            }
        });
    }

    private void initComponent() {
        tvShow = findViewById(R.id.tv_show);
        btnRequest = findViewById(R.id.btn_request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}