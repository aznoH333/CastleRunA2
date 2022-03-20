package com.mygdx.game.logic.player;

import com.mygdx.game.items.interfaces.IItem;
import com.mygdx.game.items.interfaces.IItemAlways;
import com.mygdx.game.items.interfaces.IItemOnAttack;
import com.mygdx.game.items.interfaces.IItemOnKill;
import com.mygdx.game.items.interfaces.IItemOnLevelStart;
import com.mygdx.game.items.interfaces.ItemActivationType;

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


    public void clearItems(){
        items.clear();
        onLevelStarts.clear();
        onAttacks.clear();
        onKills.clear();
        always.clear();
    }

    public void addItem(IItem item){
        items.add(item);
        ItemActivationType[] list = item.getActivationType();

        for (ItemActivationType type: list) {
            switch (type){
                case Always:
                    always.add((IItemAlways) item);
                    break;
                case OnKill:
                    onKills.add((IItemOnKill) item);
                    break;
                case OnAttack:
                    onAttacks.add((IItemOnAttack) item);
                    break;
                case OnLevelStart:
                    onLevelStarts.add((IItemOnLevelStart) item);
                    break;
            }
        }
    }

    // item activations
    public void onLevelStart(){
        for (IItemOnLevelStart item: onLevelStarts) {
            item.onLevelStart();
        }
    }

    public void onKill(float enemyX, float enemyY){
        for (IItemOnKill item: onKills) {
            item.onKillFunction(enemyX, enemyY);
        }
    }
}