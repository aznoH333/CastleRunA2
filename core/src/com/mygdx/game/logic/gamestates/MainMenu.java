package com.mygdx.game.logic.gamestates;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.BottomHud;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.elements.regularElements.Text;
import com.mygdx.game.ui.interfaces.IUIElement;

import java.util.ArrayList;

public class MainMenu implements IGameState{

    private final static com.mygdx.game.logic.menus.MainMenu mainMenu = com.mygdx.game.logic.menus.MainMenu.getINSTANCE();
    private final static ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final UIManager ui = UIManager.getINSTANCE();
    public MainMenu(){
        uiElements.add(new BottomHud(-515f,0f));
        uiElements.add(new Button(16,32,ButtonType.Large,uiElements.get(0),()->Gdx.app.exit()));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64), 38,"icon4", uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(16,168,ButtonType.Large,uiElements.get(0),()->{}));
        uiElements.add(new Text(94,80,"Options",uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(16,304,ButtonType.Large,uiElements.get(0),()->ui.transition(GameState.NewGameMenu)));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64), 38,"icon0", uiElements.get(uiElements.size()-1)));
    }

    @Override
    public void update() {
        mainMenu.draw();
    }

    @Override
    public IUIElement[] getUI() {
        return uiElements.toArray(new IUIElement[0]);
    }
}
