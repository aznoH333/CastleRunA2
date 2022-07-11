package com.mygdx.game.data.load;

import com.mygdx.game.data.Particle;

import java.util.HashMap;

public class ParticleLoadList {
    public static HashMap<String, Particle> loadParticles(){
        HashMap<String,Particle> particleMap = new HashMap<>();

        // slimes
        particleMap.put("greenSlimeDeath",new Particle(new String[]{"slime3", "slime4", "slime5", "slime6"},0,2));
        particleMap.put("greenGore0",new Particle(new String[]{"gore0"},30,0));
        particleMap.put("greenGore1",new Particle(new String[]{"gore1"},30,0));
        particleMap.put("greenGore2",new Particle(new String[]{"gore2"},30,0));
        particleMap.put("greenGore3",new Particle(new String[]{"gore3"},30,0));
        particleMap.put("redSlimeDeath",new Particle(new String[]{"red_slime3", "red_slime4", "red_slime5", "red_slime6"},0,2));
        particleMap.put("fleshGore0",new Particle(new String[]{"gore4"},30,0));
        particleMap.put("fleshGore1",new Particle(new String[]{"gore5"},30,0));
        particleMap.put("fleshGore2",new Particle(new String[]{"gore6"},30,0));
        particleMap.put("fleshGore3",new Particle(new String[]{"gore7"},30,0));

        // skeletons
        particleMap.put("boneGore0",new Particle(new String[]{"gore8"},30,0));
        particleMap.put("boneGore1",new Particle(new String[]{"gore9"},30,0));
        particleMap.put("boneGore2",new Particle(new String[]{"gore10"},30,0));
        particleMap.put("boneGore3",new Particle(new String[]{"gore11"},30,0));

        //boss death
        particleMap.put("slimeBossDeath", new Particle(new String[]{"slimeExplosion0","slimeExplosion1","slimeExplosion2"},0,3));


        // sparkles
        particleMap.put("sparkle", new Particle(new String[]{"particle5","particle3","sparkle0","sparkle1","sparkle0","particle4","particle5"},0,8));
        particleMap.put("miniSparkle", new Particle(new String[]{"particle5","particle3","particle4","particle3","particle5"},0,8));
        particleMap.put("coinSparkle", new Particle(new String[]{"particle2","particle0","particle1","particle0","particle2"},0,8));

        // environmental effects
        particleMap.put("smoke", new Particle(new String[]{"particle12","particle13","particle14"},0,8));
        particleMap.put("fire", new Particle(new String[]{"particle9","particle10","particle11"},30,8));
        particleMap.put("electricity", new Particle(new String[]{"particle6","particle7","particle8","particle7","particle6","particle8"},0,4));


        // potions
        particleMap.put("potion", new Particle(new String[]{"potion0"},420,0));
        particleMap.put("healthGain", new Particle(new String[]{"gainPart0", "gainPart1", "gainPart2", "gainPart2", "gainPart2", "gainPart2", "gainPart3", "gainPart4"},0 , 2));
        particleMap.put("energyGain", new Particle(new String[]{"gainPart5", "gainPart6", "gainPart7", "gainPart7", "gainPart7", "gainPart7", "gainPart8", "gainPart9"},0 , 2));

        //weapon specific
        particleMap.put("bubble_splash", new Particle(new String[]{"bubble3"},30,0));


        return particleMap;
    }
}
