package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;

public class Backgrounds {

    private static Backgrounds INSTANCE;

    public static Backgrounds getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new Backgrounds();
        return INSTANCE;
    }

    public Background castle(){
        return new Background(new String[]{"castleBack2","castleBack1","castleBack0"}, new float[]{0.25f,0.50f,0.75f});
    }

    public Background cave(){
        return new Background(new String[]{"caveBack5","caveBack4","caveBack3","caveBack2","caveBack1","caveBack0"}, new float[]{0f,0.15f,0.25f,0.40f,0.60f,0.75f});
    }

    public Background forest(){
        return new Background(new String[]{"forestBack4","forestBack3","forestBack2","forestBack1","forestBack0"}, new float[]{0.05f,0.35f,0.40f,0.60f,0.75f});
    }

    // TODO : rewrite theese to be smarter
    // TODO : weather fx (rain + thunder)
}
