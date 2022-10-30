package com.mygdx.game.ui.elements.regularElements;

import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.ui.interfaces.IUIElement;

public class CoinCounter implements IUIElement {

    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    private static final PlayerStats ps =PlayerStats.getINSTANCE();
    private final float x;
    private final float y;
    private final IUIElement parent;

    public CoinCounter(float x, float y, IUIElement parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    @Override
    public void draw() {
        spr.draw("coinCounter0", x + parent.getX(), y + parent.getY(), 5, false);
        spr.drawText(String.valueOf(ps.getCoins()), x + 64 + parent.getX(), y + parent.getY() + 48, 6);
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
