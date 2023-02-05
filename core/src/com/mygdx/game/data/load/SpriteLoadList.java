package com.mygdx.game.data.load;

import com.mygdx.game.logic.drawing.DrawingManager;




public class SpriteLoadList {
    public static void loadAllSprites(DrawingManager spr){

        //player


        // player light entity
        spr.loadTextureAndSprite("light", "entities/player/light_entities/light.png");
        spr.loadTextureAndSprite("darkness", "entities/player/light_entities/darkness.png");
        spr.loadTextureAndSprite("tma", "entities/player/light_entities/tma.png");

        //weapons
        spr.loadSprites("entities/weapons/meteor/meteor_", "meteor", 2);
        spr.loadSprites("entities/weapons/bubble/bubble_", "bubble", 4);
        spr.loadSprites("entities/weapons/rocks/rocks_", "rock", 6);
        spr.loadSprites("entities/weapons/bone_sword/bone_sword_", "bone_sword", 0);
        spr.loadSprites("entities/weapons/bone_sword/shockwave_", "shockwave", 5);

        //enemies
        spr.loadSprites("entities/enemies/slimeboss/slime_boss_","slime_boss",2);
        spr.loadSprites("entities/enemies/mechboss/goblin_mech_", "mech", 7);
        spr.loadSprites("entities/enemies/skeletons/rocket_skeleton/rocket_skeleton_", "rocket_skeleton", 3);




        // enemy projectiles
        spr.loadSprites("entities/enemies/skeletons/bones/bone_","bone",4);



        // environmental objects







        // fx
        spr.loadSprites("entities/weapons/medium_explosion/medium_explosion_", "explosion" ,6);


        //UI
        spr.loadSprites("ui/bars/meters_", "meter", 5);
        spr.loadSprites("ui/bars/bars_segments_", "bar", 1);
        spr.loadSprites("ui/bars/coin_counter_", "coinCounter", 0);
        spr.loadSprites("ui/bars/bars_start_", "barStart", 1);
        spr.loadSprites("ui/ui_elements/message_box/message_box_","msgBox", 1);
        spr.loadSprites("ui/ui_elements/levelBar/levelBar_","lvlBar", 6);
        spr.loadSprites("ui/death/damage_overlay_", "damage_overlay", 0);
        spr.loadSprites("ui/death/death_skull_", "death_skull", 0);

        // shop item backdrop
        spr.loadTextureAndSprite("shop_ui_backdrop", "ui/ui_elements/ShopBackGround.png");

        // button select indicator
        spr.loadSprites("ui/buttons/selected_button_indicator", "selected_button_indicator",3);





        //spr.loadSprites("ui/buttons/button_", "button", 1);
        spr.loadSprites("ui/buttons/button_part_", "button", 5);
        spr.loadSprites("ui/large_buttons/button_", "large_button", 5);
        spr.loadSprites("ui/icons/weapon_icons_", "wIcon", 5);
        spr.loadSprites("ui/icons/large_icon_", "large_icon", 1);

        spr.loadSprites("ui/menu/menu_backdrop_", "menu_back", 0);
        spr.loadSprites("ui/menu/tittle_", "tittle", 0);


        spr.loadSprites("ui/ui_elements/gamehud_top/top_bar_", "hudTop", 0);
        spr.loadSprites("ui/ui_elements/gamehud_bottom/hud_bot_", "hudBot", 0);
        spr.loadSprites("ui/ui_elements/transition_", "transition", 0);



        //particles
        spr.loadSprites("particles/gore/slime_explosion_","slimeExplosion",2);
        spr.loadSprites("particles/effects/ice/ice_flame_","icePart",6);
        spr.loadSprites("particles/misc/crit_icon_","crit",0);
        spr.loadSprites("particles/misc/xp_icon_","xp",1);


        // temporary bogus
        // TODO : clean up

        // load tilesets
        spr.loadTexture("16tiles", "SpriteSheets/16xTiles.png", 16, 16);
        spr.loadTexture("320tiles", "SpriteSheets/320xTiles.png", 320, 320);
        spr.loadTexture("9tiles", "SpriteSheets/9xTiles.png", 9, 9);


        // load tiles
        spr.loadSpriteCollectionFromHelper("castle", "16tiles", 4);
        spr.loadSpriteCollectionFromHelper("cave", "16tiles", 4);
        spr.loadSpriteCollectionFromHelper("forest", "16tiles", 6);

        spr.loadSpriteCollectionFromHelper("spikeTrap", "16tiles", 4);
        spr.loadSpriteCollectionFromHelper("spikes", "16tiles", 4);
        spr.loadSpriteCollectionFromHelper("ghost_platform", "16tiles", 1);
        spr.loadSpriteCollectionFromHelper("platform", "16tiles", 6);

        // load backgrounds
        spr.loadSpriteCollectionFromHelper("castleBack", "320tiles", 3);
        spr.loadSpriteCollectionFromHelper("caveBack", "320tiles", 6);
        spr.loadSpriteCollectionFromHelper("forestBack", "320tiles", 11);
        spr.loadSpriteCollectionFromHelper("yardBack", "320tiles", 8);

        // load entities
        spr.loadSpriteCollectionFromHelper("chest", "16tiles", 2);
        spr.loadSpriteCollectionFromHelper("door", "16tiles", 2);
        spr.loadSpriteCollectionFromHelper("furniture", "16tiles", 7);
        spr.loadSpriteCollectionFromHelper("slime", "16tiles",7);
        spr.loadSpriteCollectionFromHelper("red_slime", "16tiles", 7);
        spr.loadSpriteCollectionFromHelper("purple_slime", "16tiles", 7);
        spr.loadSpriteCollectionFromHelper("frog_green", "16tiles", 3);
        spr.loadSpriteCollectionFromHelper("frog_red", "16tiles", 3);
        spr.loadSpriteCollectionFromHelper("frog_blue", "16tiles", 3);
        spr.loadSpriteCollectionFromHelper("skeleton", "16tiles", 4);
        spr.loadSpriteCollectionFromHelper("armoredSkeleton", "16tiles", 4);
        spr.loadSpriteCollectionFromHelper("saw", "16tiles", 3);
        spr.loadSpriteCollectionFromHelper("tCross", "16tiles", 2);
        spr.loadSpriteCollectionFromHelper("goblin", "16tiles", 4);
        spr.loadSpriteCollectionFromHelper("potion", "16tiles", 3);
        spr.loadSpriteCollectionFromHelper("player", "16tiles", 5);
        spr.loadSpriteCollectionFromHelper("hunter", "16tiles", 5);
        spr.loadSpriteCollectionFromHelper("midas", "16tiles", 5);
        spr.loadSpriteCollectionFromHelper("haunted", "16tiles", 5);

        // load weapons
        spr.loadSpriteCollectionFromHelper("sword", "16tiles", 7);
        spr.loadSpriteCollectionFromHelper("bomb", "16tiles", 5);
        spr.loadSpriteCollectionFromHelper("cross", "16tiles", 5);
        spr.loadSpriteFromHelper("bubble4", "16tiles");
        spr.loadSpriteCollectionFromHelper("fireball", "16tiles", 5);
        spr.loadSpriteCollectionFromHelper("midas_sword", "16tiles", 7);
        spr.loadSpriteCollectionFromHelper("rocks", "16tiles", 1);
        spr.loadSpriteCollectionFromHelper("dagger", "16tiles", 2);
        spr.loadSpriteCollectionFromHelper("axe", "16tiles", 4);
        spr.loadSpriteCollectionFromHelper("itemPreview", "16tiles", 9);
        spr.loadSpriteCollectionFromHelper("miniexplosion", "16tiles", 6);

        // misc ui stuff
        spr.loadSpriteCollectionFromHelper("map_tile", "16tiles", 4);
        spr.loadSpriteFromHelper("equip_ui_mark", "16tiles");
        spr.loadSpriteCollectionFromHelper("icon", "16tiles", 5);
        spr.loadSpriteCollectionFromHelper("locked_char", "16tiles", 1);
        spr.loadSpriteCollectionFromHelper("shop_icon", "16tiles", 3);
        spr.loadSpriteCollectionFromHelper("bone", "16tiles", 5);
        spr.loadSpriteCollectionFromHelper("crit", "16tiles", 1);
        spr.loadSpriteCollectionFromHelper("xp", "16tiles", 2);


        // particles
        spr.loadSpriteCollectionFromHelper("pickup", "9tiles", 3);
        spr.loadSpriteCollectionFromHelper("rocket", "9tiles", 3);
        spr.loadSpriteCollectionFromHelper("miniorb", "9tiles", 1);
        spr.loadSpriteCollectionFromHelper("minibomb", "9tiles", 2);
        spr.loadSpriteCollectionFromHelper("coinPart", "9tiles", 3);
        spr.loadSpriteCollectionFromHelper("particle", "9tiles", 15);
        spr.loadSpriteCollectionFromHelper("sparkle", "9tiles", 2);
        spr.loadSpriteCollectionFromHelper("gainPart", "9tiles", 10);
        spr.loadSpriteCollectionFromHelper("gore", "9tiles", 14);
        spr.loadSpriteCollectionFromHelper("furniturePart", "9tiles", 5);


    }
}
