package com.mygdx.game.entities.environment;

import com.mygdx.game.Game;

import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.entities.Entity;

import java.util.Random;

public class Coin extends Pickup{
    private byte animation = 0;
    private final byte animationOffset;
    private static final int animationLength = 4;
    private static final ParticleManager part = ParticleManager.getINSTANCE();

    public Coin(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
        animationOffset = (byte) (Math.random() * animationLength);
    }

    @Override
    public void update(LevelManager lvl, Random r){
        super.update(lvl, r);
        if (Game.Time() % 8 == 0 && r.nextInt(10) == 1)
            part.addParticle("coinSparkle",x +  r.nextInt((int)xSize) - 16,y + r.nextInt((int)ySize) - 16,0,0,0);
    }

    @Override
    public void draw(SpriteManager spr) {
        switch (animation){
            case 0:
                spr.drawGame("pickup1",x,y,1);
                break;
            case 1:
            case 3:
                spr.drawGame("pickup2",x,y,1);
                break;
            case 2:
                spr.drawGame("pickup3",x,y,1);
                break;
        }

        animation = (byte) ((animationOffset + (Game.Time()>>2))%animationLength);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Coin(x,y,xSize,ySize,hp);
    }

    @Override
    public void onPickup() {
        EntityManager.getINSTANCE().spawnEntity("picked coin",x,y);
    }
}
