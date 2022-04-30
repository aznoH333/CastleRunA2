package com.mygdx.game.items.items;

import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemOnKill;
import com.mygdx.game.logic.entities.abstracts.Entity;

public class MicroBombs implements IItem, IItemOnKill {
    @Override
    public String getSprite() {
        return "itemPreview2";
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

    }
}
