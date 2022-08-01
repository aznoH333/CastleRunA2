package com.mygdx.game.data.tilesets.forest;

import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.level.tileCollums.TileCollum;

public class ForestRegular3 extends TileCollum {
    private static final String[] sprites = {"forest5", "forest1", "forest2"};
    private static final String repeated = "forest3";
    private static final TileCollumSpecial special = TileCollumSpecial.None;
    private static final boolean grace = false;


    public ForestRegular3(){
        super(sprites, repeated, grace, special);
    }
    public ForestRegular3(int y){super(y,sprites, repeated, grace, special);}

    @Override
    public TileCollum getNew(int y) {
        return new ForestRegular3(y);
    }

}

