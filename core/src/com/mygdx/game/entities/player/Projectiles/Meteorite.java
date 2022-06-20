package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class Meteorite extends Projectile {
    private final static EntityManager ent = EntityManager.getINSTANCE();

    public Meteorite(float x, float y) {
        super(x, y, 64, 64, 1, Team.PlayerProjectiles, 0);
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.draw("meteor" + (Game.Time()%3),x, y, 2);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Meteorite(x, y);
    }

    @Override
    public void onDestroy() {
        // TODO : sound
        // TODO : screenshake
        ent.spawnEntity("explosion",x - 32,y - 48);
        DrawingManager.getINSTANCE().addScreenShake(16);
    }

    @Override
    protected void onEnemyHit(Enemy other) {
        destroy();
        other.takeDamage(4);
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        this.x += 12;
        this.y -= 28;
        if (lvl.collidesWithLevel(this)) destroy();
    }
}
