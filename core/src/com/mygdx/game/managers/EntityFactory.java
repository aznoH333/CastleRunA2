package com.mygdx.game.managers;

import com.mygdx.game.data.Entity;
import com.mygdx.game.entities.Projectiles.ChargedDagger;
import com.mygdx.game.entities.Projectiles.Dagger;
import com.mygdx.game.entities.Slime;

import java.util.HashMap;

public class EntityFactory {
    private static EntityFactory INSTANCE;
    public static EntityFactory getInstance(){
        if (INSTANCE == null)
            INSTANCE = new EntityFactory();
        return INSTANCE;
    }

    private final HashMap<String, Entity> entities = new HashMap<>();

    public EntityFactory(){
        // TODO: rewrite these to an external class like tile loadlist
        //add enemies
        entities.put("slime", new Slime(0,0,64,64,1));


        //add projectiles
        entities.put("dagger", new Dagger(0,0,48,32, 1));
        entities.put("charged dagger", new ChargedDagger(0,0,48,32, 1));

    }

    public Entity getByName(String name, float x, float y){
        return entities.get(name).getCopy(x,y);
    }
}
