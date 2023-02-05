package com.mygdx.game.data.tilesets.traps;

import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.level.tileCollums.ISpecialTile;
import com.mygdx.game.logic.level.tileCollums.TileCollum;

public class Spikes extends TileCollum implements ISpecialTile {


    private final static String[] sprites = new String[]{"spikes1", "spikes2"};

    public Spikes() {
        super(sprites, "spikes3", true, TileCollumSpecial.Spikes);
        hurts = true;
    }

    public Spikes(int y) {
        super(y, sprites, "spikes3", true, TileCollumSpecial.Spikes);
        hurts = true;
    }

    @Override
    public TileCollum getNew(int y) {
        return new Spikes(y);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(float x, float y) {
        DrawingManager.getINSTANCE().draw("spikes0", x, y + 64, 1);
    }
}
