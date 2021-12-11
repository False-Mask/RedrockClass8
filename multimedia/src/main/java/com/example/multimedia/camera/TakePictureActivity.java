package com.example.multimedia.camera;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import com.example.multimedia.R;

import java.io.File;
import java.io.IOException;

public class TakePictureActivity extends AppCompatActivity {

    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    ActivityResultLauncher<Intent> launcher;

    Button btnTakePhoto;
    ImageView ivShow;

    String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        initComponent();
        initListener();

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    int resultCode = result.getResultCode();
                    if (resultCode != RESULT_OK) return;

                    Intent backIntent = result.getData();
                    //if (backIntent==null) return;

                    //这里注意一下直接通过Intent返回的图是缩略图，直接展示会比较模糊。
                    //Bitmap bitmap = getBitmapDirectly(backIntent);
                    //ivShow.setImageBitmap(bitmap);
                    ivShow.setImageURI(Uri.fromFile(new File(currentPhotoPath)));
                });
    }

    private Bitmap getBitmapDirectly(Intent backIntent) {
        return (Bitmap) backIntent.getExtras().get("data");
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
        btnTakePhoto.setOnClickListener((v)->{
            File picFile=null;
            try {
                picFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Uri photoURI = FileProvider.getUriForFile(this,
                    getPackageName() +"."+ "fileprovider",
                    picFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);

            launcher.launch(intent);
        });
    }

    private void initComponent() {
        btnTakePhoto = findViewById(R.id.btn_take_photo);
        ivShow = findViewById(R.id.iv_show);
    }
}