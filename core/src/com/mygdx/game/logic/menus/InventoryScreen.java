package com.mygdx.game.logic.menus;

import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.logic.player.InventoryManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.player.Weapon;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.InvisUIParent;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.elements.regularElements.Text;

public class InventoryScreen {
    private static InventoryScreen INSTANCE;

    public static InventoryScreen getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new InventoryScreen();
        return INSTANCE;
    }

    private final static InventoryManager inv = InventoryManager.getINSTANCE();
    private final static PlayerStats stats = PlayerStats.getINSTANCE();
    private final static UIManager ui = UIManager.getINSTANCE();
    private Controls equipSlot = Controls.AttackLeft;


    public void loadUI(){
        // add weapon buttons
        InvisUIParent prnt = new InvisUIParent();
        ui.addUIElement(prnt);

        for (int i = 0; i < inv.getUnlockedWeapons().size();i++){
            Weapon w = inv.getUnlockedWeapon(i);

            if (!w.getName().equals("Nothing")) {
                Button btn = new Button(16, 1144 - (i - 1)  * 136, ButtonType.Large, prnt, () -> stats.equipWeapon(w, equipSlot));
                Sprite spr = new Sprite(16, 32, "player0", btn); // TODO : make this look presentable
                ui.addUIElement(btn);
                ui.addUIElement(spr);
                ui.addUIElement(new Sprite(16, 16, w.getSprite(), spr));
                ui.addUIElement(new Text(16, 128, w.getName(), btn));
            }
            // TODO : this
        }
    }

    public void changeSlot(Controls slot){
        equipSlot = slot;
        // TODO : change button color?
    }
}
