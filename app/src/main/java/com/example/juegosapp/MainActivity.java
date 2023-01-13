package com.example.juegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button lobttn;
    Button dmcobttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lobttn = (Button) findViewById(R.id.lightsout);
        dmcobttn = (Button) findViewById(R.id.dmco);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lightsout:
                startActivity(new Intent(MainActivity.this, LightsOut.class));
                break;
            case R.id.dmco:
                startActivity(new Intent(MainActivity.this, Dosmilcuarentayocho.class));
                break;
        }
    }
}