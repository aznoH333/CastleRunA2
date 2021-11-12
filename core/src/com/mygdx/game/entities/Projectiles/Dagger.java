package com.mygdx.game.entities.Projectiles;

import com.mygdx.game.data.Entity;
import com.mygdx.game.managers.LevelManager;
import com.mygdx.game.managers.SpriteManager;

import java.util.Random;

public class Dagger extends Entity {
    private final float speed = 12f;
    private float lvlY;

    public Dagger(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        x += speed;
        lvlY = lvl.getOnPos(x + (lvl.getTileScale() -1)).getY() + lvl.getTileScale();
        //detect ground collision
        if (lvlY > y) takeDamage(1);
    }

    @Override
    public void draw(SpriteManager spr) {
        spr.draw("dagger1",x,y);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Dagger(x,y,xSize,ySize,hp);
    }
}
