package com.mygdx.game.logic.entities;

import com.mygdx.game.data.load.EntityLoadList;
import com.mygdx.game.logic.entities.abstracts.Entity;

import java.util.HashMap;

public class EntityFactory {
    private static EntityFactory INSTANCE;
    public static EntityFactory getInstance(){
        if (INSTANCE == null)
            INSTANCE = new EntityFactory();
        return INSTANCE;
    }

    private final HashMap<String, Entity> entities;

    public EntityFactory(){
        entities = EntityLoadList.loadEntities();
    }

    public Entity getByName(String name, float x, float y){
        return entities.get(name).getCopy(x,y);
    }

    public Entity getByName(String name, float x, float y, int specialParam){
        return entities.get(name).getCopy(x,y, specialParam);
    }
}
