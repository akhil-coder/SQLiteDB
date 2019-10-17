package com.appface.akhil.sqlitedb;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appface.akhil.sqlitedb.Beans.ToDO;
import com.appface.akhil.sqlitedb.SQLite_DB.ToDoListDBAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ToDoListDBAdapter toDoListDBAdapter;
    private List<ToDO> toDos;
    private byte[] byteArrayImage;
    private static final int SELECT_FILE = 2;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 11;
    private static final int PERMISSION_CODE_CAMERA = 500;
    private static final int PERMISSION_CODE_GALLERY = 501;
    ImageView ivcamera, ivupload, ivgallery, iv_avatar;
    final int CAMERA_REQUEST = 1;
    private static final String TAG = "MainActivity";
    Uri imageUri;
    private Bitmap bitmap;
    private EditText et_image_name;
    private TextView tv_display;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoListDBAdapter = ToDoListDBAdapter.getToDoListDBAdapterInstance(getApplicationContext());
        toDos = toDoListDBAdapter.getAllToDos();
        ivcamera = findViewById(R.id.iv_camera);
        ivgallery = findViewById(R.id.iv_gallery);
        iv_avatar = findViewById(R.id.iv_avatar);
        et_image_name = findViewById(R.id.et_image_name);
        ivupload = findViewById(R.id.iv_upload);

        ivgallery.setOnClickListener(this);
        ivcamera.setOnClickListener(this);
        ivupload.setOnClickListener(this);

        setNewList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_gallery: checkGalleryPermission(); break;
            case R.id.iv_camera: checkCameraPermission(); break;
            case R.id.iv_upload: storeImageInSQL();
//                                 onImageUpload();
                                 break;
            default: break;
        }
    }

    private void setNewList(){
//        tv_display.setText(getToDolistString());
    }

    private void setNewDisplayList() {
        toDos = toDoListDBAdapter.getAllToDos();
        if(toDos != null && toDos.size() > 0){
            for(ToDO toDO: toDos){

            }
        }
    }

    private void modifyToDo() {
//        int id = Integer.parseInt(et_removeid.getText().toString());
//        String task = et_modify.getText().toString();
//        toDoListDBAdapter.modify(id, task);
//        setNewList();
    }

    private void removeToDo() {
//        toDoListDBAdapter.delete(Integer.parseInt(et_removeid.getText().toString()));
        setNewList();
    }

    private void storeImageInSQL() {
        byteArrayImage = DbBitmapUtility.getBytes(bitmap);
        if(toDoListDBAdapter.insert(et_image_name.getText().toString(), byteArrayImage))
            Toast.makeText(this, "Image Saved Locally", Toast.LENGTH_SHORT).show();
        setNewList();
    }

    private void onImageUpload()
    {
        String imagebase64 = convertBitmaptoJpeg();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ImageClass> call = apiInterface.uploadImage("Init Image", imagebase64);
        call.enqueue(new Callback<ImageClass>() {
            @Override
            public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {

                ImageClass imageClass = response.body();
                Toast.makeText(MainActivity.this, "Server response: " + imageClass.getResponse(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ImageClass> call, Throwable t) {

            }
        });
    }

    private String convertBitmaptoJpeg() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void openGallery() {
        Log.d(TAG, "openGallery: ");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void openCamera() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "New Picture");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "From the camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && imageUri != null){
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            iv_avatar.setImageURI(imageUri);
        }

        if(requestCode == SELECT_FILE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                iv_avatar.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode)
        {
            case PERMISSION_CODE_CAMERA: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                }
                break;
            }

            case PERMISSION_CODE_GALLERY: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
                break;
            }
        }
    }

    void checkCameraPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ) {
                String[] permission = {Manifest.permission.CAMERA};
                requestPermissions(permission, PERMISSION_CODE_CAMERA);
            } else {
                //Persmission already granted
                openCamera();
            }
        }else {
            // < Marshmellow
            openCamera();
        } //test
    }

    void checkGalleryPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permission, PERMISSION_CODE_GALLERY);
            } else {
                //Persmission already granted
                openGallery();
            }
        }else {
            // < Marshmellow
            openCamera();
        }
    }
}
