package com.mygdx.game.data.tilesets.forest;

import com.mygdx.game.logic.level.tileCollums.TileCollum;
import com.mygdx.game.data.enums.TileCollumSpecial;

public class ForestRegular extends TileCollum {

    private static final String[] sprites = {"forest0", "forest1", "forest2","forest3"};
    private static final String repeated = "forest4";
    private static final TileCollumSpecial special = TileCollumSpecial.None;
    private static final boolean grace = false;


    public ForestRegular(){
        super(sprites, repeated, grace, special);
    }
    public ForestRegular(int y){super(y,sprites, repeated, grace, special);}

    @Override
    public TileCollum getNew(int y) {
        return new ForestRegular(y);
    }

}
