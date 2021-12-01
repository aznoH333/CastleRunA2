package com.mygdx.game.data.tilesets.universal;

import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;

public class Gap extends TileCollum {

    private static final String[] sprites = {};
    private static final String repeated = "";
    private static final TileCollumSpecial special = TileCollumSpecial.Gap;
    private static final boolean grace = false;

    public Gap(){
        super(sprites, repeated, grace, special);
    }

    // FIXME: gaps having collisions
}
