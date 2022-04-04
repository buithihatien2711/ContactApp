package com.example.contactapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.contactapp2.databinding.ActivityUpdateContactBinding;

public class UpdateContact extends AppCompatActivity {
    private int id;
    private String name;
    private String phone;
    private String email;
    Contact contact;
    ActivityUpdateContactBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        if(intent != null){
//            id = Integer.parseInt(intent.getStringExtra("id"));
            id = intent.getIntExtra("id", -1);

            name = intent.getStringExtra("name");
            binding.txtName.setText(name);

            phone = intent.getStringExtra("phone");
            binding.txtPhone.setText(phone);

            email = intent.getStringExtra("email");
            binding.txtEmail.setText(email);
        }

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact(id, name, phone, email);
            }
        });

        if(ContextCompat.checkSelfPermission(UpdateContact.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(UpdateContact.this, new String[]{
                    Manifest.permission.CAMERA}, 200);
        }

        binding.btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent1, 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            binding.imageView.setImageBitmap(bitmap);
        }
    }

    private void updateContact(int id, String name, String phone, String email) {
        AppDatabase db  = AppDatabase.getInstance(this.getApplicationContext());
        contact = new Contact();
        contact.setId(id);
        contact.setName(binding.txtName.getText().toString());
        contact.setPhone(binding.txtPhone.getText().toString());
        contact.setEmail(binding.txtEmail.getText().toString());

        db.contactDao().updateContact(contact);
//        db.contactDao().updateContact(id, name, phone, email);

        finish();

    }
}