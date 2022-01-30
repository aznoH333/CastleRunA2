package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;

public class Backgrounds {


    // TODO: multilayered backgrounds

    public static Background castle(){
        return new Background(new String[]{"castleBack0","castleBack1"}, new float[]{0.5f,0.25f});
    }

    // TODO : cave background
    public static Background cave(){
        return castle();
    }
}
