package com.mygdx.game.ui.elements.regularElements;

import com.mygdx.game.data.IStringFunction;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class UpdatingSprite implements IUIElement, IUIUpdatable {

    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    private final float x;
    private final float y;
    private final IUIElement parent;
    private final IStringFunction function;
    private String sprite = "player0";

    public UpdatingSprite(float x, float y, IStringFunction function, IUIElement parent){
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.function = function;
        //sprite = function.function();
    }
    @Override
    public void draw() {
        spr.draw(sprite, x + parent.getX(), y + parent.getY(), 5, false);
    }

    @Override
    public float getX() {
        return x + parent.getX();
    }

    @Override
    public float getY() {
        return y + parent.getY();
    }

    @Override
    public void update() {
        sprite = function.function();
    }
}
