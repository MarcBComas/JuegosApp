package com.example.juegosapp;

import android.view.View;
import android.widget.ImageView;

public class DMCOController {
    DMCOBoard board;
    ImageView[][] images;

    public DMCOController(ImageView[][] imgArray) {
        images = imgArray;
        board = new DMCOBoard(this.images.length, this.images[0].length);
        board.addRandomCell();
        updateView();
    }

    public void move(String movement) {
        switch (movement) {
            case "up":
                board.moveUp();
                break;
            case "down":
                board.moveDown();
                break;
            case "left":
                board.moveLeft();
                break;
            case "right":
                board.moveRight();
                break;
        }
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
