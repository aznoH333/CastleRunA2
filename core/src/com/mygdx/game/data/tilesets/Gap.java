package com.mygdx.game.data.tilesets;

import com.mygdx.game.data.ITileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;

public class Gap implements ITileCollum {

    @Override
    public String getTexture(int index) {
        return null;
    }

    @Override
    public TileCollumSpecial getSpecial() {
        return TileCollumSpecial.Gap;
    }

    @Override
    public boolean grace() {
        return true;
    }

    @Override
    public ITileCollum getNew(int y) {
        return new Gap();
    }

    @Override
    public int getY() {
        return -9999;
    }
}
