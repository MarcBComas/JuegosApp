package com.example.juegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class LightsOut extends AppCompatActivity {
    ImageButton[][] buttons;
    LOController controller;
    Toast wtoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights_out);
        wtoast = Toast.makeText(this, "You won!", Toast.LENGTH_SHORT);
        buttons = new ImageButton[5][5];
        buttons[0][0] = (ImageButton)findViewById(R.id.imgBttn00);
        buttons[0][1] = (ImageButton)findViewById(R.id.imgBttn01);
        buttons[0][2] = (ImageButton)findViewById(R.id.imgBttn02);
        buttons[0][3] = (ImageButton)findViewById(R.id.imgBttn03);
        buttons[0][4] = (ImageButton)findViewById(R.id.imgBttn04);
        buttons[1][0] = (ImageButton)findViewById(R.id.imgBttn10);
        buttons[1][1] = (ImageButton)findViewById(R.id.imgBttn11);
        buttons[1][2] = (ImageButton)findViewById(R.id.imgBttn12);
        buttons[1][3] = (ImageButton)findViewById(R.id.imgBttn13);
        buttons[1][4] = (ImageButton)findViewById(R.id.imgBttn14);
        buttons[2][0] = (ImageButton)findViewById(R.id.imgBttn20);
        buttons[2][1] = (ImageButton)findViewById(R.id.imgBttn21);
        buttons[2][2] = (ImageButton)findViewById(R.id.imgBttn22);
        buttons[2][3] = (ImageButton)findViewById(R.id.imgBttn23);
        buttons[2][4] = (ImageButton)findViewById(R.id.imgBttn24);
        buttons[3][0] = (ImageButton)findViewById(R.id.imgBttn30);
        buttons[3][1] = (ImageButton)findViewById(R.id.imgBttn31);
        buttons[3][2] = (ImageButton)findViewById(R.id.imgBttn32);
        buttons[3][3] = (ImageButton)findViewById(R.id.imgBttn33);
        buttons[3][4] = (ImageButton)findViewById(R.id.imgBttn34);
        buttons[4][0] = (ImageButton)findViewById(R.id.imgBttn40);
        buttons[4][1] = (ImageButton)findViewById(R.id.imgBttn41);
        buttons[4][2] = (ImageButton)findViewById(R.id.imgBttn42);
        buttons[4][3] = (ImageButton)findViewById(R.id.imgBttn43);
        buttons[4][4] = (ImageButton)findViewById(R.id.imgBttn44);
        controller = new LOController(buttons);

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
            case R.id.imgBttn04:
                controller.click(0,4);
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
            case R.id.imgBttn14:
                controller.click(1,4);
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
            case R.id.imgBttn24:
                controller.click(2,4);
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
            case R.id.imgBttn34:
                controller.click(3,4);
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
            case R.id.imgBttn44:
                controller.click(4,4);
                break;
            case R.id.newButton:
                controller = new LOController(buttons);
                while (controller.win()){
                    controller = new LOController(buttons);
                }
                controller.updateView();
                break;
        }
        if (controller.win()) {
            wtoast.show();
        }
        controller.updateView();
    }
}