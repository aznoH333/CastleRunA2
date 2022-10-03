package com.mygdx.game.logic.level.levelModifiers;


import com.mygdx.game.logic.level.tileCollums.TileCollum;

public interface ILevelModifier {
    String getIntroMessage();
    void levelModifierTick();
    void onLevelStart();
    void onTileGenerate(TileCollum collum);
}
