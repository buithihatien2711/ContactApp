package com.example.contactapp2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact")
    public List<Contact> getAllContact();

    @Insert
    public void insertAll(Contact ... contact);
}

