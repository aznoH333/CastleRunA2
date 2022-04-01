package com.mygdx.game.logic.menus;

public class NewGameMenu {
    private static NewGameMenu INSTANCE;
    public static NewGameMenu getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new NewGameMenu();
        return INSTANCE;
    }

    public void draw(){
        // TODO: stage selection
        // TODO: starting equipment selection
    }
}
