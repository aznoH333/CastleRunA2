package com.mygdx.game.data.load;

import com.mygdx.game.data.Particle;

import java.util.HashMap;

public class ParticleLoadList {
    public static HashMap<String, Particle> loadParticles(){
        HashMap<String,Particle> particleMap = new HashMap<>();

        // slimes
        particleMap.put("greenSlimeDeath",new Particle(new String[]{"slime3", "slime4", "slime5", "slime6"},0,2));
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
        particleMap.put("coinFlash", new Particle(new String[]{"coinPart0","coinPart1","coinPart2"},0,4));


        // environmental effects
        particleMap.put("smoke", new Particle(new String[]{"particle12","particle13","particle14"},0,8));
        particleMap.put("fire", new Particle(new String[]{"particle9","particle10","particle11"},30,8));
        particleMap.put("electricity", new Particle(new String[]{"particle6","particle7","particle8","particle7","particle6","particle8"},0,4));
        particleMap.put("smallRock0", new Particle(new String[]{"rock5"},30,0));
        particleMap.put("smallRock1", new Particle(new String[]{"rock6"},30,0));
        particleMap.put("furniture0", new Particle(new String[]{"furniturePart0"},30,0));
        particleMap.put("furniture1", new Particle(new String[]{"furniturePart1"},30,0));
        particleMap.put("furniture2", new Particle(new String[]{"furniturePart2"},30,0));
        particleMap.put("furniture3", new Particle(new String[]{"furniturePart3"},30,0));
        particleMap.put("furniture4", new Particle(new String[]{"furniturePart4"},30,0));



        // potions
        particleMap.put("potion", new Particle(new String[]{"potion0"},420,0));
        particleMap.put("healthGain", new Particle(new String[]{"gainPart0", "gainPart1", "gainPart2", "gainPart2", "gainPart2", "gainPart2", "gainPart3", "gainPart4"},0 , 2));
        particleMap.put("energyGain", new Particle(new String[]{"gainPart5", "gainPart6", "gainPart7", "gainPart7", "gainPart7", "gainPart7", "gainPart8", "gainPart9"},0 , 2));

        //weapon specific
        particleMap.put("bubble_splash", new Particle(new String[]{"bubble3"},30,0));

        // misc
        particleMap.put("crit", new Particle(new String[]{"crit0"}, 45,0));
        particleMap.put("xp", new Particle(new String[]{"xp0"}, 45,0));
        particleMap.put("xpBig", new Particle(new String[]{"xp1"}, 45,0));

        return particleMap;
    }
}
