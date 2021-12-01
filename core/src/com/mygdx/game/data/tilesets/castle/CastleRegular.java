package com.mygdx.game.data.tilesets.castle;

import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;

public class CastleRegular extends TileCollum {

    private static final String[] sprites = {"castle0", "castle1", "castle2",};
    private static final String repeated = "castle3";
    private static final TileCollumSpecial special = TileCollumSpecial.None;
    private static final boolean grace = false;

    public CastleRegular(){
        super(sprites, repeated, grace, special);
    }
    public CastleRegular(int y){super(y,sprites, repeated, grace, special);}

    @Override
    public TileCollum getNew(int y) {
        return new CastleRegular(y);
    }
    @Override
    public void update() {

    }
    @Override
    public void draw(float x, float y) {

    }
}
