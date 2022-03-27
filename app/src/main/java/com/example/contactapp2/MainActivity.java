package com.example.contactapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.contactapp2.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact> contactList;
    private  ContactAdapter contactAdapter;

    private AppDatabase appDatabase;
    private ContactDao contactDao;

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        appDatabase = AppDatabase.getInstance(this);
        contactDao = appDatabase.contactDao();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Contact contact1 = new Contact("Nguyen Van A", "098298492832", "a@gmail.com");
                Contact contact2 = new Contact("Nguyen Van B", "098298492832", "b@gmail.com");
                contactDao.insertAll(contact1, contact2);
            }
        });

        contactList = new ArrayList<Contact>();
        contactList.add(new Contact("Nguyen Van A", "098298492832", "a@gmail.com"));
        contactList.add(new Contact("Nguyen Van B", "098298492832", "b@gmail.com"));
        contactList.add(new Contact("Nguyen Van C", "098298492832", "c@gmail.com"));

        contactAdapter = new ContactAdapter(contactList);
        binding.rvContacts.setAdapter(contactAdapter);
        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));

    }
}