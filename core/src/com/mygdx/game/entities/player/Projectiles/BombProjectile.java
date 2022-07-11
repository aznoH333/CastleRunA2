package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class BombProjectile extends Entity {

    private int timer = 120;
    private float xM;
    private float yM;
    private final static float gravity = 1f;
    private boolean landed = false;

    public BombProjectile(float x, float y) {
        super(x, y, 64, 64, 1, Team.PlayerProjectiles);
        xM = 7;
        yM = 6;

    }

    @Override
    public void update(LevelManager lvl, Random r) {
        timer--;
        if (timer <= 0) destroy();

        if (!landed){
            x += xM;
            y += yM;
            yM -= gravity;


            if (lvl.collidesWithLevel(this)) {
                y = lvl.getLevelY(this);
                yM *= -0.5f;
                xM *= 0.5f;
                if (yM < 3){
                    landed = true;
                    this.y = lvl.getLevelY(this);
                }
            }
        }

    }

    @Override
    public void draw(DrawingManager spr) {
        if (timer > 80) spr.draw("bomb0", x, y, 1);
        else if (timer > 40) spr.draw("bomb1", x, y, 1);
        else {
            if (Game.Time()%40 > 20) spr.draw("bomb2", x, y, 1);
            else spr.draw("bomb3", x, y, 1);
        }
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new BombProjectile(x, y);
    }

    @Override
    public void onDestroy() {
        EntityManager.getINSTANCE().spawnEntity("explosion", x - 32,y - 48);
        DrawingManager.getINSTANCE().addScreenShake(20);
        SoundManager.getINSTANCE().playSound("enemyDeath1",0.5f);
        // TODO : sound
    }
}
