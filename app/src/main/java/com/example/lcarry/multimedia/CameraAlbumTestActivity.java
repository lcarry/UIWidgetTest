package com.example.lcarry.multimedia;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lcarry.uiwidgettest.R;

import java.io.File;
import java.io.FileNotFoundException;

public class CameraAlbumTestActivity extends AppCompatActivity implements View.OnClickListener{


    public static final int TAKE_PHOTO = 1;

    public static final int CHOOSE_PHOTO = 2;

    private ImageView mImageViewPicture;

    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_album_test);

        Button buttonTakePhoto = (Button)findViewById(R.id.button_take_photo);
        buttonTakePhoto.setOnClickListener(this);

        mImageViewPicture = (ImageView)findViewById(R.id.image_view_picture);


        Button buttonChoosePhoto = (Button)findViewById(R.id.button_choose_photo);
        buttonChoosePhoto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_take_photo: {
                //拍照
                takePhoto(v);

            }
            break;
            case R.id.button_choose_photo: {
                //选取照片
                choosePhoto(v);

            }
            break;
            default:
                break;
        }
    }

    private void takePhoto(View view) {
        //创建file对象，用于存储拍照后的图片
        File outputImage = new File(getExternalCacheDir(),"output_image.jpg");

        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //android6.0以上系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mImageUri = FileProvider.getUriForFile(CameraAlbumTestActivity.this,
                    "com.example.lcarry.uiwidgettest.fileprovider",outputImage);
        } else {
            mImageUri = Uri.fromFile(outputImage);
        }

        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,mImageUri);
        startActivityForResult(intent,TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
            {
                if (resultCode == RESULT_OK) {
                    //将拍摄的照片显示出来
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(mImageUri));
                        mImageViewPicture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
                break;
            case CHOOSE_PHOTO:
            {
                if (resultCode == RESULT_OK) {
                    //判断系统版本号是否大于4.4
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }

                }
            }
            break;
            default:
                break;
        }
    }

    private void choosePhoto(View view) {
        if (ContextCompat.checkSelfPermission(CameraAlbumTestActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CameraAlbumTestActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        } else {
            //打开相册
            openAlbum();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    openAlbum();
                else
                    Toast.makeText(this,"You Denied The Permission ",Toast.LENGTH_SHORT).show();
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    //android4.4版本以后，获取相册图片不再返回真实Uri，而是返回一个封装过的Uri
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        //如果是document类型的Uri，通过document id处理
        if(DocumentsContract.isDocumentUri(this,uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents"
                    .equals(uri.getAuthority())){// MediaProvider
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection);

            } else if ("com.android.providers.downloads.documents"
                    .equals(uri.getAuthority())){// DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);

            } else if ("com.android.externalstorage.documents"
                    .equals(uri.getAuthority())){// ExternalStorageProvider
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    imagePath = Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {// MediaStore (and general)
            // 是content类型的uri，使用普通方式
            imagePath = getImagePath(uri,null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {// File
            // 如果是file类型的uri，直接获取路径
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection){
        String path = null;
        //通过uri和selection获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null){
            if (cursor.moveToNext()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            mImageViewPicture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this,"faild to get image ",Toast.LENGTH_SHORT).show();
        }

    }
}
