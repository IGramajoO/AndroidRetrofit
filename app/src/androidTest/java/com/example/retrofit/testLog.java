package com.example.retrofit;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.retrofit.db.AppDatabase;
import com.example.retrofit.db.User;
import com.example.retrofit.db.UserDao;

import org.junit.Test;

import static org.junit.Assert.*;

public class testLog {

    @Test
    public void testUser(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDao testDB = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .build()
                .getUserDao();

        User user = new User("Test", "Test");
        assertNull(testDB.getUserByUsername("Test"));
        testDB.insert(user);
        assertNotNull(testDB.getUserByUsername("Test"));

    }

    @Test
    public void testDeleteUser(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDao testDB = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .build()
                .getUserDao();

        User user = new User("Test", "Test");
        assertNull(testDB.getUserByUsername("Test"));
        testDB.insert(user);
        assertNotNull(testDB.getUserByUsername("Test"));
        testDB.deleteUser(testDB.getUserByUsername("Test"));
        assertNull(testDB.getUserByUsername("Test"));

    }

}
