package com.mygdx.game.logic.gamestates;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.UIBox;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.interfaces.IUIElement;

import java.util.ArrayList;

public class GameOver implements IGameState{

    private final static com.mygdx.game.logic.menus.GameOver gameOver = com.mygdx.game.logic.menus.GameOver.getINSTANCE();
    private final static ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final UIManager ui = UIManager.getINSTANCE();
    public GameOver(){
        uiElements.add(new UIBox(521));
        uiElements.add(new Button(16,16,ButtonType.Small,uiElements.get(0),()-> Gdx.app.exit()));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, 38,"icon4", uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(16,168,ButtonType.Small,uiElements.get(0),()-> ui.transition(GameState.NewGameMenu)));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, 38,"icon0", uiElements.get(uiElements.size()-1)));
    }

    @Override
    public void update() {
        gameOver.render();
    }

    @Override
    public IUIElement[] getUI() {
        return uiElements.toArray(new IUIElement[0]);
    }
}
