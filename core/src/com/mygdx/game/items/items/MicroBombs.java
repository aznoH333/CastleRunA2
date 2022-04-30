package com.mygdx.game.items.items;

import com.mygdx.game.Game;
import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemOnKill;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.abstracts.Entity;

public class MicroBombs implements IItem, IItemOnKill {
    @Override
    public String getSprite() {
        return "itemPreview3";
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
        return "Micro bombs";
    }

    @Override
    public void onKillFunction(Entity enemy) {

        EntityManager ent = EntityManager.getINSTANCE();
        for (int i = Game.getGeneralRandom().nextInt(1)+3; i>0; i--){
            ent.spawnEntity("micro bomb", enemy.getX(), enemy.getY());
        }

    }
}
