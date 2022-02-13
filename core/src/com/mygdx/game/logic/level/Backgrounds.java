package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;

public class Backgrounds {



    public static Background castle(){
        return new Background(new String[]{"castleBack1","castleBack0"}, new float[]{0.5f,0.75f});
    }

    public static Background cave(){
        return new Background(new String[]{"caveBack0","caveBack1","caveBack2"}, new float[]{0.25f,0.50f,0.75f});
    }
}
