package com.mygdx.game.items.items;

import com.mygdx.game.entities.player.itemEntities.FriendlyOrb;
import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemOnLevelStart;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.player.PlayerStats;


public class FriendlyOrbItem implements IItem, IItemOnLevelStart {

    private final EntityManager ent = EntityManager.getINSTANCE();
    private final PlayerStats stats = PlayerStats.getINSTANCE();


    @Override
    public String getSprite() {
        return "itemPreview2";
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
        return "Friendly orb";
    }

    @Override
    public void onLevelStart() {
        ent.addEntity(new FriendlyOrb(stats.getPlayer().getX() - 8, stats.getPlayer().getY() - 8));
    }
}
