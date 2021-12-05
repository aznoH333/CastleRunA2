package com.mygdx.game.data;

import com.mygdx.game.data.enums.TileCollumSpecial;

public abstract class TileCollum {

    private final String[] sprites;
    private final String repeated;
    private final int y;
    private final TileCollumSpecial special;
    private final boolean grace;
    protected boolean hurts = false;

    public TileCollum(String[] sprites, String repeated, boolean grace, TileCollumSpecial special){
        this.sprites = sprites;
        this.repeated = repeated;
        this.grace = grace;
        this.special = special;
        y = 0;
    }

    public TileCollum(int y, String[] sprites, String repeated, boolean grace, TileCollumSpecial special){
        this.sprites = sprites;
        this.repeated = repeated;
        this.grace = grace;
        this.special = special;
        this.y = y;
    }

    public String getTexture(int index){
        if (index >= sprites.length) return repeated;
        return sprites[index];
    }

    public TileCollumSpecial getSpecial(){
        return special;
    }

    public boolean grace(){
        return grace;
    }
    //bad code moment
    public abstract TileCollum getNew(int y);

    public int getY(){
        return y;
    }

    public boolean getHurts(){
        return hurts;
    }

    public abstract void update();

    public abstract void draw(float x, float y);
}
