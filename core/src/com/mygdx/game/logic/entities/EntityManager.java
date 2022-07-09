package com.mygdx.game.logic.entities;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.player.ItemManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.PlayerStats;

import java.util.ArrayList;
import java.util.Random;

public class EntityManager {
    private final LevelManager lvl;
    private final DrawingManager spr;
    private final Random r;
    private final ArrayList<Entity> entities;
    private final ArrayList<Entity> entitySpawnQueue;


    private static EntityManager INSTANCE;

    public static EntityManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new EntityManager();
        return INSTANCE;
    }

    // a nasty workaround but i am very lazy


    public EntityManager(){
        this.lvl = LevelManager.getINSTANCE();
        this.r = Game.getGeneralRandom();
        this.spr = DrawingManager.getINSTANCE();
        entities = new ArrayList<>();
        entitySpawnQueue = new ArrayList<>();
    }


    public void update(){
        // clear queue
        entities.addAll(entitySpawnQueue);
        entitySpawnQueue.clear();

        for (Entity ent: entities) {
            //kill if falls outside off lvl
            if (ent.getY() < -ent.getYSize() || ent.getX() < -lvl.getTileScale())
                if (ent.getTeam() == Team.Player) {PlayerStats.getINSTANCE().setHp(0);ent.destroy();}
                else ent.safeDelete();

            //entity collisions
            for (Entity entOther: entities) {
                if (ent.collide(entOther) && !ent.equals(entOther))
                    ent.onCollide(entOther);
            }


            ent.update(lvl,r);
            if (ent instanceof Enemy)
                ((Enemy)ent).updateEffects();
        }

        deleteEntities();
        render();
    }

    private void render(){
        for (Entity ent: entities) {
            ent.draw(spr);
        }
    }

    // outdated
    public void addEntity(Entity ent){
        entitySpawnQueue.add(ent);
    }

    // spawns a new entity at x y
    public void spawnEntity(String name,float x, float y){
        addEntity(EntityFactory.getInstance().getByName(name,x,y));
    }
    public void spawnEntity(String name,float x, float y, int specialParam){
        addEntity(EntityFactory.getInstance().getByName(name,x,y, specialParam));
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
                Entity ent = entities.get(i);
                if (ent.triggerEffects()){
                    ent.onDestroy();
                    if (ent.getTeam() == Team.Enemies)
                        ItemManager.getINSTANCE().onKill(ent);
                }
                entities.remove(i);
                i--;
            }
        }
    }

    // FIXME : can crash the game if called in the same frame as entity spawn
    public void clearEnemyEntities(){
        for (Entity entity: entities) {
            if (entity.getTeam() == Team.Enemies || entity.getTeam() == Team.EnemyProjectiles)
                entity.destroy();
        }
        deleteEntities();
    }

    public void clear(){
        for (Entity entity: entities) {
            entity.safeDelete();
        }
        deleteEntities();
        entitySpawnQueue.clear();
    }
}
