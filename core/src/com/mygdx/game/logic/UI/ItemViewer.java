package com.mygdx.game.logic.UI;

import com.mygdx.game.logic.player.InventoryManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.sprites.SpriteManager;

public class ItemViewer {

    private static ItemViewer INSTANCE;
    public static ItemViewer getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new ItemViewer();
        return INSTANCE;
    }

    private int y = 0;
    private int height = 0;
    private final PlayerStats playerStats = PlayerStats.getINSTANCE();
    private final InventoryManager inventoryManager = InventoryManager.getINSTANCE();
    private final SpriteManager spr = SpriteManager.getINSTANCE();

    public void changeDimensions(int y, int height){
        this.y = y;
        this.height = height;
    }


    public void update(){
        for (int i = 0; i < inventoryManager.getUnlockedWeapons().size(); i++) {
            // TODO : more UI sprites ASAP
            // TODO : scrolling || workaround (inv limit??, scroll buttons??)
            // TODO : fonts
            spr.draw("player0", 32, y+height-(i*80),1);
        }
    }

    public void updateValues(){

    }
}
