package com.mygdx.game.items.items;

import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemOnKill;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.player.PlayerStats;

public class VampireCharm implements IItem, IItemOnKill {

    private byte enemyKillCounter = 0;

    @Override
    public String getSprite() {
        return "itemPreview1";
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
        return "Vampire charm";
    }

    @Override
    public void onKillFunction(Entity enemy) {
        enemyKillCounter++;

        if (enemyKillCounter == 5){
            PlayerStats.getINSTANCE().heal(1);
            enemyKillCounter = 0;
        }
    }
}
