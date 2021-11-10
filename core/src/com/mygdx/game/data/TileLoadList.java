package com.mygdx.game.data;

import com.mygdx.game.managers.SpriteManager;

public class TileLoadList {
    public static void loadAllSprites(SpriteManager spr){
        //player
        spr.loadSprites("Entities/Player/Player_","player",5);

        //weapons
        spr.loadSprites("Entities/Weapons/SmallDagger/small_dagger_","dagger",1);

        //entities
        spr.loadSprites("Entities/Enemies/Slimes/GreenSlime/geen_slime_","slime",2);

        //tiles
        spr.loadSprites("Tiles/Castle/Foreground/Castle_", "castle", 3);
        spr.loadSprites("Tiles/Castle/Background/Castle_back_", "castleBack", 5);

    }
}
