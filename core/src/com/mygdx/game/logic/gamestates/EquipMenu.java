package com.mygdx.game.logic.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.menus.InventoryScreen;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.BottomHud;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.elements.regularElements.UpdatingSprite;
import com.mygdx.game.ui.interfaces.IUIElement;

import java.util.ArrayList;

public class EquipMenu implements IGameState{

    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private final static ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final static PlayerStats stats = PlayerStats.getINSTANCE();
    private final UIManager ui = UIManager.getINSTANCE();
    private final InventoryScreen invScreen = InventoryScreen.getINSTANCE();


    public EquipMenu(){
        uiElements.add(new BottomHud(-515f,-150f));
        uiElements.add(new Button(16, 182, ButtonType.Large ,uiElements.get(0),()->ui.transition(GameState.StageMenu)));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64), 38,"icon4", uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(16, 315, ButtonType.SmallEquipMenu ,uiElements.get(0),()->invScreen.changeSlot(Controls.AttackLeft)));
        uiElements.add(new UpdatingSprite((com.mygdx.game.Game.gameWorldWidth/4 - 80), 48,()-> stats.getLeftWeapon().getWeaponIcon(), uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(Game.gameWorldWidth/2 + 8, 315, ButtonType.SmallEquipMenu ,uiElements.get(0),()->invScreen.changeSlot(Controls.AttackRight)));
        uiElements.add(new UpdatingSprite((com.mygdx.game.Game.gameWorldWidth/4 - 80), 48,()-> stats.getRightWeapon().getWeaponIcon(), uiElements.get(uiElements.size()-1)));
    }

    @Override
    public void update() {
        // draw background
        spr.draw("menu_back0", 0, ((float)-Game.Time()/2)%1280, 0, 1, 1, Color.WHITE, false);
        spr.draw("menu_back0", 0, (((float)-Game.Time()/2)) %1280 + 1280, 1, 1, 1, Color.WHITE, false);

        float selectedButtonX = (invScreen.getEquipSlot() == Controls.AttackLeft) ? 16 : (Game.gameWorldWidth/2);

        if (ui.isOpened()) {
            float animationOffset = (float) ((Game.Time()/32) % 2) * 8;

            spr.draw("selected_button_indicator0", selectedButtonX - animationOffset, 320 + animationOffset, 6);
            spr.draw("selected_button_indicator1", selectedButtonX + (Game.gameWorldWidth/2 - 52) + animationOffset, 320 + animationOffset, 6);
            spr.draw("selected_button_indicator2", selectedButtonX - animationOffset, 162 - animationOffset, 6);
            spr.draw("selected_button_indicator3", selectedButtonX + (Game.gameWorldWidth/2 - 52) + animationOffset, 162 - animationOffset, 6);

        }
    }

    @Override
    public IUIElement[] getUI() {
        InventoryScreen.getINSTANCE().loadUI();
        return uiElements.toArray(new IUIElement[0]);
    }
}
