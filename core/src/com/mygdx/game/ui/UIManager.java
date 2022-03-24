package com.mygdx.game.ui;

import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.ui.interfaces.IUIElement;

public class UIManager {
    private static UIManager INSTANCE;

    public static UIManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new UIManager();
        return INSTANCE;
    }

    private IUIElement[] uiElements = new IUIElement[0];



    public void drawUI(){
        for (IUIElement element: uiElements) {
            element.draw();
        }
    }

    public void changeUI(GameState state){
        switch (state){
            case Game:
                uiElements = new IUIElement[0];
        }
    }
}
