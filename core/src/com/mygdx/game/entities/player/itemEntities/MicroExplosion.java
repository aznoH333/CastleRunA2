package com.mygdx.game.entities.player.itemEntities;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class MicroExplosion extends Projectile {
    public MicroExplosion(float x, float y, float xSize, float ySize) {
        super(x, y, xSize, ySize, 1, Team.PlayerProjectiles, 16);
    }

    @Override
    public void draw(DrawingManager spr) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new MicroExplosion(x,y,xSize,ySize);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    protected void onEnemyHit(Enemy other) {
        other.takeDamage(1);
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        
    }
}
