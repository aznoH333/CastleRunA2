package com.mygdx.game.logic.drawing;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.data.enums.DrawingDataType;

public interface DrawingData {
    float getX();
    float getY();
    Texture getTexture();
    String getText();
    DrawingDataType getType();
}
