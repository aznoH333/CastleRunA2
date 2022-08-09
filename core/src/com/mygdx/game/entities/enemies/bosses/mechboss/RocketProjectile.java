package com.mygdx.game.entities.enemies.bosses.mechboss;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.PlayerStats;

import java.util.Random;

public class RocketProjectile extends Entity {

    private float targetX = 0;
    private float targetY = 0;
    private final static float desiredHeight = 1000;
    private boolean deploying = true;
    private final static PlayerStats ps = PlayerStats.getINSTANCE();
    private final static ParticleManager pm = ParticleManager.getINSTANCE();
    private final static float speed = 12f;

    public RocketProjectile(float x, float y) {
        super(x, y, 64, 64, 1, Team.EnemyProjectiles);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (deploying && y < desiredHeight){
            y += speed;
            targetX = ps.getPlayer().getX();
            targetY = ps.getPlayer().getY();
        }else if (x > targetX){
            deploying = false;
            x -= speed;
        }else {
            y -= speed;
            if (lvl.collidesWithLevel(this)){
                destroy();
            }
        }

        if (Game.Time() % 2 == 0) pm.addParticle("smoke",x,y,0,0,0);
    }

    @Override
    public void draw(DrawingManager spr) {
        // TODO : sprites
        spr.draw("player0",x, y, 1);
        spr.draw("player0",targetX, targetY, 1);
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new RocketProjectile(x, y);
    }

    @Override
    public void onDestroy() {

    }
}
