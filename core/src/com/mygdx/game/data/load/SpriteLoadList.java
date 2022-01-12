package com.mygdx.game.data.load;

import com.mygdx.game.logic.sprites.SpriteManager;

public class SpriteLoadList {
    public static void loadAllSprites(SpriteManager spr){
        //TODO: rework to iterate through folders

        //player
        spr.loadSprites("entities/player/Player_","player",4);

        //weapons
        spr.loadSprites("entities/weapons/small_dagger/small_dagger_","dagger",1);
        spr.loadSprites("entities/weapons/basic_sword/sword_","sword",6);
        spr.loadSprites("entities/weapons/throwing_axe/Axe_","axe",3);

        //enemies
        spr.loadSprites("entities/enemies/slimes/green_slime/green_slime_","slime",6);
        spr.loadSprites("entities/enemies/slimes/red_slime/red_slime_","red_slime",6);
        spr.loadSprites("entities/enemies/skeletons/basic_skeleton/skeleton_","skeleton",2);
        spr.loadSprites("entities/enemies/bullshit/skull/skull_","skull",1);



        // environmental objects
        spr.loadSprites("tiles/general/chest/Chest_","chest",1);
        spr.loadSprites("tiles/general/door/Door_","door",1);
        spr.loadSprites("entities/pickups/Pick_up_","pickup",3);
        spr.loadSprites("entities/pickups/Potion_","potion",2);



        //tiles
        spr.loadSprites("tiles/castle/foreground/Castle_", "castle", 3);
        spr.loadSprites("tiles/castle/background/Castle_back_", "castleBack", 5);
        spr.loadSprites("tiles/general/spike_trap/Spike_trap_", "spikeTrap", 3);
        spr.loadSprites("tiles/cave/foreground/cave_","cave",3);
        spr.loadSprites("Tiles/cave/background/cave_back_","caveBack",9);


        //UI
        spr.loadSprites("ui/bars/UiBars_", "barUI", 6);
        spr.loadSprites("ui/buttons/button_", "button", 13);
        spr.loadSprites("ui/icons/icon_", "icon", 11);


        //particles
        spr.loadSprites("particles/effects/Sparkle_","sparkle",1);
        spr.loadSprites("particles/gore/Blood_","gore",13);
    }
}
