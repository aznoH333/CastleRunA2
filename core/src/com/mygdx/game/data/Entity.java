package com.mygdx.game.data;

import com.mygdx.game.managers.EntityManager;
import com.mygdx.game.managers.LevelManager;
import com.mygdx.game.managers.SpriteManager;

import java.util.Random;

public abstract class Entity {
    protected float x;
    protected float y;
    protected float xSize;
    protected float ySize;
    protected int hp;
    protected float moveTo;
    protected final EntityManager e;
    private boolean markForDestruction = false;

    public Entity(float x, float y, float xSize, float ySize, int hp){
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
        this.hp = hp;
        this.moveTo = x;

        e = EntityManager.getINSTANCE();
    }

    //abstract methods
    public abstract void update(LevelManager lvl, Random r);
    public abstract void draw(SpriteManager spr);
    public abstract void onCollide(Entity other);
    public abstract Entity getCopy(float x, float y);

    //collision will probably
    public boolean collide(Entity other){
        return (x - xSize < other.getX() && x > other.getX() - other.getXSize() && y - ySize < other.getY() && y > other.getY() - other.getYSize());
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
    public float getYSize(){
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

    // deleting
    public void destroy(){markForDestruction = true;}
    public boolean isMarked(){return markForDestruction;}
}