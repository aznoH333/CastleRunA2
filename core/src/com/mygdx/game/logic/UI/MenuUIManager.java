package com.mygdx.game.logic.UI;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.GameState;

public class MenuUIManager {
    private static MenuUIManager INSTANCE;

    public static MenuUIManager getINSTANCE(){
        if(INSTANCE == null)
            INSTANCE = new MenuUIManager();
        return INSTANCE;
    }

    private Button[] buttons;


    public void update(){
        for (Button button: buttons) {
            button.draw();
            button.manageInput();
        }
    }

    public void changeButtonSet(GameState state){
        switch (state){
            case StageMenu:
                buttons = new Button[]{
                        new Button(ButtonType.Large,"icon0",16,152,()->{
                            Game.changeState(GameState.Game);}),
                        new Button(ButtonType.Large,"icon4",16,16,()->{
                            Gdx.app.exit();
                        })
                };
                break;
            default:
                buttons = new Button[]{};
                break;
        }
    }
}
