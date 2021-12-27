package com.mygdx.game.data.load;

import com.mygdx.game.entities.enemies.Slime;
import com.mygdx.game.entities.environment.Chest;
import com.mygdx.game.entities.environment.Coin;
import com.mygdx.game.entities.environment.EnergyPickup;
import com.mygdx.game.entities.environment.PickedCoin;
import com.mygdx.game.entities.player.Projectiles.ChargedDagger;
import com.mygdx.game.entities.player.Projectiles.Dagger;
import com.mygdx.game.entities.player.Projectiles.SwordSwipe;
import com.mygdx.game.logic.entities.Entity;

import java.util.HashMap;

public class EntityLoadList {
    public static HashMap<String,Entity> loadEntities(){
        HashMap<String, Entity> entities = new HashMap<>();

        //add enemies
        entities.put("slime", new Slime(0,0,64,64,1));


        //add projectiles
        entities.put("sword swipe", new SwordSwipe(0,0,64,64, 1));
        entities.put("dagger", new Dagger(0,0,48,32, 1));
        entities.put("charged dagger", new ChargedDagger(0,0,48,32, 1));

        // FIXME normalize naming conventions
        // pickups
        entities.put("energy pickup", new EnergyPickup(0,0,16,16,1));
        entities.put("coin pickup", new Coin(0,0,16,16,1));
        entities.put("picked coin", new PickedCoin(0,0,16,16,1));


        // environment
        entities.put("chest", new Chest(0,0,64,64,1));

        //return
        return entities;
    }
}
