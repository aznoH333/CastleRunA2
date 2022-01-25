package com.mygdx.game.entities.environment;

import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.player.PlayerStats;

public class EnergyPickup extends Pickup{
    public EnergyPickup(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void draw(SpriteManager spr) {
        spr.drawGame("pickup0",x,y,1);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new EnergyPickup(x,y,xSize,ySize,1);
    }

    @Override
    public void onPickup() {
        final PlayerStats ps = PlayerStats.getINSTANCE();
        if (ps.getEnergy() < ps.getMaxEnergy())
            ps.setEnergy(ps.getEnergy() + 1);
        SoundManager.getINSTANCE().playSound("energy");
    }
}
