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

        // sparkles
        particleMap.put("sparkle", new Particle(new String[]{"sparkle0","sparkle1"},30,8));


        return particleMap;
    }
}
