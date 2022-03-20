package com.mygdx.game.entities.player.Projectiles;


import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class ChargedAxe extends Axe{

    private static final float xM = 12f;
    private float yM = 15f;

    public ChargedAxe(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (pierceTimer > 0) pierceTimer--;
        yM -= gravity;
        x += xM;
        y += this.yM;
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new ChargedAxe(x, y, xSize, ySize, hp);
    }

}