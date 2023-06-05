package com.example.juegosapp.DMCO;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.juegosapp.MainActivity;
import com.example.juegosapp.OnSwipeTouchListener;
import com.example.juegosapp.R;
import com.example.juegosapp.data.GameDataHelper;
import com.example.juegosapp.data.RankingActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Dosmilcuarentayocho3 extends AppCompatActivity{
    ImageView[][] cells;
    Button newGame;
    SharedPreferences prefs;
    DMCOController controller;
    TextView textTimer;
    TextView textClicks;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    LinearLayout layout;
    TextView score;
    GameDataHelper db;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosmilcuarentayocho3);
        prefs = getApplicationContext().getSharedPreferences("preferences", 0);
        textTimer = (TextView) findViewById(R.id.timer);
        timer = new Timer();
        cells = new ImageView[3][3];
        db = new GameDataHelper(this);
        score = (TextView)findViewById(R.id.score);
        newGame = (Button)findViewById(R.id.newGame);
        layout = (LinearLayout) findViewById(R.id.dmcolayout);
        cells[0][0] = (ImageView) findViewById(R.id.casilla00);
        cells[0][1] = (ImageView) findViewById(R.id.casilla01);
        cells[0][2] = (ImageView) findViewById(R.id.casilla02);
        cells[1][0] = (ImageView) findViewById(R.id.casilla10);
        cells[1][1] = (ImageView) findViewById(R.id.casilla11);
        cells[1][2] = (ImageView) findViewById(R.id.casilla12);
        cells[2][0] = (ImageView) findViewById(R.id.casilla20);
        cells[2][1] = (ImageView) findViewById(R.id.casilla21);
        cells[2][2] = (ImageView) findViewById(R.id.casilla22);
        controller = new DMCOController(cells);
        startTimer();
        layout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                controller.move("up");
                update();
            }

            public void onSwipeRight() {
                controller.move("right");
                update();
            }

            public void onSwipeLeft() {
                controller.move("left");
                update();
            }

            public void onSwipeBottom() {
                controller.move("down");
                update();
            }
        });
    }

    public void update() {
        score.setText(String.valueOf(controller.getScore()));
        controller.addRandomCell();
        if (controller.win()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You Win! Your score is "+String.valueOf(controller.getScore()));
            builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = prefs.getString("user",null);
                    db.newScore(name, getTimerText(), controller.getScore(), "DMCO");
                    Intent intent = new Intent(getBaseContext(), RankingActivity.class);
                    intent.putExtra("GAME", "DMCO");
                    startActivity(intent);
                }
            });
            builder.show();
            recreate();
        } else if (controller.lose()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You lost! Your score is "+String.valueOf(controller.getScore()));
            builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = prefs.getString("user",null);
                    db.newScore(name, getTimerText(), controller.getScore(), "DMCO");
                    Intent intent = new Intent(getBaseContext(), RankingActivity.class);
                    intent.putExtra("GAME", "DMCO");
                    startActivity(intent);

                }
            });
            builder.show();
            recreate();
        }
        //controller.updateView();
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.newGame:
                controller = new DMCOController(cells);
                resetTimer();
                startTimer();
                break;
            case R.id.stepBack:
                if (controller.getPlayedBoard() != null) {
                    controller.playBack();
                    controller.updateView();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "You can't stepback!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    public void animateX() {

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