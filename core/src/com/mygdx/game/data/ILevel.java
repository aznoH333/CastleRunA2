package com.mygdx.game.data;

import java.util.Random;

public interface ILevel {
    // it returns tiles according to their spawn chance
    ITileCollum randomTile(Random r, int y);

    Entity randomEnemy(Random r, float x, int y);
    // TODO: add a lot of level generation related stuff here

    //height
    int defaultHeight();
    int maxHeight();
    int minHeight();

    //height change
    float changeChance();
    int changeLengthMax();
    int changeLengthMin();
}
