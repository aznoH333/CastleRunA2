package com.mygdx.game.logic.level;

public class Backgrounds {
    private static final String[][] castleBack = {
            {"castleBack0", "castleBack1", "castleBack0", "castleBack1", "castleBack2", "castleBack2", "castleBack2", "castleBack2", "castleBack2"},
            {"castleBack1", "castleBack0", "castleBack1", "castleBack0", "castleBack3", "castleBack3", "castleBack3", "castleBack3", "castleBack3"},
            {"castleBack0", "castleBack1", "castleBack0", "castleBack1", "castleBack4", "castleBack4", "castleBack4", "castleBack4", "castleBack4"},
            {"castleBack1", "castleBack0", "castleBack1", "castleBack0", "castleBack4", "castleBack4", "castleBack4", "castleBack5", "castleBack4"},
            {"castleBack0", "castleBack1", "castleBack0", "castleBack1", "castleBack4", "castleBack4", "castleBack4", "castleBack4", "castleBack4"},
            {"castleBack1", "castleBack0", "castleBack1", "castleBack0", "castleBack4", "castleBack4", "castleBack4", "castleBack4", "castleBack4"},
            {"castleBack0", "castleBack1", "castleBack0", "castleBack1", "castleBack4", "castleBack4", "castleBack4", "castleBack5", "castleBack4"},
            {"castleBack1", "castleBack0", "castleBack1", "castleBack0", "castleBack4", "castleBack4", "castleBack4", "castleBack4", "castleBack4"},
    };

    private static final String[][] caveBack = {
            {"caveBack7","caveBack9","caveBack2","caveBack4","caveBack0","caveBack0","caveBack0","caveBack0","caveBack3","caveBack5","caveBack8","caveBack7"},
            {"caveBack7","caveBack9","caveBack5","caveBack1","caveBack0","caveBack0","caveBack0","caveBack0","caveBack6","caveBack2","caveBack8","caveBack7"},
    };

    // TODO: multilayered backgrounds

    public static String[][] castle(){
        return castleBack;
    }

    public static String[][] cave(){
        return caveBack;
    }
}
