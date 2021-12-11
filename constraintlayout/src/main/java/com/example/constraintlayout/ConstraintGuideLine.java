package com.example.constraintlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ConstraintGuideLine extends AppCompatActivity {

    Button btnJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_guide_line);

        btnJump = findViewById(R.id.btn_jump);

        btnJump.setOnClickListener((v)->{

        });
    }
}