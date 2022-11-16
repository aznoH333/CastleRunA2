package com.mygdx.game.logic.gamestates;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.BottomHud;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.elements.regularElements.Text;
import com.mygdx.game.ui.interfaces.IUIElement;

import java.util.ArrayList;

public class NewGameMenu implements IGameState{

    private final static com.mygdx.game.logic.menus.NewGameMenu newGameMenu = com.mygdx.game.logic.menus.NewGameMenu.getINSTANCE();
    private final static ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final UIManager ui = UIManager.getINSTANCE();
    public NewGameMenu(){
        uiElements.add(new BottomHud(-515f,-30f));

        uiElements.add(new Button(16,62, ButtonType.Large, uiElements.get(0),()-> ui.transition(GameState.MainMenu)));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64), 38,"icon4", uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(16,198,ButtonType.Large,uiElements.get(0),()->ui.transition(GameState.MasteryScreen)));
        uiElements.add(new Text((Game.gameWorldWidth/2 - (5*25)),80,"Points",uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(16,338, ButtonType.Large, uiElements.get(0), newGameMenu.buttonOperation()));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64), 38,"icon0", uiElements.get(uiElements.size()-1)));
    }

    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    @Override
    public void update() {
        // draw background
        spr.draw("menu_back0", 0, ((float)-Game.Time()/2)%1280, 0, false);
        spr.draw("menu_back0", 0, (((float)-Game.Time()/2))%1280 + 1280, 0, false);

        newGameMenu.draw();
        newGameMenu.update();
    }

    @Override
    public IUIElement[] getUI() {
        return uiElements.toArray(new IUIElement[0]);
    }
}
