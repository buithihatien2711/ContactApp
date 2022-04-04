package com.example.contactapp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class AddNewContact extends AppCompatActivity {

    private ImageView imageView;
    private Button btnTakePhoto;
    private Bitmap bitmapAvt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        final EditText name =  findViewById(R.id.txt_name);
        final EditText phone =  findViewById(R.id.txt_phone);
        final EditText email =  findViewById(R.id.txt_email);
        Button saveButton =  findViewById(R.id.btn_save);
        imageView = findViewById(R.id.imageView);
        btnTakePhoto = findViewById(R.id.btn_take_photo);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] imgAvt = getBitmapAsByteArray(bitmapAvt);
                saveNewUser(name.getText().toString(), phone.getText().toString(), email.getText().toString()); //, imgAvt);
            }
        });

        if(ContextCompat.checkSelfPermission(AddNewContact.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddNewContact.this, new String[]{
                    Manifest.permission.CAMERA}, 200);
        }
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent1, 300);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 300){
            bitmapAvt = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmapAvt);
        }
    }

    private void saveNewUser(String name, String phone, String email){ //, byte[] imgAvatar) {
        AppDatabase db  = AppDatabase.getInstance(this.getApplicationContext());

//        Contact contact = new Contact(name, phone, email, imgAvatar);

        Contact contact = new Contact(name, phone, email);
        db.contactDao().insertAll(contact);

        finish();

    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}