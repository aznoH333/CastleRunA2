package com.mygdx.game.entities.environment;

import com.mygdx.game.Game;

import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.player.PlayerStats;

import java.util.Random;

public class Coin extends Pickup{
    private byte animation = 0;
    private final byte animationOffset;
    private static final int animationLength = 4;
    private static final ParticleManager part = ParticleManager.getINSTANCE();

    public Coin(float x, float y) {
        super(x, y, 32,32,1);
        animationOffset = (byte) (Math.random() * animationLength);
    }

    @Override
    public void update(LevelManager lvl, Random r){
        super.update(lvl, r);
        if (Game.Time() % 8 == 0 && r.nextInt(10) == 1)
            part.addParticle("coinSparkle",x +  r.nextInt((int)xSize) - 16,y + r.nextInt((int)ySize) - 16,0,0,0);
    }

    @Override
    public void draw(DrawingManager spr) {
        switch (animation){
            case 0:
                spr.draw("pickup0",x,y,1);
                break;
            case 1:
            case 3:
                spr.draw("pickup1",x,y,1);
                break;
            case 2:
                spr.draw("pickup2",x,y,1);
                break;
        }

        animation = (byte) ((animationOffset + (Game.Time()>>2))%animationLength);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Coin(x,y);
    }

    @Override
    public void onPickup() {
        Random r = Game.getGeneralRandom();
        PlayerStats.getINSTANCE().gainCoin();
        ParticleManager.getINSTANCE().addParticle("coinFlash", x, y - 4, 0, 0, 0);
        for (int i = 0; i  < 3; i++)
            part.addParticle("coinSparkle",x +  r.nextInt((int)xSize) - 16,y + r.nextInt((int)ySize) - 16,0,0,0);
    }
}
