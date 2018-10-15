package com.example.sandy.newgeckotransporter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class CameraActivity extends AppCompatActivity {
    private static final String TAG = "CameraActivity";
    private static final int requestCode = 200;
    ImageView mImageView;
    Bitmap mBitmap;
    Uri mUri;
    Button mShareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mImageView = findViewById(R.id.Imageview);
        mShareButton = findViewById(R.id.shareButton);

        // Determine Uri of camera image to save.
        Uri outputFileUri = getimage();
        Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        capture.putExtra(MediaStore.EXTRA_OUTPUT,outputFileUri);
        startActivityForResult(capture,requestCode);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareimage();
            }
        });

    }

    public Uri getimage()
    {
        Uri outputFileUri = null;
        File getimage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (getimage!=null)
        {
            Log.i(TAG, "file path:" + getimage.getAbsolutePath());
            outputFileUri = Uri.fromFile(new File(getimage.getPath(),"Profile.png"));
            Log.i(TAG, "output uri:" + outputFileUri.toString());
        }
        return outputFileUri;
    }
    public  void shareimage()
    {
        Uri imageUri = mUri;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.setType("Image/jpeg");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(intent,"Send"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (getimage() != null) {
                mUri = getimage();
                Log.i(TAG, "pic uri path:" + mUri.toString());
                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mUri);
                    mBitmap = Utils.rotateImageIfRequired(mBitmap, mUri);
                    mBitmap = Utils.getResizedBitmap(mBitmap, 500);
                    mImageView.setImageBitmap(mBitmap);


                    String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                    File mydir = new File(root + "/newtranspoter");
                    mydir.mkdir();
                    Random random = new Random();
                    int n = 1000;
                    n = random.nextInt(n);
                    String name = "Image_" + n + ".jpg";
                    File file = new File(mydir, name);
                    if (file.exists()) {
                        file.delete();
                    }
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        mBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        Log.e(TAG, "exception:" + e.getMessage());
                        e.printStackTrace();
                    }
                    MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned " + path + ":");
                                    Log.i("ExternalStorage", "-> uri=" + uri);
                                }
                            });

                } catch (IOException e) {
                    Log.e(TAG, "exception:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        else {
            finish();
        }
    }

}
