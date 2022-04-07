package com.mygdx.game.items.items;

import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemOnKill;
import com.mygdx.game.items.interfaces.IItemOnLevelStart;
import com.mygdx.game.logic.entities.Entity;

import java.util.Arrays;

public class DebugItem implements IItem, IItemOnLevelStart, IItemOnKill {


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
    public String getName() {
        return "Debug item";
    }

    @Override
    public void onLevelStart() {
        System.out.println("intefacy jsou asi cool");
    }

    @Override
    public void onKillFunction(Entity enemy) {
        System.out.println(Arrays.toString(enemy.getTags()));
    }
}
