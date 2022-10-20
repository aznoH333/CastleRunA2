package com.mygdx.game.data.load;

import com.mygdx.game.logic.drawing.DrawingManager;




public class SpriteLoadList {
    public static void loadAllSprites(DrawingManager spr){

        //player
        spr.loadSprites("entities/player/knight/player_knight_","player",4);
        spr.loadSprites("entities/player/hunter/player_hunter_","hunter",4);
        spr.loadSprites("entities/player/midas/player_midas_","midas",4);

        //weapons
        spr.loadSprites("entities/weapons/small_dagger/small_dagger_","dagger",1);
        spr.loadSprites("entities/weapons/basic_sword/sword_","sword",6);
        spr.loadSprites("entities/weapons/throwing_axe/Axe_","axe",3);
        spr.loadSprites("entities/weapons/boomerang/cross_","cross",4);
        spr.loadSprites("entities/weapons/fireball/Fireball_","fireball",4);
        spr.loadSprites("entities/weapons/meteor/meteor_", "meteor", 2);
        spr.loadSprites("entities/weapons/bubble/bubble_", "bubble", 4);
        spr.loadSprites("entities/weapons/bomb/bomb_", "bomb", 4);
        spr.loadSprites("entities/weapons/ice_sword/ice_sword_", "ice_sword", 7);
        spr.loadSprites("entities/weapons/rocks/rocks_", "rock", 6);
        spr.loadSprites("entities/weapons/midas_sword/midas_sword_", "midas_sword", 6);

        //enemies
        spr.loadSprites("entities/enemies/slimes/green_slime/green_slime_","slime",6);
        spr.loadSprites("entities/enemies/slimes/red_slime/red_slime_","red_slime",6);
        spr.loadSprites("entities/enemies/skeletons/basic_skeleton/skeleton_","skeleton",3);
        spr.loadSprites("entities/enemies/skeletons/armored_skeleton/armored_skeleton_","armoredSkeleton",3);
        spr.loadSprites("entities/enemies/slimeboss/slime_boss_","slime_boss",2);
        spr.loadSprites("entities/enemies/bullshit/skull/skull_", "ghost_skull", 1);
        spr.loadSprites("entities/enemies/goblin/goblin_", "goblin", 3);
        spr.loadSprites("entities/enemies/mechboss/goblin_mech_", "mech", 7);
        spr.loadSprites("entities/enemies/skeletons/rocket_skeleton/rocket_skeleton_", "rocket_skeleton", 3);
        spr.loadSprites("entities/enemies/slimes/purple_slime/purple_slime_", "purple_slime", 6);


        // enemy projectiles
        spr.loadSprites("entities/enemies/skeletons/bones/bone_","bone",4);
        spr.loadSprites("entities/enemies/general_projectiles/saw_", "saw", 2);
        spr.loadSprites("entities/enemies/general_projectiles/rocket_", "rocket", 2);
        spr.loadSprites("entities/enemies/general_projectiles/target_cross_", "tcross", 1);



        // environmental objects
        spr.loadSprites("tiles/general/chest/Chest_","chest",1);
        spr.loadSprites("tiles/general/door/Door_","door",1);
        spr.loadSprites("entities/pickups/Pick_up_","pickup",3);
        spr.loadSprites("entities/pickups/Potion_","potion",2);
        spr.loadSprites("tiles/general/furniture/furniture_", "furniture", 6);
        spr.loadSprites("tiles/general/furniture/parts/furniture_part_", "furniturePart", 4);



        //tiles
        // castle
        spr.loadSprites("tiles/castle/foreground/castle_", "castle", 3);
        spr.loadSprites("tiles/castle/background/castle_back_", "castleBack", 2);

        // general
        spr.loadSprites("tiles/general/spike_trap/spike_trap_", "spikeTrap", 3);
        spr.loadSprites("tiles/general/platform/platform_", "platform", 5);
        spr.loadSprites("tiles/general/ghost_platform/ghost_platform_", "ghost_platform", 0);

        // cave
        spr.loadSprites("tiles/cave/foreground/cave_","cave",3);
        spr.loadSprites("tiles/cave/background/cave_background_","caveBack",5);

        // forest
        spr.loadSprites("tiles/forest/foreground/forest_","forest",5);
        spr.loadSprites("tiles/forest/background/forestback_","forestBack",10);

        // yard
        spr.loadSprites("tiles/yard/background/castle_yard_","yardBack",7);



        // items
        spr.loadSprites("items/itemprewievs/itempreviews_", "itemPreview",8);
        spr.loadSprites("items/minibombs/minibomb_", "minibomb",1);
        spr.loadSprites("items/friendlyOrb/miniorb_","miniorb",0);

        // fx
        spr.loadSprites("items/minibombs/miniexplosion_", "miniexplosion",5);
        spr.loadSprites("entities/weapons/medium_explosion/medium_explosion_", "explosion" ,6);


        //UI
        spr.loadSprites("ui/bars/meters_", "meter", 8);
        spr.loadSprites("ui/bars/bars_segments_", "bar", 1);
        spr.loadSprites("ui/bars/bars_start_", "barStart", 1);
        spr.loadSprites("ui/ui_elements/message_box/message_box_","msgBox", 2);
        spr.loadSprites("ui/ui_elements/levelBar/levelBar_","lvlBar", 6);


        //spr.loadSprites("ui/buttons/button_", "button", 1);
        spr.loadSprites("ui/buttons/button_part_", "button", 5);
        spr.loadSprites("ui/icons/icon_", "icon", 4);
        spr.loadSprites("ui/icons/shop_icon_", "shop_icon", 2);
        spr.loadSprites("ui/icons/locked_character_","locked_char", 0);


        spr.loadSprites("ui/ui_elements/gamehud_top/top_bar_", "hudTop", 2);
        spr.loadSprites("ui/ui_elements/gamehud_bottom/hud_bot_", "hudBot", 0);
        spr.loadSprites("ui/ui_elements/transition_", "transition", 0);

        //map ui
        spr.loadSprites("ui/map/mapPointer/map_tiles_", "map_tile", 3);


        //particles
        spr.loadSprites("particles/effects/Sparkle_","sparkle",1);
        spr.loadSprites("particles/effects/part_small_","particle",14);
        spr.loadSprites("particles/gore/Blood_","gore",13);
        spr.loadSprites("particles/gore/slime_explosion_","slimeExplosion",2);
        spr.loadSprites("particles/gainParticles/gain_part_", "gainPart", 9);
        spr.loadSprites("particles/effects/ice/ice_flame_","icePart",6);
        spr.loadSprites("particles/coinSplash/coin_splash_","coinPart",2);
        spr.loadSprites("particles/misc/crit_icon_","crit",0);
        spr.loadSprites("particles/misc/xp_icon_","xp",1);

    }
}
