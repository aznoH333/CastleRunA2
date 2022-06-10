package com.mygdx.game.ui.elements.regularElements;

import com.mygdx.game.Game;
import com.mygdx.game.data.IIntegerFunction;
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
    private int value;
    private int maxValue;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private final float cellSize;
    private final static int barLength = (int)Game.gameWorldWidth/2-64; // length of the sprite (default = 280)
    private final static int xOffset = 32;
    private final static int yOffset = 12;
    private final IIntegerFunction valueGetter;
    private final IIntegerFunction maxValueGetter;

    public HudBar(float x, float y, String barSprite, BarType barType, IUIElement parent, IIntegerFunction valueGetter, IIntegerFunction maxValueGetter) {
        this.x = x;
        this.y = y;
        this.barSprite = barSprite;
        this.barType = barType;
        this.parent = parent;
        this.value = valueGetter.function();
        this.maxValue = maxValueGetter.function();
        cellSize = (float) barLength/maxValue;
        this.valueGetter = valueGetter;
        this.maxValueGetter = maxValueGetter;
    }


    @Override
    public void draw() {
        for (int i = 0; i < maxValue; i++) {
            if (i < value){
                spr.draw(barType.barStart, x + i * cellSize + parent.getX() + xOffset, y + parent.getY() + yOffset, 5);
                for (int j = 0; j < cellSize - 24; j+=12) {
                    spr.draw(barType.bar, x + parent.getX() + j  + 12 + i*cellSize + xOffset, y + parent.getY() + yOffset, 5);
                }
                spr.draw(barType.barEnd, x + i * cellSize + parent.getX() + cellSize - 12 + xOffset, y + parent.getY() + yOffset, 5);
            }else{
                spr.draw("meter3", x + i * cellSize + parent.getX() +xOffset, y + parent.getY() + yOffset, 5);
                for (int j = 0; j < cellSize - 24; j+=12) {
                    spr.draw("meter4", x + parent.getX() + j  + 12 + i*cellSize + xOffset, y + parent.getY() + yOffset, 5);
                }
                spr.draw("meter5", x + i * cellSize + parent.getX() + cellSize - 12 + xOffset, y + parent.getY() + yOffset, 5);

            }
        }


        // draw overlay
        spr.draw(barSprite, parent.getX() + x, parent.getY() + y, 5);
        for (int i = 56; i < barLength + 32; i+=16) {
            spr.draw("bar0", parent.getX() + x + i, y + parent.getY(), 5);
        }
        spr.draw("bar1", parent.getX() + x + barLength + 32, parent.getY() + y, 5);
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
        this.value = valueGetter.function();
        this.maxValue = maxValueGetter.function();
    }
}
