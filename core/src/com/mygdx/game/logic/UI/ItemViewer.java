package com.mygdx.game.logic.UI;

import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.logic.player.InventoryManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.player.Weapon;
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
    private Button[] buttons = {};
    private Controls itemSlot = Controls.AttackLeft;

    public void changeDimensions(int y, int height){
        this.y = y;
        this.height = height;
        updateValues();
    }


    public void update(){
        for (Button button: buttons) {
            button.manageInput();
            button.draw();
        }
    }

    public void updateValues(){
        buttons = new Button[inventoryManager.getUnlockedWeapons().size()];
        for (int i = 0; i < inventoryManager.getUnlockedWeapons().size(); i++) {
            // TODO : more UI sprites ASAP
            // TODO : scrolling || workaround (inv limit??, scroll buttons??)
            // TODO : fonts
            final Weapon w = inventoryManager.getUnlockedWeapon(i);
            buttons[i] = new Button(ButtonType.LargeItemSelect,w.getSprite(),16,y + height - (136*i),()-> playerStats.equipWeapon(w,itemSlot));
        }
    }

    public void changeSlot(Controls slot){
        itemSlot = slot;
    }
}
