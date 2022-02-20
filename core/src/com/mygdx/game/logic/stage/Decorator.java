package com.mygdx.game.logic.stage;

public class Decorator {
    private final String sprite;
    private final int x;
    private final int y;
    public Decorator(String sprite, int x, int y){
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public String getSprite() {
        return sprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
