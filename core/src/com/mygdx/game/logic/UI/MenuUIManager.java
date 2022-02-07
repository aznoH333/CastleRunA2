package com.mygdx.game.logic.UI;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.logic.sprites.SpriteManager;

public class MenuUIManager {
    private static MenuUIManager INSTANCE;

    public static MenuUIManager getINSTANCE(){
        if(INSTANCE == null)
            INSTANCE = new MenuUIManager();
        return INSTANCE;
    }

    private Button[] buttons;
    private SpriteManager spr = SpriteManager.getINSTANCE();


    public void update(){
        spr.draw("hudBot0",0,-30,4);
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
                            Game.changeState(GameState.Game);}),
                        new Button(ButtonType.Large,"icon4",16,31,()->{
                            Gdx.app.exit();
                        }),
                        new Button(ButtonType.Small, "icon3",16,167,()->{

                        }),
                        new Button(ButtonType.Small, "icon3",368,167,()->{

                        })
                };
                break;
            default:
                buttons = new Button[]{};
                break;
        }
    }
}
