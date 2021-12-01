package com.mygdx.game.data.tilesets.universal;

import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;

public class SpikeTrap extends TileCollum {

    private static final String[] sprites = {"player0", "player1", "player2",};
    private static final String repeated = "player3";
    private static final TileCollumSpecial special = TileCollumSpecial.None;
    private static final boolean grace = false;

    public SpikeTrap(){
        super(sprites, repeated, grace, special);
    }

    // TODO: tile specials
}
