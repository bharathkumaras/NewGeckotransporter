package com.example.sandy.newgeckotransporter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class Gallery extends AppCompatActivity {
    private static final int galleryRequestCode = 201;
    ImageView imageView;
    Bitmap myBitmap;
    Button shareButton;
    Uri picUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        shareButton = findViewById(R.id.shareButton);
        imageView = findViewById(R.id.Imageview);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("Image/*");
        startActivityForResult(Intent.createChooser(intent,"Selectimage"),galleryRequestCode);



    }

    public void share()
    {
        Uri ur = picUri;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM,ur);
        intent.setType("Images/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(intent,"send via"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        {
            if (requestCode == galleryRequestCode)
            {
                picUri = data.getData();
                try {
                    myBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),picUri);
                    myBitmap = Utils.rotateImageIfRequired(myBitmap, picUri);
                    myBitmap = Utils.getResizedBitmap(myBitmap, 500);
                    imageView.setImageBitmap(myBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            finish();
        }
    }
}
