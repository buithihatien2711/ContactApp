package com.example.contactapp2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "imgAvatar")
    private byte[] imgAvatar;

    public Contact(int id, String name, String phone, String email, byte[] imgAvatar) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.imgAvatar = imgAvatar;
    }

    public Contact(String name, String phone, String email, byte[] imgAvatar) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.imgAvatar = imgAvatar;
    }

//    public Contact(String name, String phone, String email) {
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//    }

    public Contact(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(byte[] imgAvatar) {
        this.imgAvatar = imgAvatar;
    }
}

