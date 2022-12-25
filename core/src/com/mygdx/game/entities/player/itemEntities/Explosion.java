package com.mygdx.game.entities.player.itemEntities;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class Explosion extends Projectile {
    private final long deletionTime;
    public Explosion(float x, float y) {
        super(x, y, 128, 128, 1, Team.PlayerProjectiles, 64);
        deletionTime = Game.Time() + 16;
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.draw("explosion" + (6 -(long)(((double)(deletionTime - Game.Time())/3.5))),x,y,2);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Explosion(x,y);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    protected void onEnemyHit(Enemy other) {
        other.takeDamage(2);
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        if (Game.Time() >= deletionTime) destroy();
    }
}