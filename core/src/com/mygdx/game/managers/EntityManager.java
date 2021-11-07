package com.mygdx.game.managers;

import com.mygdx.game.data.Entity;

import java.util.ArrayList;
import java.util.Random;

public class EntityManager {
    private Level lvl;
    private SpriteManager spr;
    private Random r;
    private ArrayList<Entity> entities;

    public EntityManager(Level lvl, Random r, SpriteManager spr){
        this.lvl = lvl;
        this.r = r;
        this.spr = spr;
        entities = new ArrayList<>();
    }

    public void update(){
        for (Entity ent: entities) {
            //kill if falls outside off lvl
            if (ent.getY() < -lvl.getTileScale())
                ent.takeDamage(Integer.MAX_VALUE);
            ent.update(lvl,r);
        }

        // delete dead entities
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getHP() <= 0){
                entities.remove(i);
                i--;
            }
        }


        render();
    }

    private void render(){
        for (Entity ent: entities) {
            ent.draw(spr);
        }
    }

    public void addEntity(Entity ent){
        entities.add(ent);
    }

    public void shiftAllEntities(float x){
        for (Entity ent: entities) {
                ent.shiftX(-x);
        }
    }

}
