package bananagrams;

import javafx.scene.control.Button;

public class Tile extends Button{

    boolean type;		//true if letter false if board tile
    int x,y;			//x and y cords when placed on board
    char letter;
    boolean played = false;

    public Tile(char c) {		//letter tile
        type = true;
        letter = c;
        x=-1;
        y=-1;
        super.setText(c+"");
    }

    public Tile(int a,int b) {		//board tile
        type = false;
        x=a;
        y=b;
    }

    public boolean isLetter() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char c) {
        letter = c;
    }

    public void play() {
        played = true;
    }

    public boolean getPlayed() {
        return played;
    }

    public void setPlayed(boolean b) {
        played=b;
    }

    public void setCoords(int a, int b) {
        x=a;
        y=b;
    }
}