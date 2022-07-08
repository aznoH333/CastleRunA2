package com.mygdx.game.data.levelgeneration;

import com.mygdx.game.logic.level.tileCollums.TileCollum;

public class TileWeightData {
    // can't use pairs, because java shits the bed if you try to initialize 'generic' arrays
    private final float weight;
    private final TileCollum tile;

    public TileWeightData(float weight, TileCollum tile){
        this.weight = weight;
        this.tile = tile;
    }

    public float getWeight() {
        return weight;
    }

    public TileCollum getTile() {
        return tile;
    }
}
