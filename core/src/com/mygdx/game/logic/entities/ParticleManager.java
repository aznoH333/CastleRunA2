package com.mygdx.game.logic.entities;

import com.mygdx.game.data.Particle;
import com.mygdx.game.data.load.ParticleLoadList;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ParticleManager {
    private static ParticleManager INSTANCE;
    public static ParticleManager getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new ParticleManager();
        return INSTANCE;
    }

    private final HashMap<String,Particle> particleMap;
    private final ArrayList<Particle> particles = new ArrayList<>();

    public ParticleManager(){
        // add particles
        particleMap = ParticleLoadList.loadParticles();
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
        //destroy marked particles
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isMarked()){
                particles.remove(i);
                i--;
            }
        }


        for (Particle part: particles) {
            part.update();
        }

    }

    public void draw(DrawingManager spr){
        for (Particle part: particles) {
            spr.draw(part.getSpr(),part.getX(), part.getY(),3);
        }
    }

    public void shiftPartsBy(float x){
        for (Particle part: particles) {
            part.shiftX(-x);
        }
    }

    public void clear() {
        particles.clear();
    }
}
