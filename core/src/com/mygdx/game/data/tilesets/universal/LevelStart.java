package com.mygdx.game.data.tilesets.universal;

import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;

public class LevelStart extends TileCollum {

    private static final String[] sprites = {};
    private static final String repeated = "player0";
    private static final TileCollumSpecial special = TileCollumSpecial.None;
    private static final boolean grace = false;

    public LevelStart() {
        super(sprites, repeated, grace, special);
    }

    public LevelStart(int y) {
        super(y, sprites, repeated, grace, special);
    }

    @Override
    public TileCollum getNew(int y) {
        return new LevelStart(y);
    }
    @Override
    public void update() {

    }
    @Override
    public void draw(float x, float y) {

    }
}
