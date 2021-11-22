package com.mygdx.game.managers;

import com.mygdx.game.data.Entity;
import com.mygdx.game.data.Particle;

import java.util.ArrayList;
import java.util.HashMap;

public class ParticleManager {
    private static ParticleManager INSTANCE;
    public static ParticleManager getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new ParticleManager();
        return INSTANCE;
    }

    private final HashMap<String,Particle> particleMap = new HashMap<>();
    private final ArrayList<Particle> particles = new ArrayList<>();

    public ParticleManager(){
        // add particles
        particleMap.put("greenSlimeDeath",new Particle(new String[]{"slime3", "slime4", "slime5", "slime6"},0,2));
        particleMap.put("greenGore0",new Particle(new String[]{"gore0"},30,0));
        particleMap.put("greenGore1",new Particle(new String[]{"gore1"},30,0));
        particleMap.put("greenGore2",new Particle(new String[]{"gore2"},30,0));
        particleMap.put("greenGore3",new Particle(new String[]{"gore3"},30,0));

    }

    public void addParticle(String name, float x, float y, float xM, float yM, float gravity){
        particles.add(particleMap.get(name).clone(x,y,xM,yM,gravity));
    }
    // also changes lifetime
    public void addParticle(String name, float x, float y, float xM, float yM, float gravity, int lifeTime){
        Particle temp = particleMap.get(name).clone(x,y,xM,yM,gravity);
        temp.setLifeTime(lifeTime);
        particles.add(temp);

    }

    public void update(){
        for (Particle part: particles) {
            part.update();
        }
        //destroy marked particles
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isMarked()){
                particles.remove(i);
                i--;
            }
        }
    }

    public void draw(SpriteManager spr){
        for (Particle part: particles) {
            spr.draw(part.getSpr(),part.getX(), part.getY());
        }
    }

    public void shiftPartsBy(float x){
        for (Particle part: particles) {
            part.shiftX(-x);
        }
    }

}
