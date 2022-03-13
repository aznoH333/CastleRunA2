package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.graphics.Texture;

public class SpriteData {
    private final Texture texture;
    private final float x;
    private final float y;
    public SpriteData(Texture texture, float x, float y){
        this.texture = texture;
        this.x = x;
        this.y = y;
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
}
