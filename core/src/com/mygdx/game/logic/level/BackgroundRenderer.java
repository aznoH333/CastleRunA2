package com.mygdx.game.logic.level;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
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
        long t = Game.Time();
        for (int i = 0; i < background.getLayers().length; i++) {
            for (int j = 0; j < (Gdx.graphics.getWidth() * (1280/Gdx.graphics.getHeight()) + veryDumb*2); j+= veryDumb){
                spr.drawGame(background.getLayers()[i].getSprite(),(- (distance + (background.getLayers()[i].getWindSpeed() * t)) * background.getLayers()[i].getParralax())%veryDumb + j,0, -1);
            }
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
