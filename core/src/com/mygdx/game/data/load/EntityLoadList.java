package com.mygdx.game.data.load;

import com.mygdx.game.entities.enemies.Slime;
import com.mygdx.game.entities.player.Projectiles.ChargedDagger;
import com.mygdx.game.entities.player.Projectiles.Dagger;
import com.mygdx.game.logic.entities.Entity;

import java.util.HashMap;

public class EntityLoadList {
    public static HashMap<String,Entity> loadEntities(){
        HashMap<String, Entity> entities = new HashMap<>();

        //add enemies
        entities.put("slime", new Slime(0,0,64,64,1));


        //add projectiles
        entities.put("dagger", new Dagger(0,0,48,32, 1));
        entities.put("charged dagger", new ChargedDagger(0,0,48,32, 1));

        //return
        return entities;
    }
}
