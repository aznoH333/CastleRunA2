package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class SwordSwipe extends Projectile {

    protected int lifeTime = 12;
    protected boolean hurts = true;
    public SwordSwipe(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles,1);
    }

    @Override
    protected void onEnemyHit(Enemy other) {
        if (hurts){
            other.takeDamage(1);
            hurts = false;
        }
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        lifeTime--;
        if (lifeTime == 0) destroy();
    }


    @Override
    public void draw(DrawingManager spr) {
        // me when bruh

        if (lifeTime>0)
            spr.drawGame("sword" + (int) Math.ceil((13 - lifeTime) >> 1),x,y,1);

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new SwordSwipe(x,y,64,64,1);
    }

    @Override
    public void onDestroy() {

    }
}
