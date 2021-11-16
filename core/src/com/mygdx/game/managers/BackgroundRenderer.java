package com.mygdx.game.managers;

public class BackgroundRenderer {
    // singleton stuff
    private static BackgroundRenderer INSTANCE;
    public static BackgroundRenderer getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new BackgroundRenderer();
        return INSTANCE;
    }


    //Temp
    private String[][] background = {{"player0"}};
    private float distance = 0;
    private final int tileScale = 64;
    public BackgroundRenderer(){

    }


    public void draw(SpriteManager spr){
        for (int x = 0; x < 21 + background.length; x++) {
            for (int y = 0; y < 12; y++) {
                spr.draw(background[x%background.length][y%background[0].length]
                        , x * tileScale - distance%(tileScale * background.length)
                        , y * tileScale);
            }
        }


    }

    public void setBackground(String[][] nBack){
        background = nBack;
        distance = 0;
    }

    public void advance(float advanceDist){
        distance += advanceDist;
    }
}
