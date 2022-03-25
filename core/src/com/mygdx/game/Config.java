package com.mygdx.game;

public class Config {
    private final static float musicVolume = 0.5f;
    private final static float sfxVolume = 0.7f;
    private final static float animationSpeed = 10;


    public static float getMusicVolume(){
        return musicVolume;
    }

    public static float getSfxVolume(){
        return sfxVolume;
    }

    public static float getAnimationSpeed(){
        return animationSpeed;
    }

}
