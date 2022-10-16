package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.data.enums.DrawingDataType;

public class TextData implements DrawingData{
    private final float x;
    private final float y;
    private final String text;
    private final float scale;


    public TextData(String text, float x, float y, float scale){
        this.text = text;
        this.x = x;
        this.y = y;
        this.scale = scale;
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
    public Texture getTexture() {
        return null;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public DrawingDataType getType() {
        return DrawingDataType.Text;
    }

    @Override
    public boolean affectedByScreenShake() {
        return false;
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public ColorType getColor() {
        return ColorType.Normal;
    }
}
