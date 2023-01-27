package com.mygdx.game.logic;

import com.mygdx.game.entities.player.playerClasses.PlayerKnight;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.player.PlayerStats;

public class ScreenOverlayManager {
    private static ScreenOverlayManager INSTANCE;

    public static ScreenOverlayManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new ScreenOverlayManager();
        return INSTANCE;
    }

    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    private static final PlayerStats ps = PlayerStats.getINSTANCE();
    private float darknessX = 0;
    private float darknessY = 0;

    public void draw(){
        if (ps.getPlayer() != null){
            PlayerKnight playerReference = ps.getPlayer();
            darknessX = playerReference.getX() - 2096;
            darknessY = playerReference.getY() - 2096;
        }
        spr.draw("tma", darknessX, darknessY, 3);

    }


}
