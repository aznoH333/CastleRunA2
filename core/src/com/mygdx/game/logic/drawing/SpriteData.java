package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.data.enums.DrawingDataType;

public class SpriteData implements DrawingData{
    private final TextureRegion sprite;
    private final float x;
    private final float y;
    private final boolean affectedByScreenShake;
    private final float scaleX;
    private final float scaleY;
    private final Color color;

    public SpriteData(TextureRegion sprite, float x, float y, boolean affectedByScreenShake, float scaleX, float scaleY, Color color){
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.affectedByScreenShake = affectedByScreenShake;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.color = color;
    }

    @Override
    public TextureRegion getSprite() {
        return sprite;
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
    public float getXScale() {
        return scaleX;
    }

    @Override
    public float getYScale() {
        return scaleY;
    }


    @Override
    public Color getColor() {
        return color;
    }
}
