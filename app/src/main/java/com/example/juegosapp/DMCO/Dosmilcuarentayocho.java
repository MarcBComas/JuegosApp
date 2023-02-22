package com.example.juegosapp.DMCO;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegosapp.highscores.GameDataHelper;
import com.example.juegosapp.OnSwipeTouchListener;
import com.example.juegosapp.R;
import com.example.juegosapp.highscores.RankingActivity;

public class Dosmilcuarentayocho extends AppCompatActivity{
    ImageView[][] cells;
    Button newGame;
    DMCOController controller;
    Toast wtoast;
    Toast ltoast;
    LinearLayout layout;
    TextView score;
    GameDataHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosmilcuarentayocho);
        ltoast = Toast.makeText(this, "You lost!", Toast.LENGTH_SHORT);
        cells = new ImageView[4][4];
        db = new GameDataHelper(this);
        score = (TextView)findViewById(R.id.score);
        newGame = (Button)findViewById(R.id.newGame);
        layout = (LinearLayout) findViewById(R.id.dmcolayout);
        cells[0][0] = (ImageView) findViewById(R.id.casilla00);
        cells[0][1] = (ImageView) findViewById(R.id.casilla01);
        cells[0][2] = (ImageView) findViewById(R.id.casilla02);
        cells[0][3] = (ImageView) findViewById(R.id.casilla03);
        cells[1][0] = (ImageView) findViewById(R.id.casilla10);
        cells[1][1] = (ImageView) findViewById(R.id.casilla11);
        cells[1][2] = (ImageView) findViewById(R.id.casilla12);
        cells[1][3] = (ImageView) findViewById(R.id.casilla13);
        cells[2][0] = (ImageView) findViewById(R.id.casilla20);
        cells[2][1] = (ImageView) findViewById(R.id.casilla21);
        cells[2][2] = (ImageView) findViewById(R.id.casilla22);
        cells[2][3] = (ImageView) findViewById(R.id.casilla23);
        cells[3][0] = (ImageView) findViewById(R.id.casilla30);
        cells[3][1] = (ImageView) findViewById(R.id.casilla31);
        cells[3][2] = (ImageView) findViewById(R.id.casilla32);
        cells[3][3] = (ImageView) findViewById(R.id.casilla33);
        controller = new DMCOController(cells);
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
        controller.updateView();
        if (controller.win()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You Win! Write your name");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = input.getText().toString();
                    db.newScore(name, String.valueOf(controller.getScore()), "DMCO");
                    Intent intent = new Intent(getBaseContext(), RankingActivity.class);
                    intent.putExtra("GAME", "DMCO");
                    startActivity(intent);
                }
            });
            builder.show();
        } else if (controller.lose()) {
            ltoast.show();
        }
    }
    public void onClick(View v) {
        controller = new DMCOController(cells);
    }
}