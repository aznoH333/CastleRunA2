package com.mygdx.game.ui.elements;

import com.mygdx.game.data.enums.UIType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;

public class Text implements IUIElement {

    private final float x;
    private final float y;
    private final String text;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private final IUIElement parent;

    public Text(float x, float y, String text, IUIElement parent){
        this.x = x;
        this.y = y;
        this.text = text;
        this.parent = parent;
    }

    @Override
    public void draw() {
        spr.drawText(text,x+parent.getX(),y+parent.getY());
    }

    @Override
    public float getX() {
        return x+ parent.getX();
    }

    @Override
    public float getY() {
        return y+ parent.getY();
    }

    @Override
    public UIType[] getTypes() {
        return new UIType[0];
    }
}
