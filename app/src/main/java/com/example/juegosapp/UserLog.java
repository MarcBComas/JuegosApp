package com.example.juegosapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juegosapp.LO.LightsOut5;
import com.example.juegosapp.data.GameDataHelper;

public class UserLog extends AppCompatActivity {
    Button login;
    Button signup;
    EditText username;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    EditText password;
    TextView info;
    GameDataHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlog);
        prefs = getApplicationContext().getSharedPreferences("preferences", 0);
        editor = prefs.edit();
        db = new GameDataHelper(this);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        info = (TextView) findViewById(R.id.signinfo);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.login:
                if (db.signIn(username.getText().toString(), password.getText().toString())) {
                    startActivity(new Intent(UserLog.this, MainActivity.class));
                    editor.putString("user", username.getText().toString());
                    editor.commit();
                } else {
                    info.setText("Username or password are incorrect.");
                }
                break;
            case R.id.signup:
                if(db.signUp(username.getText().toString(), password.getText().toString())) {
                    info.setText("Sign Up was succesful!");
                } else {
                    info.setText("Name already taken");
                }
                break;
        }
    }

}
