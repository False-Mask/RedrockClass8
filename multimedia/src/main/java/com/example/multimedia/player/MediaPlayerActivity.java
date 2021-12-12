package com.example.multimedia.player;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multimedia.R;

import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity {

    MediaPlayer player;

    Button btnPlayPause;
    Button btnNext;

    Button btnPlayMovie;

    TextureView textureView;

    //歌单列表
    int[] playList = new int[]{
            R.raw.sun,
            R.raw.beautiful,
            R.raw.seeds
    };

    //记录当前正在播放歌曲的index
    int currentIndex = 0;

    //mediaPlayer是否处于播放状态
    boolean isPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        initComponent();
        initListener();


        initMediaPlayer();
        configMediaPlayer();
        prepareMediaPlayer();
    }

    private void initListener() {

        //点击进行状态的切换
        btnPlayPause.setOnClickListener((v) -> {
            isPlay = !player.isPlaying();
            if (isPlay) player.start();
            else player.pause();
        });

        //点击进行
        btnNext.setOnClickListener((v) -> {
            player.reset();
            currentIndex = (currentIndex + 1) % playList.length;
            try {
                player.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + playList[currentIndex]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.setOnPreparedListener(MediaPlayer::start);
            player.prepareAsync();
        });

        //点击播放视频
        btnPlayMovie.setOnClickListener((v) -> {
            //由于我是用的之前的播放音乐的那个mediaPlayer所以使用它进行视频播放的时候得重新设置。
            player.reset();
            //设置播放设置
            player.setAudioAttributes(
                    new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()

            );
            //设置数据源
            try {
                player.setDataSource(
                        this,Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.solitary_brave)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            //设置准备完成的监听
            player.setOnPreparedListener(MediaPlayer::start);
            //设置MediaPlayer视频内容的载体
            player.setSurface(new Surface(textureView.getSurfaceTexture()));
            //异步准备(解码)
            player.prepareAsync();
        });
    }

    private void initComponent() {
        btnPlayPause = findViewById(R.id.btn_play_pause);
        btnNext = findViewById(R.id.btn_next);
        btnPlayMovie = findViewById(R.id.btn_play_movie);
        textureView = findViewById(R.id.textureView2);
    }

    private void prepareMediaPlayer() {
        //异步线程准备 —— 新开辟一个线程进行准备不存在阻塞线程的风险 UI safely
        player.prepareAsync();

//        不是很推荐，尤其是网络音频。
//        本地raw文件或者本地音视频勉强可以。
//        同步准备 —— 如果是在UI线程存在阻塞线程的风险
//        try {
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void configMediaPlayer() {
        //播放的数据源
        try {
            player.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + playList[currentIndex]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //播放设备的配置
        player.setAudioAttributes(
                new AudioAttributes.Builder()
                        //设置播放的类型
                        //音频or视频or......
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
    }

    private void initMediaPlayer() {
        //高端的代码往往只需要最朴素的实例化方法hh
        player = new MediaPlayer();
        //第二种
        //虽然不推荐还是得贴出来
        //mediaPlayer = MediaPlayer.create(this,R.raw.sun);
    }
}