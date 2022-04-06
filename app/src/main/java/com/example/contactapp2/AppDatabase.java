package com.example.contactapp2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
//            instance = Room.databaseBuilder(context,
//                    AppDatabase.class, "contactapp").build();
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "contactapp")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

