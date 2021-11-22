package com.mygdx.game.managers;

import com.mygdx.game.data.Entity;

import java.util.ArrayList;
import java.util.Random;

public class EntityManager {
    private final LevelManager lvl;
    private final SpriteManager spr;
    private final Random r;
    private final ArrayList<Entity> entities;
    private final ArrayList<Entity> entitySpawnQueue;

    private static EntityManager INSTANCE;

    public static EntityManager getINSTANCE(){
        return INSTANCE;
    }

    // a nasty workaround but i am very lazy
    public static void createINSTANCE(LevelManager lvl, Random r, SpriteManager spr){
        INSTANCE = new EntityManager(lvl, r, spr);
    }

    public EntityManager(LevelManager lvl, Random r, SpriteManager spr){
        this.lvl = lvl;
        this.r = r;
        this.spr = spr;
        entities = new ArrayList<>();
        entitySpawnQueue = new ArrayList<>();
    }


    public void update(){
        // clear queue
        entities.addAll(entitySpawnQueue);
        entitySpawnQueue.clear();

        for (Entity ent: entities) {
            //kill if falls outside off lvl
            if (ent.getY() < -lvl.getTileScale() || ent.getX() < -lvl.getTileScale())
                ent.destroy();

            //entity collisions
            for (Entity entOther: entities) {
                if (ent.collide(entOther) && !ent.equals(entOther))
                    ent.onCollide(entOther);
            }

            ent.update(lvl,r);
        }

        deleteEntities();
        render();
    }

    private void render(){
        for (Entity ent: entities) {
            ent.draw(spr);
        }
    }

    public void addEntity(Entity ent){
        entitySpawnQueue.add(ent);
    }

    public void shiftAllEntities(float x){
        for (Entity ent: entities) {
                ent.shiftX(-x);
        }
    }

    private void deleteEntities(){
        // delete dead entities or marked
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getHP() <= 0 || entities.get(i).isMarked()){
                entities.get(i).onDestroy();
                entities.remove(i);
                i--;
            }
        }
    }

}
