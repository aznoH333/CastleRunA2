package com.mygdx.game.logic.gamestates;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.level.BackgroundRenderer;
import com.mygdx.game.logic.stage.LevelProgressionManager;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.BottomHud;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.elements.regularElements.Text;
import com.mygdx.game.ui.interfaces.IUIElement;

import java.util.ArrayList;

public class StageMenu implements IGameState{

    private final static ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final BackgroundRenderer back = BackgroundRenderer.getINSTANCE();
    private final DrawingManager spr = DrawingManager.getINSTANCE();
    private final LevelProgressionManager lvlMan = LevelProgressionManager.getINSTANCE();
    private final UIManager ui = UIManager.getINSTANCE();
    private final LevelProgressionManager levelMan = LevelProgressionManager.getINSTANCE();

    public StageMenu(){
        uiElements.add(new BottomHud(-515f,-30f));
        uiElements.add(new Button(16,62,ButtonType.Large,uiElements.get(0),()->ui.transition(GameState.MainMenu)));
        uiElements.add(new Sprite((com.mygdx.game.Game.gameWorldWidth/2 - 64), 38,"icon4", uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(16,198,ButtonType.Small,uiElements.get(0),()->ui.transition(GameState.EquipMenu)));
        uiElements.add(new Text(94,80,"Items",uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(com.mygdx.game.Game.gameWorldWidth/2 + 8,198,ButtonType.Small,uiElements.get(0),()->ui.transition(GameState.Shop)));
        uiElements.add(new Text(110,80,"Shop",uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(16,338,ButtonType.Large,uiElements.get(0),()->{ui.transition(GameState.Game);levelMan.startLevel();}));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64), 38,"icon0", uiElements.get(uiElements.size()-1)));
    }

    @Override
    public void update() {
        back.draw(spr);
        back.advance(1);
        lvlMan.drawLevelProgressUI();
    }

    @Override
    public IUIElement[] getUI() {
        return uiElements.toArray(new IUIElement[0]);
    }
}
