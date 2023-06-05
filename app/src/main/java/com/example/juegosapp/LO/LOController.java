package com.example.juegosapp.LO;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.juegosapp.R;

public class LOController {
    LOBoard board;
    LOBoard copyBoard;
    ImageButton[][] buttons;
    TextView textClicks;
    private int numClicks;

    public LOController(ImageButton[][] bArray, TextView textClicks) {
        this.buttons = bArray;
        this.textClicks = textClicks;
        this.board = new LOBoard(this.buttons.length, this.buttons[0].length);
        board.randomize();
        this.copyBoard = new LOBoard(board);
        numClicks = 0;
        updateView();
        board.flush();
    }

    public void updateView(){
        textClicks.setText("Pulsaciones: "+numClicks);
        for (int i = 0; i <buttons.length; i++){
            for (int j = 0; j < buttons[i].length; j++) {
                if (board.getPos(i,j).isHint()) {
                    buttons[i][j].setImageResource(R.drawable.hint);
                    buttons[i][j].setVisibility(View.VISIBLE);
                } else if (board.getPos(i,j).isOn()) {
                    buttons[i][j].setImageResource(R.drawable.lighton);
                    buttons[i][j].setVisibility(View.VISIBLE);
                } else if (!board.getPos(i,j).isOn()) {
                    buttons[i][j].setImageResource(R.drawable.lightoff);
                    buttons[i][j].setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void click(int i, int j){
        board.click(i,j);
        numClicks++;
        updateView();
    }

    public void retryBoard(){
        board = copyBoard;
        copyBoard = new LOBoard(copyBoard);
        updateView();
    }

    public void hint(){
        board.triggerHint(copyBoard);
        updateView();
    }

    public int getNumClicks() {
        return numClicks;
    }

    public boolean win(){
        return this.board.win();
    }
}
