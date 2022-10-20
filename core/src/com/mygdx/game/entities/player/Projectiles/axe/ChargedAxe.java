package com.mygdx.game.entities.player.Projectiles.axe;


import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class ChargedAxe extends Axe {

    private static final float xM = 8;
    private float yM = 12f;

    public ChargedAxe(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }


    @Override
    public void projectileUpdate(LevelManager lvl, Random r) {
        yM -= gravity;
        x += xM;
        y += this.yM;
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new ChargedAxe(x, y, xSize, ySize, hp);
    }

}
