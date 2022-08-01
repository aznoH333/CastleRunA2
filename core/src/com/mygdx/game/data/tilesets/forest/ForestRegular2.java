package com.mygdx.game.data.tilesets.forest;

import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.level.tileCollums.TileCollum;

public class ForestRegular2 extends TileCollum {
    private static final String[] sprites = {"forest4", "forest1", "forest2"};
    private static final String repeated = "forest3";
    private static final TileCollumSpecial special = TileCollumSpecial.None;
    private static final boolean grace = false;


    public ForestRegular2(){
        super(sprites, repeated, grace, special);
    }
    public ForestRegular2(int y){super(y,sprites, repeated, grace, special);}

    @Override
    public TileCollum getNew(int y) {
        return new ForestRegular2(y);
    }

}

