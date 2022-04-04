package com.example.contactapp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.example.contactapp2.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener{

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

//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                Contact contact1 = new Contact("Nguyen Van A", "098298492832", "a@gmail.com");
//                Contact contact2 = new Contact("Nguyen Van B", "098298492832", "b@gmail.com");
//                contactDao.insertAll(contact1, contact2);
//            }
//        });

        contactList = new ArrayList<Contact>();
        contactList = (ArrayList<Contact>) contactDao.getAllContact();
//        contactList.add(new Contact("Nguyen Van A", "098298492832", "a@gmail.com"));
//        contactList.add(new Contact("Nguyen Van B", "098298492832", "b@gmail.com"));
//        contactList.add(new Contact("Nguyen Van C", "098298492832", "c@gmail.com"));
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                contactList = (ArrayList<Contact>) contactDao.getAllContact();
//            }
//        });

        contactAdapter = new ContactAdapter(contactList);
        binding.rvContacts.setAdapter(contactAdapter);
        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));

        contactAdapter.setClickListener(this);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddNewContact.class), 100);
            }
        });
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            contactList = new ArrayList<Contact>();
            contactList = (ArrayList<Contact>) contactDao.getAllContact();
            contactAdapter = new ContactAdapter(contactList);
            binding.rvContacts.setAdapter(contactAdapter);
            binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                contactAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return ;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view, int position) {
        final Contact contact = contactList.get(position);
        int id = contact.getId();
        Intent intent = new Intent(MainActivity.this, UpdateContact.class);
        intent.putExtra("id", id);
        intent.putExtra("name", contact.getName());
        intent.putExtra("phone", contact.getPhone());
        intent.putExtra("email", contact.getEmail());
        startActivityForResult(intent, 100);
    }
}