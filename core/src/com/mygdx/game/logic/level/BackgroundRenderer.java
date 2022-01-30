package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;
import com.mygdx.game.logic.sprites.SpriteManager;

public class BackgroundRenderer {
    // singleton stuff
    private static BackgroundRenderer INSTANCE;
    public static BackgroundRenderer getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new BackgroundRenderer();
        return INSTANCE;
    }


    //Temp
    private float distance = 0;
    Background background;
    public BackgroundRenderer(){

    }

    // TODO: rewrite backgrounds to not use tiles


    public void draw(SpriteManager spr){
        for (int i = 0; i < background.getBackgrounds().length; i++) {
            spr.drawGame(background.getBackgrounds()[i],0,0, -1);
        }
    }

    public void setBackground(Background nBack){
        background = nBack;
        distance = 0;
    }

    public void advance(float advanceDist){
        distance += advanceDist;
    }
}
