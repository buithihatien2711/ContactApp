package com.example.contactapp2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactapp2.databinding.ActivityAddNewContactBinding;

public class AddNewContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        final EditText name =  findViewById(R.id.txt_name);
        final EditText phone =  findViewById(R.id.txt_phone);
        final EditText email =  findViewById(R.id.txt_email);
        Button saveButton =  findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewUser(name.getText().toString(), phone.getText().toString(), email.getText().toString());
            }
        });
    }

    private void saveNewUser(String name, String phone, String email) {
        AppDatabase db  = AppDatabase.getInstance(this.getApplicationContext());

        Contact contact = new Contact(name, phone, email);
        db.contactDao().insertAll(contact);

        finish();

    }
}