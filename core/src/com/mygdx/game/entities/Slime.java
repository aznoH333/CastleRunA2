package com.mygdx.game.entities;

import com.mygdx.game.data.Entity;
import com.mygdx.game.managers.Level;
import com.mygdx.game.managers.SpriteManager;

import java.util.Random;

public class Slime extends Entity {

    public Slime(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void update(Level lvl, Random r) {

    }

    @Override
    public void draw(SpriteManager spr) {
        spr.draw("player0",x,y);
    }

    @Override
    public Entity getCopy() {
        return new Slime(x,y, xSize, ySize, hp);
    }
}
