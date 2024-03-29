package com.example.juegosapp.DMCO;

import android.view.View;
import android.widget.ImageView;

import com.example.juegosapp.R;

public class DMCOController {
    DMCOBoard board;
    DMCOBoard playedBoard;
    ImageView[][] images;
    int score;
    int backupScore;

    public DMCOController(ImageView[][] imgArray) {
        images = imgArray;
        this.score = 0;
        this.backupScore = 0;
        playedBoard = null;
        board = new DMCOBoard(this.images.length, this.images[0].length, this);
        board.addRandomCell();
        updateView();
    }

    public void move(String movement) {
        backupScore = score;
        playedBoard = new DMCOBoard(board);
        switch (movement) {
            case "up":
                score += board.moveUp(images);
                break;
            case "down":
                score += board.moveDown(images);
                break;
            case "left":
                score += board.moveLeft(images);
                break;
            case "right":
                score += board.moveRight(images);
                break;
        }
    }

    public void playBack() {
        board = new DMCOBoard(playedBoard);
        score = backupScore;
        playedBoard = null;
    }

    public int getScore() {
        return score;
    }

    public DMCOBoard getPlayedBoard() {
        return playedBoard;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateView(){
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                switch (board.getPos(i, j).getValue()) {
                    case 0:
                        images[i][j].setImageResource(R.drawable.casilla_vacia);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        images[i][j].setImageResource(R.drawable.casilla2);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        images[i][j].setImageResource(R.drawable.casilla4);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        images[i][j].setImageResource(R.drawable.casilla8);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 16:
                        images[i][j].setImageResource(R.drawable.casilla16);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 32:
                        images[i][j].setImageResource(R.drawable.casilla32);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 64:
                        images[i][j].setImageResource(R.drawable.casilla64);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 128:
                        images[i][j].setImageResource(R.drawable.casilla128);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 256:
                        images[i][j].setImageResource(R.drawable.casilla256);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 512:
                        images[i][j].setImageResource(R.drawable.casilla512);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 1024:
                        images[i][j].setImageResource(R.drawable.casilla1024);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                    case 2048:
                        images[i][j].setImageResource(R.drawable.casilla2048);
                        images[i][j].setVisibility(View.VISIBLE);
                        break;
                }
            }
        }
    }


    public boolean win() {return board.win();}

    public boolean lose() {return board.isGameOver();}

    public void addRandomCell() {board.addRandomCell();}

}
