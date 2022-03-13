package com.mygdx.game.data.load;

import com.mygdx.game.logic.drawing.DrawingManager;




public class SpriteLoadList {
    public static void loadAllSprites(DrawingManager spr){
        //TODO: rework to iterate through folders

        //player
        spr.loadSprites("entities/player/player_","player",4);
        spr.loadSprites("entities/player/mapSprites/player_map_","playerMap",5);

        //weapons
        spr.loadSprites("entities/weapons/small_dagger/small_dagger_","dagger",1);
        spr.loadSprites("entities/weapons/basic_sword/sword_","sword",6);
        spr.loadSprites("entities/weapons/throwing_axe/Axe_","axe",3);
        spr.loadSprites("entities/weapons/boomerang/cross_","cross",4);

        //enemies
        spr.loadSprites("entities/enemies/slimes/green_slime/green_slime_","slime",6);
        spr.loadSprites("entities/enemies/slimes/green_slime/green_slime_","slime",6);
        spr.loadSprites("entities/enemies/slimes/red_slime/red_slime_","red_slime",6);
        spr.loadSprites("entities/enemies/skeletons/basic_skeleton/skeleton_","skeleton",2);
        spr.loadSprites("entities/enemies/slimeboss/slime_boss_","slime_boss",2);

        // enemy projectiles
        spr.loadSprites("entities/enemies/projectiles/bone/bone_","bone",5);



        // environmental objects
        spr.loadSprites("tiles/general/chest/Chest_","chest",1);
        spr.loadSprites("tiles/general/door/Door_","door",1);
        spr.loadSprites("entities/pickups/Pick_up_","pickup",3);
        spr.loadSprites("entities/pickups/Potion_","potion",2);



        //tiles
        spr.loadSprites("tiles/castle/foreground/castle_", "castle", 3);
        spr.loadSprites("tiles/castle/background/castle_back_", "castleBack", 1);
        spr.loadSprites("tiles/general/spike_trap/spike_trap_", "spikeTrap", 3);
        spr.loadSprites("tiles/general/platform/platform_", "platform", 5);
        spr.loadSprites("tiles/general/ghost_platform/ghost_platform_", "ghost_platform", 0);
        spr.loadSprites("tiles/cave/foreground/cave_","cave",3);
        spr.loadSprites("tiles/cave/background/cave_back_","caveBack",2);


        //UI
        spr.loadSprites("ui/bars/ui_bars_", "barUI", 6);
        spr.loadSprites("ui/buttons/button_", "button", 1);
        spr.loadSprites("ui/buttons/button_large_", "button_large", 1);
        spr.loadSprites("ui/buttons/card_", "item_card", 1);
        spr.loadSprites("ui/icons/icon_", "icon", 4);
        spr.loadSprites("ui/icons/shop_icon_", "shop_icon", 2);

        spr.loadSprites("ui/ui_elements/gamehud_top/top_bar_", "hudTop", 2);
        spr.loadSprites("ui/ui_elements/gamehud_bottom/hud_bot_", "hudBot", 0);


        //map ui
        spr.loadSprites("ui/map/levels/castle/map_icon_castle_", "mIconCastle", 2);
        spr.loadSprites("ui/map/levels/caves/map_icon_cave_", "mIconCave", 1);
        spr.loadSprites("ui/map/tiles/map_icon_tiles_", "mTile", 2);
        spr.loadSprites("ui/map/backgrounds/map_back_", "mapBack", 2);
        spr.loadSprites("ui/map/decorators/islands/island_decorator_", "islandDecorator", 3);
        spr.loadSprites("ui/icons/boss_icon_", "bossIcon", 4);


        //particles
        spr.loadSprites("particles/effects/Sparkle_","sparkle",1);
        spr.loadSprites("particles/effects/part_small_","particle",14);
        spr.loadSprites("particles/gore/Blood_","gore",13);
        spr.loadSprites("particles/gore/slime_explosion_","slimeExplosion",2);
    }
}
