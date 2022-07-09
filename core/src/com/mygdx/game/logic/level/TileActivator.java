package com.mygdx.game.logic.level;

public class TileActivator {
    private float x;
    private int id;
    private final static int levelLength = LevelManager.getINSTANCE().getMapWidth() * LevelManager.tileScale;

    public TileActivator(float x){
        this.x = x;

    }

    public void advance(){
        this.x += 64;
        this.x %= levelLength;
        if (x < 0) x = levelLength + x; // apparently % doesn't work on negative numbers like its supposed to :[
    }
    
    public float getX(){
        return this.x;
    }

    public void shiftBy(float number){
        this.x -= number;
    }

    
}
