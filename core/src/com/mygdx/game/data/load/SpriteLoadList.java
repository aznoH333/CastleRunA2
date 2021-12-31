package com.mygdx.game.data.load;

import com.mygdx.game.logic.sprites.SpriteManager;

public class SpriteLoadList {
    public static void loadAllSprites(SpriteManager spr){
        //TODO: rework to iterate through folders

        //player
        spr.loadSprites("Entities/Player/Player_","player",4);

        //weapons
        spr.loadSprites("Entities/Weapons/SmallDagger/small_dagger_","dagger",1);
        spr.loadSprites("Entities/Weapons/BasicSword/Sword_","sword",6);

        //entities
        spr.loadSprites("Entities/Enemies/Slimes/GreenSlime/green_slime_","slime",6);
        spr.loadSprites("Entities/Pickups/Pick_up_","pickup",3);
        spr.loadSprites("Entities/Pickups/Potion_","potion",2);

        //tiles
        spr.loadSprites("Tiles/Castle/Foreground/Castle_", "castle", 3);
        spr.loadSprites("Tiles/Castle/Background/Castle_back_", "castleBack", 5);
        spr.loadSprites("Tiles/General/Spike_trap/Spike_trap_", "spikeTrap", 3);
        spr.loadSprites("Tiles/cave/foreground/cave_","cave",3);
        spr.loadSprites("Tiles/cave/background/cave_back_","caveBack",9);


        //background objects
        spr.loadSprites("Tiles/General/Chest/Chest_","chest",1);

        //UI
        spr.loadSprites("UI/Bars/UiBars_", "barUI", 6);


        //particles
        spr.loadSprites("Particles/Effects/Sparkle_","sparkle",1);
        spr.loadSprites("Particles/Gore/Blood_","gore",13);
    }
}
