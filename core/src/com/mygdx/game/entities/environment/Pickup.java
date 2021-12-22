package com.mygdx.game.entities.environment;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public abstract class Pickup extends Entity {
    public Pickup(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.Environment);
        xM = (float) Math.random() * 4 - 2;
        yM = (float) Math.random() * 8 + 3;
    }

    private float xM;
    private float yM;
    private boolean landed = false;
    private final static float gravity = 1f;
    @Override
    public void update(LevelManager lvl, Random r) {
        if (!landed){

            x += xM;
            y += yM;
            yM -= gravity;
            float lvlY = lvl.getOnPos(x + (lvl.getTileScale()/2)).getY() + lvl.getTileScale();

            if (y+ySize <= lvlY + ySize) {
                landed = true;
                y = lvlY;
                yM = 0;
                xM = 0;
            }
        }
    }

    @Override
    public abstract void draw(SpriteManager spr);

    @Override
    public void onCollide(Entity other){
        if (other.getTeam() == Team.Player && landed)
            destroy();
    }

    @Override
    public abstract Entity getCopy(float x, float y);

    @Override
    public abstract void onDestroy();
}
