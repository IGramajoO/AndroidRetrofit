package com.example.retrofit.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {com.example.retrofit.db.User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "AppDatabase.db";
    public static final String USER_TABLE = "users";
    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract com.example.retrofit.db.UserDao getUserDao();

    public static AppDatabase getInstance(Context context) {
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            DB_NAME).build();
                }
            }
        }
        return instance;
    }
}
