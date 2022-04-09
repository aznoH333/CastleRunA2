package com.mygdx.game.items.items;

import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemOnAttack;
import com.mygdx.game.logic.entities.abstracts.Entity;

public class FireEmblem implements IItem, IItemOnAttack {

    @Override
    public String getSprite() {
        return "slime0";
    }

    @Override
    public int getCost() {
        return 15;
    }

    @Override
    public float getSpawnChance() {
        return 0;
    }

    @Override
    public String getName() {
        return "Fire emblem";
    }

    @Override
    public void attackFunction(Entity projectile) {
        // TODO: this

    }
}
