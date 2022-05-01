package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;

public class Backgrounds {
    // TODO : rewrite this to be an object instead of public functions
    public static Background castle(){
        return new Background(new String[]{"castleBack1","castleBack0"}, new float[]{0.5f,0.75f});
    }

    public static Background cave(){
        return new Background(new String[]{"caveBack5","caveBack4","caveBack3","caveBack2","caveBack1","caveBack0"}, new float[]{0f,0.15f,0.25f,0.40f,0.60f,0.75f});
    }

    public static Background forest(){
        return new Background(new String[]{"player0"}, new float[]{0.75f});
    }
}
