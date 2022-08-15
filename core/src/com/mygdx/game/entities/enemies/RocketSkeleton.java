package com.mygdx.game.entities.enemies;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class RocketSkeleton extends Enemy {
    public RocketSkeleton(float x, float y) {
        super(x, y, 64, 128, 4, Team.Enemies);
    }


    //private String sprite = "goblin0";
    private int hopTimer = 0;
    private final static int timerMax = 100;
    private int attackTimer = 300;
    private float yM = 0;
    private boolean landed = false;
    private int danceAnimation = 0;
    private boolean danceToggle = true;

    @Override
    public void update(LevelManager lvl, Random r) {
        float lvlY = lvl.getLevelY(this);
        landed = y <= lvlY - yM && yM <= 0;
        if (landed) {
            yM = 0;
            y = lvlY;

            if (attackTimer == 0) {
                e.spawnEntity("rocket", x, y + 64);
                attackTimer = 200;
            }
            attackTimer--;

        } else {
            yM -= 0.8f;
        }


        //jump
        if (hopTimer == 0) {
            hopTimer = timerMax;
            boolean direction = r.nextBoolean();

            if (lvl.getOnPos(x - (lvl.getTileScale() >> 1)).getSpecial() != TileCollumSpecial.Gap && direction) {
                moveTo = x - lvl.getTileScale();
                yM = 4;

            } else if (lvl.getOnPos(x + xSize + (lvl.getTileScale() >> 1)).getSpecial() != TileCollumSpecial.Gap && !direction) {
                moveTo = x + lvl.getTileScale();
                yM = 4;
            }
            // FIXME sometimes rocket skeletons and saw knight just kill themselves by jumping of cliffs
        }

        if (moveTo > x) x += 8;
        if (moveTo < x) x -= 8;
        y += yM;
        if (landed) hopTimer--;


        if (Game.Time()%20 == 0) {
            if (danceToggle){
                danceAnimation++;
                if (danceAnimation == 2) danceToggle = false;
            }else {
                danceAnimation--;
                if (danceAnimation == 0) danceToggle = true;
            }
        }
    }

    @Override
    public void draw(DrawingManager spr) {
        if (landed){
            spr.draw("rocket_skeleton" + danceAnimation,x ,y, 1);
        }else {
            spr.draw("rocket_skeleton3",x ,y, 1);
        }
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new RocketSkeleton(x, y + 64);
    }

    @Override
    public void onDestroy() {

    }
}
