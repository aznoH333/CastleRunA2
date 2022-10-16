package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.data.enums.DrawingDataType;

public class SpriteData implements DrawingData{
    private final Texture texture;
    private final float x;
    private final float y;
    private final boolean affectedByScreenShake;
    private final float scale;
    private final ColorType color;
    public SpriteData(Texture texture, float x, float y, boolean affectedByScreenShake, float scale, ColorType color){
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.affectedByScreenShake = affectedByScreenShake;
        this.scale = scale;
        this.color = color;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public DrawingDataType getType() {
        return DrawingDataType.Sprite;
    }

    @Override
    public boolean affectedByScreenShake() {
        return affectedByScreenShake;
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public ColorType getColor() {
        return color;
    }
}
