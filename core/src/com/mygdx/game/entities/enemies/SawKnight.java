package com.mygdx.game.entities.enemies;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class SawKnight extends Enemy {
    public SawKnight(float x, float y) {
        super(x, y, 64, 128, 5, Team.Enemies);
    }

    private int hopTimer = 0;
    private final static int timerMax = 200;
    private int attackTimer = 300;
    private boolean direction = true;
    private float yM = 0;

    @Override
    public void update(LevelManager lvl, Random r) {
        float lvlY = lvl.getLevelY(this);
        boolean landed = y <= lvlY - yM && yM <= 0;
        if (landed) {
            yM = 0;
            y = lvlY;

            if (attackTimer == 0) {
                e.spawnEntity("sawblade", x, y + 64);
                attackTimer = 250;
            }
            attackTimer--;

        } else {
            yM -= 0.8f;
        }


        //jump
        if (hopTimer == 0) {
            hopTimer = timerMax;
            direction = r.nextBoolean();

            if (lvl.getOnPos(x - (lvl.getTileScale() >> 1)).getSpecial() != TileCollumSpecial.Gap && direction) {
                moveTo = x - lvl.getTileScale();
                yM = 4;

            } else if (lvl.getOnPos(x + xSize + (lvl.getTileScale() >> 1)).getSpecial() != TileCollumSpecial.Gap) {
                moveTo = x + lvl.getTileScale();
                yM = 4;
            }

        }

        if (moveTo > x) x += 8;
        if (moveTo < x) x -= 8;
        y += yM;
        if (landed) hopTimer--;
    }

    @Override
    public void draw(DrawingManager spr) {
        // TODO : sprite
        spr.draw("player0", x, y, 1);
        spr.draw("player0", x, y + 64, 1);
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new SawKnight(x, y);
    }

    @Override
    public void onDestroy() {

    }
}
