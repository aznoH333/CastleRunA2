package com.mygdx.game.logic.UI;

import com.mygdx.game.logic.player.InventoryManager;
import com.mygdx.game.logic.player.PlayerStats;

public class ItemViewer {
    private int x = 0;
    private int y = 0;
    private int height = 0;
    private final PlayerStats playerStats = PlayerStats.getINSTANCE();
    private final InventoryManager inventoryManager = InventoryManager.getINSTANCE();

    public void changeDimensions(int x, int y, int height){
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public void updated(){

    }
}
