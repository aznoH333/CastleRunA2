package com.mygdx.game.logic.entities.effects;

public interface IStatusEffect {
    void update();
    void onDestroy();
    int getDuration();
}
