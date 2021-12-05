package com.mygdx.game.data.load;

import com.mygdx.game.logic.SpriteManager;

public class SpriteLoadList {
    public static void loadAllSprites(SpriteManager spr){
        //player
        spr.loadSprites("Entities/Player/Player_","player",5);

        //weapons
        spr.loadSprites("Entities/Weapons/SmallDagger/small_dagger_","dagger",1);

        //entities
        spr.loadSprites("Entities/Enemies/Slimes/GreenSlime/green_slime_","slime",6);

        //tiles
        spr.loadSprites("Tiles/Castle/Foreground/Castle_", "castle", 3);
        spr.loadSprites("Tiles/Castle/Background/Castle_back_", "castleBack", 5);
        spr.loadSprites("Tiles/General/Spike_trap/Spike_trap_", "spikeTrap", 3);

        //UI
        spr.loadSprites("UI/Bars/UiBars_", "barUI", 6);


        //particles
        spr.loadSprites("Particles/Effects/Sparkle_","sparkle",1);
        spr.loadSprites("Particles/Gore/Blood_","gore",13);
    }
}
