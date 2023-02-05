package com.mygdx.game.items.items;

import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemOnAttack;

import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.items.effects.OnFire;

public class FireEmblem implements IItem, IItemOnAttack {

    @Override
    public String getSprite() {
        return "itemPreview0";
    }

    @Override
    public int getCost() {
        return 20;
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
    public void attackFunction(Projectile projectile) {
        projectile.addStatusEffect(new OnFire(60));

    }
}
