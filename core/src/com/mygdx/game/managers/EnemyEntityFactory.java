package com.mygdx.game.managers;

import com.mygdx.game.data.Entity;
import com.mygdx.game.entities.Slime;

import java.util.HashMap;

public class EnemyEntityFactory {
    private static EnemyEntityFactory INSTANCE;
    public static EnemyEntityFactory getInstance(){
        if (INSTANCE == null)
            INSTANCE = new EnemyEntityFactory();
        return INSTANCE;
    }

    private final HashMap<String, Entity> enemies = new HashMap<>();

    public EnemyEntityFactory(){
        //add enemies
        enemies.put("slime", new Slime(0,0,64,64,1));
    }

    public Entity getByName(String name, float x, float y){
        return enemies.get(name).getCopy(x,y);
    }
}
