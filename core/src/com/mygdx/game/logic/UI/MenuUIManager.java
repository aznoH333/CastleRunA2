package com.mygdx.game.logic.UI;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.stage.StageManager;
import com.mygdx.game.logic.stage.StageMap;

public class MenuUIManager {
    private static MenuUIManager INSTANCE;
    private int hudOffset = -30;

    public static MenuUIManager getINSTANCE(){
        if(INSTANCE == null)
            INSTANCE = new MenuUIManager();
        return INSTANCE;
    }

    private Button[] buttons;
    private DrawingManager spr = DrawingManager.getINSTANCE();


    public void update(){
        spr.draw("hudBot0",0,hudOffset,4);
        for (Button button: buttons) {
            button.draw();
            button.manageInput();
        }
    }

    public void changeButtonSet(GameState state){
        switch (state){
            case StageMenu:
                buttons = new Button[]{
                        new Button(ButtonType.Large,"icon0",16,303,()->{
                            StageMap.getINSTANCE().skipTransition();
                            Game.changeState(GameState.Game);
                            StageManager.getINSTANCE().startLevel();}, ""),
                        new Button(ButtonType.Large,"icon4",16,31,()->{
                            StageMap.getINSTANCE().skipTransition();
                            Gdx.app.exit();
                        }, ""),
                        new Button(ButtonType.Small, "icon3",16,167,()->{
                            StageMap.getINSTANCE().skipTransition();
                            Game.changeState(GameState.EquipMenu);
                        }, ""),
                        new Button(ButtonType.Small, "icon3",368,167,()->{
                            StageMap.getINSTANCE().skipTransition();
                            Game.changeState(GameState.Shop);
                        }, "")
                };
                hudOffset = -30;
                break;
            case EquipMenu:
                buttons = new Button[]{
                        new Button(ButtonType.Large,"icon0",16,31,()->{
                            Game.changeState(GameState.StageMenu);}, ""),
                };
                hudOffset = -284;
                break;
            default:
                buttons = new Button[]{};
                break;
        }
    }
}
