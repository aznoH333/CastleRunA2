package com.mygdx.game.ui.elements.regularElements;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Game;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.player.ProgressManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class XpBar implements IUIElement, IUIUpdatable {


    private final IUIElement parent;
    private final float x;
    private final float y;
    private final float width;
    private final float middleSegmentScale;

    public XpBar(float x, float y, IUIElement parent){
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = Game.gameWorldWidth - 48;
        this.middleSegmentScale = ((width - 16) / 16) + 0.5f;

    }

    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    @Override
    public void draw() {
        spr.draw("lvlBar0",x + parent.getX(),y + parent.getY(),5, false);
        spr.draw("lvlBar1",x + parent.getX() + 16,y + parent.getY(), 5, middleSegmentScale, 1f, Color.WHITE, false);
        spr.draw("lvlBar3",x + parent.getX() + 16, y + parent.getY(), 4, middleSegmentScale * xpPercentage, 1f, Color.WHITE, false);
        spr.draw("lvlBar2",x + width + parent.getX(),y + parent.getY(),5, false);
    }

    @Override
    public float getX() {
        return x + parent.getX();
    }

    @Override
    public float getY() {
        return y + parent.getY();
    }


    private float xpPercentage = 0;
    private static final ProgressManager prgrs = ProgressManager.getINSTANCE();
    @Override
    public void update() {
        xpPercentage = (float) prgrs.getXp() / prgrs.getNextLevelXpRequirement();
    }
}
