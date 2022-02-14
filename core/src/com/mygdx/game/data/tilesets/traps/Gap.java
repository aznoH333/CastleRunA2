package com.mygdx.game.data.tilesets.traps;

import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;

public class Gap extends TileCollum {

    private static final String[] sprites = {};
    private static final String repeated = "";
    private static final TileCollumSpecial special = TileCollumSpecial.Gap;
    private static final boolean grace = true;

    public Gap(){
        super(sprites, repeated, grace, special);
    }
    public Gap(int y){super(y,sprites, repeated, grace, special);}

    @Override
    public TileCollum getNew(int y) {
        return new Gap(-9999);
    }

    @Override
    public void update() {

    }
    @Override
    public void draw(float x, float y) {

    }

}
