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

    private float targetY = 0;
    private final static float desiredHeight = 1000;
    private boolean deploying = true;
    private final static PlayerStats ps = PlayerStats.getINSTANCE();
    private final static ParticleManager pm = ParticleManager.getINSTANCE();
    private final static float speed = 12f;

    public RocketProjectile(float x, float y) {
        super(x, y, 32, 32, 1, Team.EnemyProjectiles);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (deploying && y < desiredHeight){
            y += speed;
            moveTo = ps.getPlayer().getX() + xSize;
            targetY = ps.getPlayer().getY();
        }else if (x > moveTo){
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

        if (deploying){
            spr.draw("rocket1",x, y, 1);
        }else if (x > moveTo){
            spr.draw("rocket0",x, y, 1);
        }else {
            spr.draw("rocket2",x, y, 1);
        }
        if (deploying){
            spr.draw("tcross" + ((Game.Time() >> 2) % 2), moveTo - xSize, targetY, 2);

        }else{
            spr.draw("tcross0", moveTo - xSize, targetY, 0);

        }
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
        e.spawnEntity("micro explosion", x - xSize, y);
        DrawingManager.getINSTANCE().addScreenShake(10);
    }
}
