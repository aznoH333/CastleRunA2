package com.mygdx.game.logic.gamestates;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.BottomHud;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.CoinCounter;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.interfaces.IUIElement;

import java.util.ArrayList;

public class Shop implements IGameState{

    private final static ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final UIManager ui = UIManager.getINSTANCE();

    public Shop(){
        uiElements.add(new BottomHud(-515f,-284));
        uiElements.add(new Button(16, 364, ButtonType.Large,uiElements.get(0),()->ui.transition(GameState.StageMenu)));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64), 38,"icon4", uiElements.get(uiElements.size()-1)));
        uiElements.add(new CoinCounter(16, 516, uiElements.get(0)));
    }

    @Override
    public void update() {

    }

    @Override
    public IUIElement[] getUI() {
        com.mygdx.game.logic.shops.Shop.getINSTANCE().loadUI();
        return uiElements.toArray(new IUIElement[0]);
    }
}
