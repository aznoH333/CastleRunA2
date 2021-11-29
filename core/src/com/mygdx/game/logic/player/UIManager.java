package com.mygdx.game.logic.player;

import com.mygdx.game.logic.SpriteManager;

public class UIManager {

    private static UIManager INSTANCE;
    public static UIManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new UIManager();
        return INSTANCE;
    }


    private final int xOffset = 32;
    private final int yOffset = 640;
    private final int energyOffset = 48;

    public void draw(){
        SpriteManager spr = SpriteManager.getINSTANCE();
        PlayerStats ps = PlayerStats.getINSTANCE();

        // bars
        // health
        spr.draw("barUI2",xOffset-32,yOffset);
        for (int i = 0; i < ps.getMaxHp(); i++) {
            if (i < ps.getHp()) spr.draw("barUI0",xOffset + i*32,yOffset);
            else                spr.draw("barUI1",xOffset + i*32,yOffset);
        }
        spr.draw("barUI4",xOffset+ ps.getMaxHp()*32,yOffset);

        // energy
        spr.draw("barUI3",xOffset-32,yOffset - energyOffset);
        for (int i = 0; i < ps.getMaxEnergy(); i++) {
            if (i < ps.getEnergy()) spr.draw("barUI5",xOffset + i*32,yOffset - energyOffset);
            else                    spr.draw("barUI6",xOffset + i*32,yOffset - energyOffset);
        }
        spr.draw("barUI4",xOffset+ ps.getMaxEnergy()*32,yOffset - energyOffset);

        // TODO: touch controls
    }
}
