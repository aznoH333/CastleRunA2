package com.mygdx.game.data.enums;

import com.mygdx.game.logic.level.tileCollums.TileCollum;

public enum TileCollumSpecial {
    None(true),
    Gap(false),
    SpikeTrap(true),
    DisappearingPlatform(false);

    public final boolean draws;

    TileCollumSpecial(boolean draws){
        this.draws = draws;
    }
}
