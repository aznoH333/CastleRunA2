package com.mygdx.game.entities.environment;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
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


            if (lvl.collidesWithLevel(this)) {
                landed = true;
                y = lvl.getLevelY(this);
                yM = 0;
                xM = 0;
            }
        }
    }

    @Override
    public abstract void draw(DrawingManager spr);

    @Override
    public void onCollide(Entity other){
        if (other.getTeam() == Team.Player && landed){
            onPickup();
            destroy();
        }
    }

    @Override
    public abstract Entity getCopy(float x, float y);

    @Override
    public void onDestroy(){}

    public abstract void onPickup();
}
