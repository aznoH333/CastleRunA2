package com.mygdx.game.items.items;

import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemOnKill;
import com.mygdx.game.items.interfaces.IItemOnLevelStart;
import com.mygdx.game.items.interfaces.ItemActivationType;

public class DebugItem implements IItem, IItemOnLevelStart, IItemOnKill {

    @Override
    public ItemActivationType[] getActivationType() {
        return new ItemActivationType[]{
                ItemActivationType.OnLevelStart
                , ItemActivationType.OnKill};
    }

    @Override
    public String getSprite() {
        return "player0";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public float getSpawnChance() {
        return 0;
    }

    @Override
    public void onLevelStart() {
        System.out.println("intefacy jsou asi cool");
    }

    @Override
    public void onKillFunction(float enemyX, float enemyY) {
        System.out.println("vražda je poggers");
    }
}
