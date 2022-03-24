package com.mygdx.game.ui.interfaces;

import com.mygdx.game.data.enums.UIType;

public interface IUIElement {
    void draw();
    float getX();
    float getY();
    UIType[] getTypes();
}
