package com.mygdx.game.logic.level;

public class TileActivator {
    private float x;
    private int id;

    public TileActivator(float x, int id){
        this.x = x;
        this.id = id;
    }

    public void advance(){
        this.x += 64;
    }
    
    public float getX(){
        return this.x;
    }

    public void shiftBy(float number){
        this.x -= number;
    }
    
    public int getId(){
        return this.id;
    }
    
}
