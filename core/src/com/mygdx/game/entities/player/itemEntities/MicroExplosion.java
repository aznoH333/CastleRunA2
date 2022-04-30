package com.mygdx.game.entities.player.itemEntities;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class MicroExplosion extends Projectile {
    private final long deletionTime;
    public MicroExplosion(float x, float y) {
        super(x, y, 32, 32, 1, Team.PlayerProjectiles, 0);
        deletionTime = Game.Time() + 16;
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.drawGame("miniexplosion" + (5 -(long)(((double)(deletionTime - Game.Time())/16)*6)),x,y);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new MicroExplosion(x,y);
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
        if (Game.Time() >= deletionTime) destroy();
    }
}
