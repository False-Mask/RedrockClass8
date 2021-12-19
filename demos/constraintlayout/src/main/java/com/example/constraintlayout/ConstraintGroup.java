package com.example.constraintlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

public class ConstraintGroup extends AppCompatActivity {

    boolean isVisible = true;

    Button btnSetElevation;
    Group groupElevation;

    Button btnSetVisibility;
    Group groupVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_helper);

        initComponent();
        initListener();
    }

    private void initListener() {
        btnSetVisibility.setOnClickListener((v)->{
            isVisible = !isVisible;
            groupVisibility.setVisibility( isVisible ? View.VISIBLE:View.GONE);
        });

        btnSetElevation.setOnClickListener(v -> {
            btnSetElevation.setEnabled(false);
            final float[] elevation = {0f};
            new Thread(() -> {
                while (elevation[0] < 100f){
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    elevation[0] += 0.1f;
                    btnSetElevation.post(() -> groupElevation.setElevation(elevation[0]));
                }

                btnSetElevation.post(()-> btnSetElevation.setEnabled(true));

            }).start();
        });
    }

    private void initComponent() {
        btnSetElevation = findViewById(R.id.btn_set_elevation);
        groupElevation = findViewById(R.id.group);
        btnSetVisibility = findViewById(R.id.btn_set_visibility);
        groupVisibility = findViewById(R.id.group_visibility);
    }
}