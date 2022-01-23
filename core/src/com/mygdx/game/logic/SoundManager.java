package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Config;

import java.util.HashMap;

public class SoundManager {
    private static SoundManager INSTANCE;

    public static SoundManager getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new SoundManager();
        return INSTANCE;
    }

    private HashMap<String, Sound> sounds = new HashMap<>();
    private long musicId;
    private Sound currentMusic;

    public void loadSound(String path, String name){
        sounds.put(name, Gdx.audio.newSound(Gdx.files.internal("sounds/" + path)));
    }


    public void playSound(String soundName, float volume){
        sounds.get(soundName).play(volume);
    }
    public void playSound(String soundName){ sounds.get(soundName).play(Config.getSfxVolume()); }

    public void playMusic(String musicName, float volume){
        if (currentMusic != null)
            currentMusic.stop(musicId);
        currentMusic = sounds.get(musicName);
        musicId = sounds.get(musicName).loop(volume);
    }


    public void dispose(){
        try{
            for (Sound s: sounds.values()) {
                s.dispose();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        sounds.clear();
    }
}
