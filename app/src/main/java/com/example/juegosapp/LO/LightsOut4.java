package com.example.juegosapp.LO;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegosapp.MainActivity;
import com.example.juegosapp.data.GameDataHelper;
import com.example.juegosapp.R;
import com.example.juegosapp.data.RankingActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LightsOut4 extends AppCompatActivity {
    ImageButton[][] buttons;
    LOController controller;
    private SharedPreferences prefs;
    TextView textTimer;
    Timer timer;
    TimerTask timerTask;
    TextView textClicks;
    Double time = 0.0;
    GameDataHelper db;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights_out4);
        db = new GameDataHelper(this);
        prefs = getApplicationContext().getSharedPreferences("preferences", 0);
        textTimer = (TextView) findViewById(R.id.timer);
        textClicks = (TextView) findViewById(R.id.numClicks);
        timer = new Timer();
        buttons = new ImageButton[4][4];
        buttons[0][0] = (ImageButton)findViewById(R.id.imgBttn00);
        buttons[0][1] = (ImageButton)findViewById(R.id.imgBttn01);
        buttons[0][2] = (ImageButton)findViewById(R.id.imgBttn02);
        buttons[0][3] = (ImageButton)findViewById(R.id.imgBttn03);
        buttons[1][0] = (ImageButton)findViewById(R.id.imgBttn10);
        buttons[1][1] = (ImageButton)findViewById(R.id.imgBttn11);
        buttons[1][2] = (ImageButton)findViewById(R.id.imgBttn12);
        buttons[1][3] = (ImageButton)findViewById(R.id.imgBttn13);
        buttons[2][0] = (ImageButton)findViewById(R.id.imgBttn20);
        buttons[2][1] = (ImageButton)findViewById(R.id.imgBttn21);
        buttons[2][2] = (ImageButton)findViewById(R.id.imgBttn22);
        buttons[2][3] = (ImageButton)findViewById(R.id.imgBttn23);
        buttons[3][0] = (ImageButton)findViewById(R.id.imgBttn30);
        buttons[3][1] = (ImageButton)findViewById(R.id.imgBttn31);
        buttons[3][2] = (ImageButton)findViewById(R.id.imgBttn32);
        buttons[3][3] = (ImageButton)findViewById(R.id.imgBttn33);
        start();
    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imgBttn00:
                controller.click(0,0);
                break;
            case R.id.imgBttn01:
                controller.click(0,1);
                break;
            case R.id.imgBttn02:
                controller.click(0,2);
                break;
            case R.id.imgBttn03:
                controller.click(0,3);
                break;
            case R.id.imgBttn10:
                controller.click(1,0);
                break;
            case R.id.imgBttn11:
                controller.click(1,1);
                break;
            case R.id.imgBttn12:
                controller.click(1,2);
                break;
            case R.id.imgBttn13:
                controller.click(1,3);
                break;
            case R.id.imgBttn20:
                controller.click(2,0);
                break;
            case R.id.imgBttn21:
                controller.click(2,1);
                break;
            case R.id.imgBttn22:
                controller.click(2,2);
                break;
            case R.id.imgBttn23:
                controller.click(2,3);
                break;
            case R.id.imgBttn30:
                controller.click(3,0);
                break;
            case R.id.imgBttn31:
                controller.click(3,1);
                break;
            case R.id.imgBttn32:
                controller.click(3,2);
                break;
            case R.id.imgBttn33:
                controller.click(3,3);
                break;
            case R.id.imgBttn40:
                controller.click(4,0);
                break;
            case R.id.imgBttn41:
                controller.click(4,1);
                break;
            case R.id.imgBttn42:
                controller.click(4,2);
                break;
            case R.id.imgBttn43:
                controller.click(4,3);
                break;
            case R.id.newButton:
                controller = new LOController(buttons, textClicks);
                while (controller.win()){
                    controller = new LOController(buttons, textClicks);
                }
                resetTimer();
                startTimer();
                break;
            case R.id.retryButton:
                controller.retryBoard();
                resetTimer();
                startTimer();
                break;
            case R.id.hintButton:
                controller.hint();
                break;
        }
        if (controller.win()) {
            timerTask.cancel();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You Win! Your time was "+getTimerText());
            builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = prefs.getString("user",null);
                    db.newScore(name, getTimerText(), controller.getNumClicks(), "LO");
                    Intent intent = new Intent(getBaseContext(), RankingActivity.class);
                    intent.putExtra("GAME", "LO");
                    startActivity(intent);
                }
            });
            builder.show();
        }
        controller.updateView();
    }

    private void startTimer() {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        textTimer.setText(getTimerText());
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);
    }

    private String getTimerText()
    {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }
    private void start() {
        controller = new LOController(buttons, textClicks);
        startTimer();
    }
    private void resetTimer() {
        if(timerTask != null) {
            timerTask.cancel();
            time = 0.0;
            textTimer.setText(formatTime(0,0,0));
        }
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d",hours) + " : " + String.format("%02d",minutes) + " : " + String.format("%02d",seconds);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_games, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu_item:
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.scores_menu_item:
                Intent intent2 = new Intent(getBaseContext(), RankingActivity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}