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
        s.loadSound("Sounds/Music/PlaceHolder/23 - Bloody Tears.mp3", "placeholder music");

        //player
        s.loadSound("Sounds/player/HopSound.wav", "hop");
        s.loadSound("Sounds/player/JumpSound.wav", "jump");
        s.loadSound("Sounds/player/PlayerDeath.wav", "playerDie");
        s.loadSound("Sounds/player/weapons/Attack1.wav", "weapon1");

        //environment
        s.loadSound("Sounds/pickups/CoinPickup.wav", "coin");
        s.loadSound("Sounds/pickups/CoolPickup.wav", "pickup");
        s.loadSound("Sounds/pickups/EnergyPickup.wav", "energy");
        s.loadSound("Sounds/environment/other/ChestDestroy.wav", "chest");
        s.loadSound("Sounds/environment/traps/Spikes1.wav", "spike1");
        s.loadSound("Sounds/environment/traps/Spikes2.wav", "spike2");

        //enemies
        s.loadSound("Sounds/enemies/EnemyDeath1.wav", "enemyDeath1");
        s.loadSound("Sounds/enemies/slimes/SlimeJump.wav", "slimeJump");


    }



}
