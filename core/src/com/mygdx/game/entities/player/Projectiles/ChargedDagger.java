package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.entities.ParticleManager;

import java.util.Random;

public class ChargedDagger extends Dagger {
    private float yM = 2;
    private int particleTimer = 0;
    private static final int particleTimerMax = 4;

    public ChargedDagger(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        float speed = 18f;
        float fallSpeed = 0.5f;

        x += speed;
        y += yM;

        yM -= fallSpeed;
        //delete if out of bounds
        if (x >= (lvl.getMapWidth()-1) * lvl.getTileScale()) destroy();
        else{
            float lvlY = lvl.getOnPos(x + (lvl.getTileScale() - 1)).getY() + lvl.getTileScale();
            //detect ground collision
            if (lvlY - lvl.getTileScale() > y - ySize) destroy();
        }

        //particles
        if(particleTimer == 0){
            particleTimer = particleTimerMax;
            ParticleManager.getINSTANCE().addParticle("sparkle",x,y,0,-0.5f,0);
        }else particleTimer--;

    }


    @Override
    public Entity getCopy(float x, float y) {
        return new ChargedDagger(x,y,xSize,ySize,hp);
    }

}
