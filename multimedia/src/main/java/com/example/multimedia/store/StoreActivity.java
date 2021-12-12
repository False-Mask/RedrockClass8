package com.example.multimedia.store;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.provider.MediaStore;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multimedia.R;

public class StoreActivity extends AppCompatActivity {

    String[] projection = new String[]{
            MediaStore.Audio.Media.DATA,
    };
    String selection = "";
    String[] selectionArgs = new String[]{

    };
    String sortOrder = "";
    CancellationSignal cancellationSignal = new CancellationSignal();

    ContentResolver resolver;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        initResolver();
        searchAudio();
    }

    private void searchAudio() {
        new Thread(()->{
            cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, sortOrder, cancellationSignal);
            StringBuilder s = new StringBuilder();
            for (String columnName : cursor.getColumnNames()) {
                s.append(columnName).append("\t");
            }
            String x = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
            Log.e("TAG", x);
            while(cursor.moveToNext()){
                StringBuilder str = new StringBuilder();
                for (String columnName : cursor.getColumnNames()) {
                    str.append(columnName).append("\t");
                }
                //Log.e("TAG",str.toString());
                x = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                Log.e("TAG", x);
            }
            cursor.close();
        }).start();
    }

    private void initResolver() {
        resolver = getApplicationContext().getContentResolver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancellationSignal.cancel();
    }
}