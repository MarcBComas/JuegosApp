package com.example.juegosapp.DMCO;

import android.widget.ImageView;

public class DMCOBoard {
    DMCOCell[][] board;
    DMCOController DMCOController;
    public DMCOBoard(int h, int w, DMCOController controller){
        this.createEmptyBoard(h, w);
        this.DMCOController = controller;
    }

    public DMCOBoard(DMCOBoard copy) {
        this.DMCOController = copy.getController();
        board = new DMCOCell[copy.getHeight()][copy.getWidth()];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j <board[i].length; j++) {
                board[i][j] = new DMCOCell();
                board[i][j].setValue(copy.board[i][j].getValue());
            }
        }
    }

    public DMCOCell[][] getBoard() {
        return board;
    }

    public DMCOController getController() { return this.DMCOController;}
    public void setBoard(DMCOCell[][] board) {
        this.board = board;
    }

    public DMCOCell getPos(int i, int j) {return board[i][j];}

    public DMCOBoard createEmptyBoard(int h, int w){
        board = new DMCOCell[h][w];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j <board[i].length; j++) {
                board[i][j] = new DMCOCell();
                board[i][j].setValue(0);
            }
        }
        return this;
    }

    public int getHeight(){return this.board.length;}

    public int getWidth(){return this.board[0].length;}

    public int moveUp(ImageView[][] images) {
        int score = 0;
        for (int i = 1; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() != 0) {
                    for (int k = 0; k < i; k++) {
                        if(board[k][j].getValue() == board[i][j].getValue()){
                            animateY(images[i][j],images[k][j]);
                            board[k][j].setValue(board[k][j].getValue() * 2);
                            board[i][j].setValue(0);
                            score += 100;
                            break;
                        } else if (board[k][j].getValue() == 0) {
                            animateY(images[i][j],images[k][j]);
                            board[k][j].setValue(board[i][j].getValue());
                            board[i][j].setValue(0);
                            break;
                        }
                    }
                }
            }
        }
        return score;
    }

    public int moveDown(ImageView[][] images) {
        int score = 0;
        for (int i = board.length - 2; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() != 0) {
                    for (int k = board.length - 1; k > i; k--) {
                        if (board[k][j].getValue() == board[i][j].getValue()) {
                            animateY(images[i][j],images[k][j]);
                            board[k][j].setValue(board[i][j].getValue() * 2);
                            board[i][j].setValue(0);
                            score += 100;
                            break;
                        } else if (board[k][j].getValue() == 0) {
                            animateY(images[i][j],images[k][j]);
                            board[k][j].setValue(board[i][j].getValue());
                            board[i][j].setValue(0);
                            break;
                        }
                    }
                }
            }
        }
        return score;
    }

    public int moveLeft(ImageView[][] images) {
        int score = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j].getValue() != 0) {
                    for (int k = 0; k < j; k++) {
                        if (board[i][k].getValue() == board[i][j].getValue()) {
                            animateX(images[i][j],images[i][k]);
                            board[i][k].setValue(board[i][k].getValue() * 2);
                            board[i][j].setValue(0);
                            score += 100;
                            break;
                        }else if (board[i][k].getValue() == 0) {
                            animateX(images[i][j],images[i][k]);
                            board[i][k].setValue(board[i][j].getValue());
                            board[i][j].setValue(0);
                            break;
                        }
                    }
                }
            }
        }
        return score;
    }

    public int moveRight(ImageView[][] images) {
        int score = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = board[i].length - 2; j >= 0; j--) {
                if (board[i][j].getValue() != 0) {
                    for (int k = board[i].length - 1; k > j; k--) {
                        if (board[i][k].getValue() == board[i][j].getValue()) {
                            animateX(images[i][j],images[i][k]);
                            board[i][k].setValue(board[i][j].getValue() * 2);
                            board[i][j].setValue(0);
                            score += 100;
                            break;
                        }else if (board[i][k].getValue() == 0) {
                            animateX(images[i][j],images[i][k]);
                            board[i][k].setValue(board[i][j].getValue());
                            board[i][j].setValue(0);
                            break;
                        }
                    }
                }
            }
        }
        return score;
    }

    public boolean isGameOver(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j].getValue() == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean win(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addRandomCell(){
        if (!isGameOver() && !win()) {
            int i = (int) (Math.random() * board.length);
            int j = (int) (Math.random() * board[0].length);
            if(board[i][j].getValue() == 0){
                board[i][j].setValue(2);
            }else{
                addRandomCell();
            }
        }
    }

    public void animateX(ImageView cell1, ImageView cell2) {
        float x1 = cell1.getX();
        float x2 = cell2.getX();
        float res = x2 - x1;
        cell2.setElevation(2);
        cell1.animate().setDuration(300).translationX(res).withEndAction(new Runnable() {
            @Override
            public void run() {
                cell1.setX(x1);
                cell1.setElevation(0);
                DMCOController.updateView();
            }
        }).start();
    }

    public void animateY(ImageView cell1, ImageView cell2) {
        float y1 = cell1.getY();
        float y2 = cell2.getY();
        float res = y2 - y1;
        cell1.animate().setDuration(300).translationY(res).withEndAction(new Runnable() {
            @Override
            public void run() {
                cell1.setY(y1);
                cell1.setElevation(0);
                DMCOController.updateView();
            }
        }).start();
    }
}
