package com.example.juegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.juegosapp.DMCO.Dosmilcuarentayocho;
import com.example.juegosapp.LO.LightsOut4;
import com.example.juegosapp.LO.LightsOut5;
import com.example.juegosapp.highscores.RankingActivity;

public class MainActivity extends AppCompatActivity {
    Button lobttn;
    Button lohsbttn;
    Button loplaybttn5;

    Button loplaybttn4;
    Button dmcobttn;
    Button dmcohsbttn;
    Button dmcoplaybttn;
    Button backbttn;

    ImageView logo1;
    ImageView logo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lobttn = (Button) findViewById(R.id.lightsout);
        lohsbttn = (Button) findViewById(R.id.lohighscores);
        loplaybttn5 = (Button) findViewById(R.id.loplay5);
        loplaybttn4 = (Button) findViewById(R.id.loplay4);
        dmcobttn = (Button) findViewById(R.id.dmco);
        dmcohsbttn = (Button) findViewById(R.id.dmcohighscores);
        dmcoplaybttn = (Button) findViewById(R.id.dmcoplay);
        backbttn = (Button) findViewById(R.id.atras);
        logo1 = (ImageView) findViewById(R.id.mlogo1);
        logo2 = (ImageView) findViewById(R.id.mlogo2);
        Animation animfade = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        logo1.startAnimation(animfade);
        logo2.startAnimation(animfade);
        lobttn.startAnimation(animfade);
        dmcobttn.startAnimation(animfade);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dmco:
                dmcobttn.setVisibility(View.GONE);
                lobttn.setVisibility(View.GONE);
                dmcohsbttn.setVisibility(View.VISIBLE);
                dmcoplaybttn.setVisibility(View.VISIBLE);
                backbttn.setVisibility(View.VISIBLE);
                break;
            case R.id.lightsout:
                dmcobttn.setVisibility(View.GONE);
                lobttn.setVisibility(View.GONE);
                lohsbttn.setVisibility(View.VISIBLE);
                loplaybttn5.setVisibility(View.VISIBLE);
                loplaybttn4.setVisibility(View.VISIBLE);
                backbttn.setVisibility(View.VISIBLE);
                break;
            case R.id.atras:
                dmcobttn.setVisibility(View.VISIBLE);
                lobttn.setVisibility(View.VISIBLE);
                lohsbttn.setVisibility(View.GONE);
                loplaybttn5.setVisibility(View.GONE);
                loplaybttn4.setVisibility(View.GONE);
                dmcohsbttn.setVisibility(View.GONE);
                dmcoplaybttn.setVisibility(View.GONE);
                backbttn.setVisibility(View.GONE);
                break;
            case R.id.lohighscores:
                Intent intent = new Intent(this, RankingActivity.class);
                intent.putExtra("GAME", "LO");
                startActivity(intent);
                break;
            case R.id.dmcohighscores:
                Intent intent2 = new Intent(this, RankingActivity.class);
                intent2.putExtra("GAME", "DMCO");
                startActivity(intent2);
                break;
            case R.id.loplay5:
                startActivity(new Intent(MainActivity.this, LightsOut5.class));
                break;
            case R.id.loplay4:
                startActivity(new Intent(MainActivity.this, LightsOut4.class));
                break;
            case R.id.dmcoplay:
                startActivity(new Intent(MainActivity.this, Dosmilcuarentayocho.class));
                break;
        }
    }
}