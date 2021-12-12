package com.example.constraintlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpUseActivity extends AppCompatActivity {

    Button btnGroup;
    Button btnGuideLine;
    Button btnBarrier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_use);

        initComponent();
        initListener();
    }

    private void initListener() {
        btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpUseActivity.this,ConstraintGroup.class));
            }
        });

        btnGuideLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpUseActivity.this,ConstraintGuideLine.class));

            }
        });

        btnBarrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpUseActivity.this,ConstraintBarrier.class));

            }
        });
    }

    private void initComponent() {
        btnGroup = findViewById(R.id.btn_group);
        btnGuideLine = findViewById(R.id.btn_guideline);
        btnBarrier = findViewById(R.id.btn_barrier);
    }
}