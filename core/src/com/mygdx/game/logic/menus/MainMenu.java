package com.mygdx.game.logic.menus;

public class MainMenu {

    private static MainMenu INSTANCE;
    public static MainMenu getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new MainMenu();
        return INSTANCE;
    }

    public void draw(){
        // TODO : this
    }
}
