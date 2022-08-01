package com.mygdx.game.entities.player.itemEntities;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class MicroBomb extends Entity {

    private final long explodeTime;
    private final static long explosionDelay = 64;
    private float xM;
    private float yM;
    private boolean landed = false;
    private final static float gravity = 1f;

    public MicroBomb(float x, float y) {
        super(x, y, 32, 32, 1, Team.PlayerProjectiles);
        explodeTime = Game.Time() + explosionDelay + Game.getGeneralRandom().nextInt(32);
        xM = (float) Math.random() * 12 - 6;
        yM = (float) Math.random() * 8 + 3;
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (Game.Time() >= explodeTime) destroy();

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
    public void draw(DrawingManager spr) {
        spr.drawGame("minibomb" + ((Game.Time()/8)%2),x,y,2);
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new MicroBomb(x, y);
    }

    @Override
    public void onDestroy() {
        EntityManager.getINSTANCE().spawnEntity("micro explosion", x - 16, y-16);
        SoundManager.getINSTANCE().playSound("enemyDeath1",0.5f);
    }
}
