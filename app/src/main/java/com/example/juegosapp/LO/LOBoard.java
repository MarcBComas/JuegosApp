package com.example.juegosapp.LO;

public class LOBoard {
    private LOCell[][] board;
    private boolean hints;

    public LOBoard(int h, int w){
        this.createEmptyBoard(h, w);
    }
    public LOBoard(LOBoard copy){
        board = new LOCell[copy.getHeight()][copy.getWidth()];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new LOCell();
                board[i][j].setOn(copy.getPos(i,j).isOn());
                board[i][j].setClicked(copy.getPos(i,j).isClicked());
            }
        }
    }

    public LOCell getPos(int i, int j) {return board[i][j];}

    public int getHeight(){return this.board.length;}

    public int getWidth(){return this.board[0].length;}

    public void createEmptyBoard(int h, int w){
        board = new LOCell[h][w];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j <board[i].length; j++) {
                board[i][j] = new LOCell();
                board[i][j].setOn(false);
            }
        }
    }

    public void click(int h, int w){
        if (h >= board.length || w >= board[0].length || h < 0 || w <0) {
            throw new IllegalArgumentException();
        }
        board[h][w].light();
        board[h][w].click();
        if (hints) {
            hint();
        }
        if (h - 1 >= 0) {
            board[h - 1][w].light();
        }
        if (h + 1 < board.length) {
            board[h + 1][w].light();
        }
        if (w - 1 >= 0) {
            board[h][w - 1].light();
        }
        if (w + 1 < board[0].length) {
            board[h][w + 1].light();
        }
    }

    public void randomize(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++) {
                double lightpercent = 0.45;
                if (Math.random() < lightpercent) {
                    click(i,j);
                }
            }
        }
    }

    public boolean win(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isOn()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void triggerHint(LOBoard copy) {
        if (hints) {
            hint();
        } else {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (!getPos(i,j).isClicked() && copy.getPos(i,j).isClicked()) {
                        board[i][j].setHint(true);
                    } else if (getPos(i,j).isClicked() && !copy.getPos(i,j).isClicked()) {
                        board[i][j].setHint(true);
                    }
                }
            }
            hints = true;
        }
    }

    public void hint () {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setHint(false);
            }
        }
        hints = false;
    }

    public void flush(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setClicked(false);
            }
        }
    }
}
