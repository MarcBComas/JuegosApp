package com.example.juegosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Dosmilcuarentayocho extends AppCompatActivity{
    ImageView[][] cells;
    Button newGame;
    DMCOController controller;
    Toast wtoast;
    Toast ltoast;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosmilcuarentayocho);
        wtoast = Toast.makeText(this, "You won!", Toast.LENGTH_SHORT);
        ltoast = Toast.makeText(this, "You lost!", Toast.LENGTH_SHORT);
        cells = new ImageView[4][4];
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
                controller.addRandomCell();
                controller.updateView();
                if (controller.win()) {
                    wtoast.show();
                }
            }

            public void onSwipeRight() {
                controller.move("right");
                controller.addRandomCell();
                controller.updateView();
                if (controller.win()) {
                    wtoast.show();
                }
            }

            public void onSwipeLeft() {
                controller.move("left");
                controller.addRandomCell();
                controller.updateView();
                if (controller.win()) {
                    wtoast.show();
                } else if (controller.lose()) {
                    ltoast.show();
                }
            }

            public void onSwipeBottom() {
                controller.move("down");
                controller.addRandomCell();
                controller.updateView();
                if (controller.win()) {
                    wtoast.show();
                }
            }
        });
    }

    public void onClick(View v) {
        controller = new DMCOController(cells);
    }
}