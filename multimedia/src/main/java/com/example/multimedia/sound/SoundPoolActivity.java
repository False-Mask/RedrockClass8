package com.example.multimedia.sound;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multimedia.R;

import java.util.HashMap;

public class SoundPoolActivity extends AppCompatActivity {

    public static final int MAX_PLAY_COUNT = Integer.MAX_VALUE;
    SoundPool soundPool;
    HashMap<Integer, Integer> soundList = new HashMap<>();

    int[] rawIds = new int[]{
            R.raw.bell,
            R.raw.birdsong,
            R.raw.bongo,
            R.raw.cave,
            R.raw.crystal_drop,
            R.raw.cuckoo,
            R.raw.lit,
            R.raw.old_bicycle
    };

    Button btnPlay1;
    Button btnPlay2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);
        initSoundPool();
        loadResource();

        initComponent();
        initListener();


    }

    private void initComponent() {
        btnPlay1 = findViewById(R.id.btn_play_1);
        btnPlay2 = findViewById(R.id.btn_play_2);
    }

    private void initListener() {
        btnPlay1.setOnClickListener((v) -> {
            int randomIndex = (int) ((rawIds.length) * Math.random());
            int randomId = rawIds[randomIndex];
            Integer sid = soundList.get(randomId);
            if (sid != null) soundPool.play(sid, 1f, 1f, 1, 0, 1f);
        });

        btnPlay2.setOnClickListener((v) -> {
            int randomIndex = (int) ((rawIds.length) * Math.random());
            int randomId = rawIds[randomIndex];
            Integer sid = soundList.get(randomId);
            if (sid != null) soundPool.play(sid, 1f, 1f, 1, 0, 1f);
        });
    }

    private void loadResource() {
        for (int rawId : rawIds) {
            int sid = soundPool.load(this, rawId, 1);
            soundList.put(rawId, sid);
        }
    }

    private void initSoundPool() {
        soundPool = new SoundPool.Builder().
                setMaxStreams(MAX_PLAY_COUNT)
                .build();

    }
}