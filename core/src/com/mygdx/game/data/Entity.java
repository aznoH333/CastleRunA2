package com.mygdx.game.data;

import com.mygdx.game.managers.Level;
import com.mygdx.game.managers.SpriteManager;

import java.util.Random;

public abstract class Entity {
    protected float x;
    protected float y;
    protected float xSize;
    protected float ySize;
    protected int hp;
    protected float moveTo;

    public Entity(float x, float y, float xSize, float ySize, int hp){
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
        this.hp = hp;
        this.moveTo = x;
    }

    //abstract methods
    public abstract void update(Level lvl, Random r);
    public abstract void draw(SpriteManager spr);

    //collision will probably
    public boolean collide(Entity other){
        return (x + xSize > other.getX() && x < other.getX() && other.getY() < y + ySize && y < other.getY());
    }
    // position stuff
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getXSize() {
        return xSize;
    }
    public float getySize(){
        return ySize;
    }
    public void shiftX(float shiftBy){
        x += shiftBy;
        moveTo += shiftBy;
    }

    //hp stuff
    public int getHP(){
        return hp;
    }
    public void takeDamage(int damage){
        hp -= damage;
    }
}