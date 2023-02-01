package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.data.enums.DrawingDataType;

public class TextData implements DrawingData{
    private final float x;
    private final float y;
    private final String text;
    private final float scale;
    private final Color color;


    public TextData(String text, float x, float y, float scale, Color color){
        this.text = text;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.color = color;
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
    public Sprite getSprite() {
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
    public float getXScale() {
        return scale;
    }

    public float getYScale() {
        return scale;
    }


    @Override
    public Color getColor() {
        return color;
    }

}
