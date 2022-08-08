package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;
import com.mygdx.game.data.BackgroundLayer;

public class Backgrounds {

    private static Backgrounds INSTANCE;

    public static Backgrounds getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new Backgrounds();
        return INSTANCE;
    }

    // backgrounds
    private final static Background castleBack = new Background(new BackgroundLayer[]{
            new BackgroundLayer("castleBack2", 0.25f),
            new BackgroundLayer("castleBack1", 0.50f),
            new BackgroundLayer("castleBack0", 0.75f),
    });

    private final static Background caveBack = new Background(new BackgroundLayer[]{
                new BackgroundLayer("caveBack5", 0),
                new BackgroundLayer("caveBack4", 0.15f),
                new BackgroundLayer("caveBack3", 0.25f),
                new BackgroundLayer("caveBack2", 0.40f),
                new BackgroundLayer("caveBack1", 0.60f),
                new BackgroundLayer("caveBack0", 0.75f),
    });

    private final static Background forestBack = new Background(new BackgroundLayer[]{
                new BackgroundLayer("forestBack10", 0.15f),
                new BackgroundLayer("forestBack9", 0.17f),
                new BackgroundLayer("forestBack8", 0.18f),
                new BackgroundLayer("forestBack7", 0.25f),
                new BackgroundLayer("forestBack6", 0.27f),
                new BackgroundLayer("forestBack5", 0.30f),
                new BackgroundLayer("forestBack4", 0.45f),
                new BackgroundLayer("forestBack3", 0.50f),
                new BackgroundLayer("forestBack2", 0.45f),
                new BackgroundLayer("forestBack1", 0.65f),
                new BackgroundLayer("forestBack0", 0.75f),
    });

    private final static Background yard = new Background(new BackgroundLayer[]{
                new BackgroundLayer("yardBack7", 0.0f),
                new BackgroundLayer("yardBack6", 0.08f),
                new BackgroundLayer("yardBack5", 0.12f),
                new BackgroundLayer("yardBack4", 0.45f),
                new BackgroundLayer("yardBack3", 0.45f),
                new BackgroundLayer("yardBack2", 0.10f, 80f),
                new BackgroundLayer("yardBack1", 0.15f, 50f),
                new BackgroundLayer("yardBack0", 0.20f, 30f),
    });







    public Background castle(){
        return castleBack;
    }

    public Background cave(){
        return caveBack;
    }

    public Background forest(){
        return forestBack;
    }

    public Background yard(){
        return yard;
    }

    // TODO : weather fx (rain + thunder)
}
