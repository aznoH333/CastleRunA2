package com.mygdx.game.data.load;

import com.mygdx.game.logic.SoundManager;

public class SoundLoadList {

    // TODO: rewrite these to iterate through folders
    public static void loadAllSounds(){

        /**
         * !!Warning!!
         *
         * libgdx is very dumb!
         *
         * sfxr have to be saved as 16bit 22050hz to load
         *
         * !!ALL OF THIS IS TEMPORARY!!
         */

        SoundManager s = SoundManager.getINSTANCE();
        // FIXME : music not loading (probably try using Music class instead of Sound class)
        //s.loadSound("music/PlaceHolder/23 - Bloody Tears.mp3", "placeholder music");

        //player
        s.loadSound("player/HopSound.wav", "hop");
        s.loadSound("player/JumpSound.wav", "jump");
        s.loadSound("player/PlayerDeath.wav", "playerDie");
        s.loadSound("player/weapons/Attack1.wav", "weapon1");

        //environment
        s.loadSound("pickups/CoinPickup.wav", "coin");
        s.loadSound("pickups/CoolPickup.wav", "pickup");
        s.loadSound("pickups/EnergyPickup.wav", "energy");
        s.loadSound("environment/other/ChestDestroy.wav", "chest");
        s.loadSound("environment/traps/Spikes1.wav", "spike1");
        s.loadSound("environment/traps/Spikes2.wav", "spike2");

        //enemies
        s.loadSound("enemies/EnemyDeath1.wav", "enemyDeath1");
        s.loadSound("enemies/slimes/SlimeJump.wav", "slimeJump");


    }



}
