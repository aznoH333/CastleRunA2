package com.mygdx.game.items.items;

import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemAlways;
import com.mygdx.game.items.interfaces.IItemOnLevelStart;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.PlayerStats;

public class CursedItem implements IItem, IItemAlways, IItemOnLevelStart {

    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    private static final PlayerStats ps = PlayerStats.getINSTANCE();
    private static final LevelManager lvl = LevelManager.getINSTANCE();
    private static final int deathThreshold = 150;
    private float skullOpacity = 0.0f;
    private int notMovedTimer = 0;
    private float lastDistance = 0;

    @Override
    public String getSprite() {
        return null;
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
        return "Cursed Skull";
    }

    @Override
    public void update() {
        if (lvl.getDistance() > lastDistance || lastDistance == lvl.getLevelLength()) notMovedTimer = 0;
        lastDistance = lvl.getDistance();
        notMovedTimer++;
        float expectedOpacity = ((float) notMovedTimer / deathThreshold) / 2;
        skullOpacity += (Math.abs(expectedOpacity - skullOpacity) / 6) * Math.signum(expectedOpacity - skullOpacity);
        if (notMovedTimer == deathThreshold){
            ps.getPlayer().destroy();
            ps.setHp(0);
        }
        spr.draw("death_skull0", ps.getPlayer().getX() - 96, ps.getPlayer().getY() - 100, 4, true, 1, skullOpacity);
        spr.draw("damage_overlay0", 0, 0, 4, false, 1, skullOpacity/4);
    }

    @Override
    public void onLevelStart() {
        notMovedTimer = - 200;
    }
}
