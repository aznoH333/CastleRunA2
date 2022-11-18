package com.mygdx.game.ui.elements.regularElements;

import com.mygdx.game.Game;
import com.mygdx.game.data.IIntegerFunction;
import com.mygdx.game.data.enums.BarType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;
import com.mygdx.game.ui.interfaces.IUIUpdateOnInit;

public class HudBar implements IUIElement, IUIUpdatable, IUIUpdateOnInit {

    private final float x;
    private final float y;
    private final String barSprite;
    private final BarType barType;
    private final IUIElement parent;
    private int value;
    private int maxValue;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private float cellSize;
    private float cellWidth;
    private final static int barLength = (int)Game.gameWorldWidth - 256;
    private final IIntegerFunction valueGetter;
    private final IIntegerFunction maxValueGetter;
    private final float overLayWidth = (float) (barLength - 64) / 32 + 0.5f;

    public HudBar(float x, float y, String barSprite, BarType barType, IUIElement parent, IIntegerFunction valueGetter, IIntegerFunction maxValueGetter) {
        this.x = x;
        this.y = y;
        this.barSprite = barSprite;
        this.barType = barType;
        this.parent = parent;
        this.value = valueGetter.function();
        this.maxValue = maxValueGetter.function();
        this.cellSize = (float) barLength/maxValue;
        this.cellWidth = (cellSize - 32) / 32;
        this.valueGetter = valueGetter;
        this.maxValueGetter = maxValueGetter;
    }


    @Override
    public void draw() {
        // intersecting points
        for (int i = 0; i < maxValue; i++) {
            if (i < value) {
                spr.draw(barType.barEnd, x + i * cellSize + parent.getX() + cellSize - 32, y + parent.getY(), 5, false);
                spr.draw(barType.bar, x + i * cellSize + parent.getX(), y + parent.getY(), 5, false, cellWidth, 1f, true);
            }else {
                spr.draw("meter4", x + i * cellSize + parent.getX() + cellSize - 32, y + parent.getY(), 5, false);
                spr.draw("meter5", x + i * cellSize + parent.getX(), y + parent.getY(), 5, false, cellWidth, 1f, true);
            }
        }


        // overlay
        spr.draw(barSprite, parent.getX() + x, parent.getY() + y, 5, false);
        spr.draw("bar0", parent.getX() + x + 32, y + parent.getY(), 5, false, overLayWidth, 1f, true);
        spr.draw("bar1", parent.getX() + x + barLength - 32, parent.getY() + y, 5, false);


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
    public void update() {
        this.value = valueGetter.function();
    }

    @Override
    public void updateOnInit() {
        maxValue = maxValueGetter.function();
        cellSize = (float) barLength/maxValue;
        cellWidth = (cellSize - 32) / 32;
    }
}
