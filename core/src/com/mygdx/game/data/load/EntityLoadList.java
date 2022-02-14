package com.mygdx.game.data.load;

import com.mygdx.game.entities.enemies.*;
import com.mygdx.game.entities.environment.*;
import com.mygdx.game.entities.player.Projectiles.*;
import com.mygdx.game.logic.entities.Entity;

import java.util.HashMap;

public class EntityLoadList {
    public static HashMap<String,Entity> loadEntities(){
        HashMap<String, Entity> entities = new HashMap<>();

        //add enemies
        entities.put("slime", new Slime(0,0,64,64,1));
        entities.put("red slime", new RedSlime(0,0,64,64,2));


        //add projectiles
        entities.put("sword swipe", new SwordSwipe(0,0,64,64, 1));
        entities.put("charged sword swipe", new ChargedSwordSwipe(0,0,64,64, 1));
        entities.put("dagger", new Dagger(0,0,48,32, 1));
        entities.put("charged dagger", new ChargedDagger(0,0,48,32, 1));
        entities.put("throwing axe", new Axe(0,0,48,32, 1));
        entities.put("charged throwing axe", new ChargedAxe(0,0,48,32, 1));

        // FIXME normalize naming conventions
        // pickups
        entities.put("energy pickup", new EnergyPickup(0,0,16,16,1));
        entities.put("coin pickup", new Coin(0,0,16,16,1));
        entities.put("picked coin", new PickedCoin(0,0,16,16,1));
        entities.put("health potion", new HealthPotion(0,0,32,32,1));


        // environment
        entities.put("chest", new Chest(0,0,64,64,1));
        entities.put("exit door", new ExitDoor(0,0,64,64,1));

        //return
        return entities;
    }
}
