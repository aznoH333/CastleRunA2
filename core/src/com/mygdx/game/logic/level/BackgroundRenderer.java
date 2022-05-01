package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;
import com.mygdx.game.logic.drawing.DrawingManager;

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

    private final static int veryDumb = 1280;

    public void draw(DrawingManager spr){
        for (int i = 0; i < background.getBackgrounds().length; i++) {
            spr.drawGame(background.getBackgrounds()[i],(- distance * background.getParallaxes()[i])%veryDumb,0, -1);
            spr.drawGame(background.getBackgrounds()[i],(- distance * background.getParallaxes()[i])%veryDumb+veryDumb,0, -1);
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
