package com.mygdx.game.logic.menus;

public class GameOver {
    private static GameOver INSTANCE;

    public static GameOver getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new GameOver();
        return INSTANCE;
    }

    public void render(){
        // TODO : this
    }
}
