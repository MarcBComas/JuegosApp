package com.example.juegosapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity{
    ImageView logo1;
    ImageView logo2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            logo1 = (ImageView) findViewById(R.id.slogo1);
            logo2 = (ImageView) findViewById(R.id.slogo2);
            Animation animfade = AnimationUtils.loadAnimation(this, R.anim.fadein);
            Animation animfade2 = AnimationUtils.loadAnimation(this, R.anim.fadein);
            animfade.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            Animation animlogo1 = AnimationUtils.loadAnimation(this, R.anim.animlogo1);
            Animation animlogo2 = AnimationUtils.loadAnimation(this, R.anim.animlogo2);
            animlogo2.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    logo1.startAnimation(animfade2);
                    logo2.startAnimation(animfade);
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            logo1.startAnimation(animlogo1);
            logo2.startAnimation(animlogo2);

        }
}
