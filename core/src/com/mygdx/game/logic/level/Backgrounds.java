package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;

public class Backgrounds {


    // TODO: multilayered backgrounds

    public static Background castle(){
        return new Background(new String[]{"castleBack1","castleBack0"}, new float[]{0.5f,0.75f});
    }

    // TODO : cave background
    public static Background cave(){
        return castle();
    }
}
