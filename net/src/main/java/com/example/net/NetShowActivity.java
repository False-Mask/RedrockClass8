package com.example.net;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class NetShowActivity extends AppCompatActivity {


    Button btnRequest;
    Button btnJump;
    TextView tvShow;

    NetUtils.Cancel job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_show);
        initComponent();
        initListener();
    }

    private void initListener() {
        btnRequest.setOnClickListener((v)->{
            requestNet(v);
        });

        btnJump.setOnClickListener((v)-> startActivity(new Intent(this,MemoryLeakActivity.class)));
    }

    private void requestNet(View v) {
        job = NetUtils.request("https://www.wanandroid.com/banner/json", new HashMap<>(), "get", new NetUtils.NetResult() {
            @Override
            public void onSuccess(String str) {
                v.post(()->{
                    tvShow.setText(str);
                });
            }

            @Override
            public void onError(Throwable throwable) {
                v.post(()-> Toast.makeText(NetShowActivity.this, throwable.toString(), Toast.LENGTH_SHORT).show());

            }
        });
    }

    private void initComponent() {
        tvShow = findViewById(R.id.tv_show);
        btnRequest = findViewById(R.id.btn_request);
        btnJump = findViewById(R.id.btn_jump);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}