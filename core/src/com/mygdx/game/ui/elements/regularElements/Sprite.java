package com.mygdx.game.ui.elements.regularElements;

import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;

public class Sprite implements IUIElement {
    private final float x;
    private final float y;
    private final String sprite;
    private final IUIElement parent;
    private static final DrawingManager spr = DrawingManager.getINSTANCE();

    public Sprite(float x, float y, String sprite, IUIElement parent){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.parent = parent;
    }

    @Override
    public void draw() {
        spr.draw(sprite,x+parent.getX(),y+parent.getY(),5, false);
    }

    @Override
    public float getX() {
        return x + parent.getX();
    }

    @Override
    public float getY() {
        return y + parent.getY();
    }

}
