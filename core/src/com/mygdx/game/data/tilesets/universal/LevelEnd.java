package com.mygdx.game.data.tilesets.universal;

import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;

public class LevelEnd extends TileCollum {

    private static final String[] sprites = {};
    private static final String repeated = "player1";
    private static final TileCollumSpecial special = TileCollumSpecial.None;
    private static final boolean grace = false;

    public LevelEnd() {
        super(sprites, repeated, grace, special);
    }

    public LevelEnd(int y) {
        super(y, sprites, repeated, grace, special);
    }

    @Override
    public TileCollum getNew(int y) {
        return new LevelEnd(y);
    }
    @Override
    public void update() {

    }
    @Override
    public void draw(float x, float y) {

    }
}
