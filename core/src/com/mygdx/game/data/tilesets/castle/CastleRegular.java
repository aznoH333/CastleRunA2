package com.mygdx.game.data.tilesets.castle;

import com.mygdx.game.data.ITileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;

public class CastleRegular implements ITileCollum {

    private String[] regularTiles = {"castle0", "castle1", "castle2"};
    private String repeated = "castle3";
    private int y = 0;


    // bad code moment
    public CastleRegular(){}
    public CastleRegular(int y){this.y = y;}

    @Override
    public String getTexture(int index) {
        if (index < regularTiles.length) return regularTiles[index];
        return repeated;
    }

    @Override
    public TileCollumSpecial getSpecial() {
        return TileCollumSpecial.None;
    }

    @Override
    public boolean grace() {
        return false;
    }

    @Override
    public ITileCollum getNew(int y) {
        return new CastleRegular(y);
    }

    @Override
    public int getY() {
        return y;
    }
}
