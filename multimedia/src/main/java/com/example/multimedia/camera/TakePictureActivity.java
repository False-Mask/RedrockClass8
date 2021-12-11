package com.example.multimedia.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.multimedia.R;

import java.io.File;
import java.io.IOException;

public class TakePictureActivity extends AppCompatActivity {

    public static final int RESULT_CODE = 1;

    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


    Button btnTakePhoto;
    ImageView ivShow;

    String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        initComponent();
        initListener();
    }

    private File createImageFile() throws IOException {
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                "pic",  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void initListener() {
        btnTakePhoto.setOnClickListener((v) -> {
            File picFile = null;
            try {
                picFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Uri photoURI = FileProvider.getUriForFile(this, getPackageName() + "." + "fileprovider", picFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

            startActivityForResult(intent, RESULT_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        //这里注意一下直接通过Intent返回的图是缩略图，直接展示会比较模糊。
        //Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        //ivShow.setImageBitmap(bitmap);
        ivShow.setImageURI(Uri.fromFile(new File(currentPhotoPath)));
    }

    private void initComponent() {
        btnTakePhoto = findViewById(R.id.btn_take_photo);
        ivShow = findViewById(R.id.iv_show);
    }
}