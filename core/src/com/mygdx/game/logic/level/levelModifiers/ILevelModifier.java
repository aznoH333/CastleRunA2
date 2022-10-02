package com.mygdx.game.logic.level.levelModifiers;


public interface ILevelModifier {
    String getIntroMessage();
    void levelModifierTick();
    void onLevelStart();
    void onTileGenerate();
}
