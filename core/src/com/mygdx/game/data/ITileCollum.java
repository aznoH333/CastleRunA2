package com.mygdx.game.data;

public interface ITileCollum {

    public String getTexture(int index);

    public TileCollumSpecial getSpecial();

    public boolean grace();
    //bad code moment
    public ITileCollum getNew(int y);

    public int getY();
}
