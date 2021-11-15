package com.mygdx.game.entities;

import com.mygdx.game.data.Entity;
import com.mygdx.game.managers.LevelManager;
import com.mygdx.game.managers.SpriteManager;

import java.util.Random;

public class Slime extends Entity {
    // constants
    private final int animationSpeed = 32;
    private final float gravity = 0.5f;
    private final float hopStrength = 3.5f;
    private final int jumpTime = 60;
    private final float moveSpeed = 4f;

    // vars
    private int jumpTimer = 0;
    private boolean landed = false;
    private float lvlY;
    private float yM = 0;
    private boolean direction = false;

    public Slime(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (landed) jumpTimer++;
        lvlY = lvl.getOnPos(x + (lvl.getTileScale() -1)).getY() + lvl.getTileScale();

        //land
        landed = y <= lvlY - yM && yM <= 0;
        if (landed){
            yM = 0;
            y = lvlY;
        }else{
            yM -= gravity;
        }
        //hop
        if (jumpTimer > jumpTime){
            jumpTimer = 0;
            yM = hopStrength;
            if (direction)  moveTo = x + lvl.getTileScale();
            else            moveTo = x - lvl.getTileScale();
            direction = !direction;
        }


        //update positions
        if (moveTo > x) x += moveSpeed;
        if (moveTo < x) x -= moveSpeed;
        y += yM;
    }

    @Override
    public void draw(SpriteManager spr) {
        if (landed)
            if (jumpTimer%animationSpeed > animationSpeed/2)
                spr.draw("slime0",x,y);
            else
                spr.draw("slime1",x,y);
        else
            spr.draw("slime2",x,y);
    }

    @Override
    public void onCollide(Entity other) {
        System.out.println(other.getClass() + " : " + other.getX() + ", " + other.getY() + " | " + other.getXSize() + ", " + other.getYSize());
        destroy();
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Slime(x,y + ySize, xSize, ySize, hp);
    }
}
