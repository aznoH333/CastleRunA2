package com.mygdx.game.entities.environment;

import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.sprites.SpriteManager;

public class HealthPotion extends Pickup{
    private static final int fillHp = 3;

    public HealthPotion(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void draw(SpriteManager spr) {
        SpriteManager.getINSTANCE().drawGame("potion1",x,y);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new HealthPotion(x,y,xSize,ySize,hp);
    }

    @Override
    public void onPickup() {
        //set hp
        PlayerStats ps = PlayerStats.getINSTANCE();
        ps.setHp(Math.min(ps.getHp() + fillHp, ps.getMaxHp()));
        SoundManager.getINSTANCE().playSound("pickup");

        //spawn particle
        ParticleManager.getINSTANCE().addParticle("potion",x,y,(float) Math.random()*6-3, 10 - (float) Math.random()*5,0.6f);
    }
}
