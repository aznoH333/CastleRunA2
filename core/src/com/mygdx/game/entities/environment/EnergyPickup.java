package com.mygdx.game.entities.environment;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SpriteManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.player.PlayerStats;

public class EnergyPickup extends Pickup{
    public EnergyPickup(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void draw(SpriteManager spr) {
        spr.draw("pickup0",x,y);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new EnergyPickup(x,y,xSize,ySize,1);
    }

    @Override
    public void onDestroy() {
        final PlayerStats ps = PlayerStats.getINSTANCE();
        if (ps.getEnergy() < ps.getMaxEnergy())
            ps.setEnergy(ps.getEnergy() + 1);
    }
}
