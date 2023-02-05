package com.mygdx.game.items.items;

import com.mygdx.game.Game;
import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemOnKill;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.abstracts.Entity;

public class FortuneCrown implements IItem, IItemOnKill {

    @Override
    public String getSprite() {
        return "itemPreview4";
    }

    @Override
    public int getCost() {
        return 30;
    }

    @Override
    public float getSpawnChance() {
        return 0;
    }

    @Override
    public String getName() {
        return "Fortune crown";
    }

    @Override
    public void onKillFunction(Entity enemy) {

        // maybe some luck stat??
        if (Game.getGeneralRandom().nextFloat() > 0.5f){
            EntityManager ent = EntityManager.getINSTANCE();
            for (int i = Game.getGeneralRandom().nextInt(2)+6; i > 0; i--){
                ent.spawnEntity("coin pickup", enemy.getX(), enemy.getY());
            }
        }
    }
}
