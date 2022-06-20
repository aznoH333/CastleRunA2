package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.data.enums.DrawingDataType;

public class TextData implements DrawingData{
    private final float x;
    private final float y;
    private final String text;


    public TextData(String text, float x, float y){
        this.text = text;
        this.x = x;
        this.y = y;
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
}
