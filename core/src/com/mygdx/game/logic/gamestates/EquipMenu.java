package com.mygdx.game.logic.gamestates;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.logic.menus.InventoryScreen;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.BottomHud;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.interfaces.IUIElement;

import java.util.ArrayList;

public class EquipMenu implements IGameState{
    private final static ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final UIManager ui = UIManager.getINSTANCE();
    private final InventoryScreen invScreen = InventoryScreen.getINSTANCE();
    public EquipMenu(){
        uiElements.add(new BottomHud(-515f,-150f));
        uiElements.add(new Button(16, 182, ButtonType.Large,uiElements.get(0),()->ui.transition(GameState.StageMenu)));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64), 38,"icon4", uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(16, 315, ButtonType.Small,uiElements.get(0),()->invScreen.changeSlot(Controls.AttackLeft)));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, 38,"icon3", uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(Game.gameWorldWidth/2 + 8, 315, ButtonType.Small,uiElements.get(0),()->invScreen.changeSlot(Controls.AttackRight)));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, 38,"icon2", uiElements.get(uiElements.size()-1)));
    }

    @Override
    public void update() {

    }

    @Override
    public IUIElement[] getUI() {
        InventoryScreen.getINSTANCE().loadUI();
        return uiElements.toArray(new IUIElement[0]);
    }
}
