package com.example.juegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.juegosapp.DMCO.Dosmilcuarentayocho3;
import com.example.juegosapp.DMCO.Dosmilcuarentayocho4;
import com.example.juegosapp.LO.LightsOut4;
import com.example.juegosapp.LO.LightsOut5;
import com.example.juegosapp.data.RankingActivity;

public class MainActivity extends AppCompatActivity {
    Button lobttn;
    Button hsbttn;
    Button loplaybttn5;

    Button loplaybttn4;
    Button dmcobttn;
    Button dmcoplaybttn4;
    Button dmcoplaybttn3;
    Button backbttn;

    ImageView logo1;
    ImageView logo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lobttn = (Button) findViewById(R.id.lightsout);
        hsbttn = (Button) findViewById(R.id.highscores);
        loplaybttn5 = (Button) findViewById(R.id.loplay5);
        loplaybttn4 = (Button) findViewById(R.id.loplay4);
        dmcobttn = (Button) findViewById(R.id.dmco);
        dmcoplaybttn4 = (Button) findViewById(R.id.dmcoplay4);
        dmcoplaybttn3 = (Button) findViewById(R.id.dmcoplay3);
        backbttn = (Button) findViewById(R.id.atras);
        logo1 = (ImageView) findViewById(R.id.mlogo1);
        logo2 = (ImageView) findViewById(R.id.mlogo2);
        Animation animfade = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        logo1.startAnimation(animfade);
        logo2.startAnimation(animfade);
        lobttn.startAnimation(animfade);
        dmcobttn.startAnimation(animfade);
        hsbttn.startAnimation(animfade);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dmco:
                dmcobttn.setVisibility(View.GONE);
                lobttn.setVisibility(View.GONE);
                hsbttn.setVisibility(View.INVISIBLE);
                dmcoplaybttn4.setVisibility(View.VISIBLE);
                dmcoplaybttn3.setVisibility(View.VISIBLE);
                backbttn.setVisibility(View.VISIBLE);
                break;
            case R.id.lightsout:
                dmcobttn.setVisibility(View.GONE);
                lobttn.setVisibility(View.GONE);
                hsbttn.setVisibility(View.INVISIBLE);
                loplaybttn5.setVisibility(View.VISIBLE);
                loplaybttn4.setVisibility(View.VISIBLE);
                backbttn.setVisibility(View.VISIBLE);
                break;
            case R.id.atras:
                dmcobttn.setVisibility(View.VISIBLE);
                lobttn.setVisibility(View.VISIBLE);
                hsbttn.setVisibility(View.VISIBLE);
                loplaybttn5.setVisibility(View.GONE);
                loplaybttn4.setVisibility(View.GONE);
                dmcoplaybttn4.setVisibility(View.GONE);
                dmcoplaybttn3.setVisibility(View.GONE);
                backbttn.setVisibility(View.GONE);
                break;
            case R.id.highscores:
                Intent intent = new Intent(this, RankingActivity.class);
                startActivity(intent);
                break;
            case R.id.loplay5:
                startActivity(new Intent(MainActivity.this, LightsOut5.class));
                break;
            case R.id.loplay4:
                startActivity(new Intent(MainActivity.this, LightsOut4.class));
                break;
            case R.id.dmcoplay4:
                startActivity(new Intent(MainActivity.this, Dosmilcuarentayocho4.class));
                break;
            case R.id.dmcoplay3:
                startActivity(new Intent(MainActivity.this, Dosmilcuarentayocho3.class));
                break;
        }
    }
}