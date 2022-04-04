package com.example.contactapp2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact")
    public List<Contact> getAllContact();

    @Insert
    public void insertAll(Contact ... contact);

//    @Query("UPDATE Contact SET name = :name, email = :email, phone = :phone WHERE id = :id")
//    public void updateContact(int id, String name, String email, String phone);

    @Update
    public void updateContact(Contact contact);
}

