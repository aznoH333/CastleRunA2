package com.mygdx.game.items.interfaces;

public interface IItem {
    ItemActivationType[] getActivationType();

    String getSprite();

    int getCost();

    float getSpawnChance();
}
