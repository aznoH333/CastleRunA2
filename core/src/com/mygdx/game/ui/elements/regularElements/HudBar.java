package com.mygdx.game.ui.elements.regularElements;

import com.mygdx.game.data.enums.BarType;
import com.mygdx.game.data.enums.UIType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class HudBar implements IUIElement, IUIUpdatable {

    private final float x;
    private final float y;
    private final String barSprite;
    private final BarType barType;
    private final IUIElement parent;
    private final int value;
    private final int maxValue;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private final float cellSize;
    private final static int barLength = 416; // length of the sprite
    private final static int xOffset = 32;
    private final static int yOffset = 12;

    public HudBar(float x, float y, String barSprite, BarType barType, IUIElement parent, int value, int maxValue) {
        this.x = x;
        this.y = y;
        this.barSprite = barSprite;
        this.barType = barType;
        this.parent = parent;
        this.value = value;
        this.maxValue = maxValue;
        cellSize = (float) barLength/maxValue;
    }


    @Override
    public void draw() {
        for (int i = 0; i < maxValue; i++) {
            if (i < value){
                spr.draw(barType.barStart, x + i * cellSize + parent.getX() + xOffset, y + parent.getY() + yOffset, 6);
                for (int j = 0; j < cellSize - 64; j+=32) {
                    spr.draw(barType.bar, x + parent.getX() + j  + 32 + i*cellSize + xOffset, y + parent.getY() + yOffset, 6);
                }
                spr.draw(barType.barEnd, x + i * cellSize + parent.getX() + cellSize - 32 + xOffset, y + parent.getY() + yOffset, 6);
            }else{
                spr.draw("meter3", x + i * cellSize + parent.getX() +xOffset, y + parent.getY() + yOffset, 6);
                for (int j = 0; j < cellSize - 64; j+=32) {
                    spr.draw("meter4", x + parent.getX() + j  + 32 + i*cellSize + xOffset, y + parent.getY() + yOffset, 6);
                }
                spr.draw("meter5", x + i * cellSize + parent.getX() + cellSize - 32 + xOffset, y + parent.getY() + yOffset, 6);

            }


        }
        // draw overlay
        spr.draw(barSprite, parent.getX() + x, parent.getY() + y, 6);
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
    public UIType[] getTypes() {
        return new UIType[]{UIType.Updatable};
    }

    @Override
    public void update() {
        // update value
    }
}
