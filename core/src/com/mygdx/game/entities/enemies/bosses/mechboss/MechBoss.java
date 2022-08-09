package com.mygdx.game.entities.enemies.bosses.mechboss;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class MechBoss extends Enemy {


    private final float defaultY;

    private boolean moving = false;

    // limb positions
    private float frontLegX = 0;
    private float frontLegY = 0;

    private float backLegX = 0;
    private float backLegY = 0;


    // timers
    private int idleFor = 80;


    // variables
    private final static Random r = Game.getGeneralRandom();
    private final static float moveSpeed = 2.3f;

    public MechBoss(float x, float y) {
        super(x, y, 124, 152, 60, Team.Enemies);
        defaultY = y;
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        final long t = Game.Time();

        // body idle
        if (!moving){
            y = ((float) Math.sin((double) t / 12) * 2) + defaultY;
            backLegY = 0;
            frontLegY = 0;
            frontLegX = 0;
            backLegX = 0;

            // move to random position
            if (idleFor == 0){
                moving = true;
                moveTo = r.nextInt((int)Game.gameWorldWidth/2 - 90) + Game.gameWorldWidth/2;
            }
        }



        // move
        if (moving){
            if (x > moveTo - 10 && x < moveTo + 10) {
                moving = false;
                idleFor = r.nextInt(60) + 60;
            }

            y = ((float) Math.sin((double) t / 4) * 4) + defaultY;
            frontLegY = Math.max(((float) Math.sin((double) t / 4) * 16), 0);
            backLegY = Math.max(((float) Math.sin(((double) t  / 4) + Math.PI) * 16), 0);

            frontLegX = (float) Math.cos((double) t / 4) * 12 * -Math.signum(moveTo - x);
            backLegX = (float) Math.cos(((double) t  / 4) + Math.PI) * 12 * -Math.signum(moveTo - x);
        }
        if (moveTo > x) x+= moveSpeed;
        if (moveTo < x) x-= moveSpeed;


        // subtract timers
        if (idleFor > 0 && !moving) idleFor--;
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.draw("mech3", x + frontLegX, defaultY + frontLegY, 3);
        spr.draw("mech1", x ,y , 2);
        spr.draw("mech4", x + backLegX, defaultY + backLegY, 1);
        spr.draw("mech2", x, y, 1);
        spr.draw("mech0", x, y, 3);
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new MechBoss(x, y);
    }

    @Override
    public void onDestroy() {

    }
}
