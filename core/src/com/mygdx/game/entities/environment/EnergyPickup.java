package com.mygdx.game.entities.environment;

import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.player.PlayerStats;

public class EnergyPickup extends Pickup{
    public EnergyPickup(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.draw("pickup0",x,y,1);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new EnergyPickup(x,y,xSize,ySize,1);
    }

    @Override
    public void onPickup() {
        PlayerStats.getINSTANCE().gainEnergy(1);
        SoundManager.getINSTANCE().playSound("energy");
    }
}
