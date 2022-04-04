package com.mygdx.game.data.tilesets.cave;

import com.mygdx.game.logic.level.tileCollums.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;

public class CaveRegular extends TileCollum {

    private static final String[] sprites = {"cave0", "cave1", "cave2",};
    private static final String repeated = "cave3";
    private static final TileCollumSpecial special = TileCollumSpecial.None;
    private static final boolean grace = false;

    public CaveRegular() {
        super(sprites, repeated, grace, special);
    }

    public CaveRegular(int y) {
        super(y, sprites, repeated, grace, special);
    }

    @Override
    public TileCollum getNew(int y) {
        return new CaveRegular(y);
    }

}
