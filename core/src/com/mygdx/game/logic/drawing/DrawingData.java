package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.data.enums.DrawingDataType;

public interface DrawingData {
    float getX();
    float getY();
    TextureRegion getSprite();
    String getText();
    DrawingDataType getType();
    boolean affectedByScreenShake();
    float getXScale();
    float getYScale();
    Color getColor();
}
