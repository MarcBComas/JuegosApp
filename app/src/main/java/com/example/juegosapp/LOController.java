package com.example.juegosapp;

import android.view.View;
import android.widget.ImageButton;

public class LOController {
    LOBoard board;
    LOBoard copyBoard;
    ImageButton[][] buttons;

    public LOController(ImageButton[][] bArray) {
        this.buttons = bArray;
        this.board = new LOBoard(this.buttons.length, this.buttons[0].length);
        board.randomize();
        this.copyBoard = new LOBoard(board);
        updateView();
    }

    public void updateView(){
        for (int i = 0; i <buttons.length; i++){
            for (int j = 0; j < buttons[i].length; j++) {
                if (board.getPos(i,j).isOn()) {
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
        updateView();
    }

    public void retryBoard(){
        board = copyBoard;
        copyBoard = new LOBoard(copyBoard);
    }

    public boolean win(){
        return this.board.win();
    }
}
