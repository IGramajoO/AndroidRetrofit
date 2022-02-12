package com.example.retrofit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.retrofit.db.User;
import com.example.retrofit.db.AppDatabase;
import com.example.retrofit.db.UserDao;

public class LoginActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.retrofit.userIdKey";

    private EditText mUsernameField;
    private EditText mPasswordField;

    private String mUsername;
    private String mPassword;

    private Button mSubmitButton;

    private UserDao mUserDAO;
    private User mUser;
    private int mUserId = -1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameField = findViewById(R.id.usernameEditText);
        mPasswordField = findViewById(R.id.passwordEditText);

        mSubmitButton = findViewById(R.id.loginSubmitButton);

        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = mUsernameField.getText().toString();
                mPassword = mPasswordField.getText().toString();
                if(checkForUserInDatabase()){
                    if(!verifyUser()){
                        Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(getApplicationContext(), BookSearchFragment.class);
                        mUserId = mUser.getId();
                        intent.putExtra(USER_ID_KEY, mUserId);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private boolean checkForUserInDatabase(){
        mUser = mUserDAO.getUserByUsername(mUsername);
        if (mUser == null){
            Toast.makeText(this, mUsername + " not found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean verifyUser(){
        return mUser.getPass().equals(mPassword);
    }
}