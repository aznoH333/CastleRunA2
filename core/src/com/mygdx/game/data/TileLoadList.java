package com.mygdx.game.data;

import com.mygdx.game.managers.SpriteManager;

public class TileLoadList {
    public static void loadAllSprites(SpriteManager spr){
        //player stuff
        spr.loadSprites("Entities/Player/Player_","player",5);
        //entities
        //tiles
        spr.loadSprites("Tiles/Temp/Castle_", "castle", 3);
    }
}
