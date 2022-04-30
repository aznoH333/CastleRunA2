package com.mygdx.game.logic.player;

import com.mygdx.game.Game;
import com.mygdx.game.data.load.ItemLoadlist;
import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemAlways;
import com.mygdx.game.items.interfaces.IItemOnAttack;
import com.mygdx.game.items.interfaces.IItemOnKill;
import com.mygdx.game.items.interfaces.IItemOnLevelStart;
import com.mygdx.game.items.items.FortuneCrown;
import com.mygdx.game.items.items.FriendlyOrbItem;
import com.mygdx.game.items.items.MicroBombs;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;

import java.util.ArrayList;

public class ItemManager {
    private static ItemManager INSTANCE;

    public static ItemManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new ItemManager();
        return INSTANCE;
    }

    private final ArrayList<IItem> items = new ArrayList<>();
    private final ArrayList<IItemOnLevelStart> onLevelStarts = new ArrayList<>();
    private final ArrayList<IItemOnKill> onKills = new ArrayList<>();
    private final ArrayList<IItemAlways> always = new ArrayList<>();
    private final ArrayList<IItemOnAttack> onAttacks = new ArrayList<>();
    private final ArrayList<IItem> unseenItems = new ArrayList<>();

    public void clearItems(){
        items.clear();
        onLevelStarts.clear();
        onAttacks.clear();
        onKills.clear();
        always.clear();
        ItemLoadlist.loadItems(unseenItems);
        // cheat items addItem(new MicroBombs());
    }

    public void addItem(IItem item){
        items.add(item);
        if (item instanceof IItemOnKill) onKills.add((IItemOnKill) item);
        if (item instanceof IItemOnAttack) onAttacks.add((IItemOnAttack) item);
        if (item instanceof IItemOnLevelStart) onLevelStarts.add((IItemOnLevelStart) item);
        if (item instanceof IItemAlways) always.add((IItemAlways) item);
    }

    // item activations
    public void onLevelStart(){
        for (IItemOnLevelStart item: onLevelStarts) {
            item.onLevelStart();
        }
    }

    public void onKill(Entity enemy){
        for (IItemOnKill item: onKills) {
            item.onKillFunction(enemy);
        }
    }

    public void onAttack(Projectile projectile){
        for (IItemOnAttack item: onAttacks) {
            item.attackFunction(projectile);
        }
    }

    public IItem getRandomUnseenItem(){
        // weapons can only be seen once per run

        if (unseenItems.size() > 0){
            IItem temp = unseenItems.get(Game.getSeededRandom().nextInt(unseenItems.size()));
            unseenItems.remove(temp);
            return temp;
        }
        // TODO : this is literally breakfasting maybe reset the weapon list??
        return new FriendlyOrbItem();
    }
}
