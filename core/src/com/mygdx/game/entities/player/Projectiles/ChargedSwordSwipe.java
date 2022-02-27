package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class ChargedSwordSwipe extends SwordSwipe{

    private static final float xM = 5f;
    private int particleTimer = 0;
    private static final int particleTimerMax = 4;

    public ChargedSwordSwipe(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        x += xM;
        lifeTime--;
        if (lifeTime == 0) destroy();

        //particles
        if(particleTimer == 0){
            particleTimer = particleTimerMax;
            ParticleManager.getINSTANCE().addParticle("miniSparkle",x+16,y+10,-0.5f,0f,0f);
        }else particleTimer--;
        // TODO: smaller particles
    }


    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.Enemies && hurts){
            other.takeDamage(2);
            hurts = false;
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new ChargedSwordSwipe(x,y,xSize,ySize,hp);
    }
}
