package com.example.temp;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    Button btnFragment1;
    Button btnFragment2;


    Fragment01 fragment01 = new Fragment01();
    Fragment02 fragment02 = new Fragment02();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        initListener();
    }

    private void initListener() {
        btnFragment1.setOnClickListener((v) -> {
            replace(R.id.f_container,fragment01);
        });


        btnFragment2.setOnClickListener((v)->{
            replace(R.id.f_container,fragment02);
        });
    }

    private void replace(@IdRes int id, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id,fragment);
        transaction.commit();
    }

    private void initComponent() {
        btnFragment1 = findViewById(R.id.btn_fragment1);
        btnFragment2 = findViewById(R.id.btn_fragment2);
    }
}