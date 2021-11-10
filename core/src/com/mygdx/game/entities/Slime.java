package com.mygdx.game.entities;

import com.mygdx.game.data.Entity;
import com.mygdx.game.managers.LevelManager;
import com.mygdx.game.managers.SpriteManager;

import java.util.Random;

public class Slime extends Entity {
    // constants
    private final int animationSpeed = 32;

    // vars
    private int jumpTimer = 0;

    public Slime(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        jumpTimer++;
    }

    @Override
    public void draw(SpriteManager spr) {
        if (jumpTimer%animationSpeed > animationSpeed/2)
            spr.draw("slime0",x,y);
        else
            spr.draw("slime1",x,y);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Slime(x,y + ySize, xSize, ySize, hp);
    }
}
