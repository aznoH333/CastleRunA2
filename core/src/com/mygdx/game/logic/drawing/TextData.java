package com.mygdx.game.logic.drawing;

public class TextData {
    private final float x;
    private final float y;
    private final String text;


    public TextData(String text, float x, float y){
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getText() {
        return text;
    }
}
