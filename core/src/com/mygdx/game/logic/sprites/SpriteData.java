package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.Texture;

public class SpriteData implements Comparable<SpriteData>{
    private final Texture texture;
    private final float x;
    private final float y;
    private final byte z;

    public SpriteData(Texture texture, float x, float y, byte z) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public byte getZ() {
        return z;
    }

    @Override
    public int compareTo(SpriteData o) {
        if (z >= o.getZ())
            return 1;
        return -1;
    }
}
