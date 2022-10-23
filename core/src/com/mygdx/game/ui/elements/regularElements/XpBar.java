package com.mygdx.game.ui.elements.regularElements;

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

    public XpBar(float x, float y, IUIElement parent){
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = Game.gameWorldWidth - 48;
    }

    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    @Override
    public void draw() {
        /* FIXME : this is horribly unoptimized
        spr.draw("lvlBar0",x + parent.getX(),y + parent.getY(),5, false);
        for (int i = 8; i < width - 8; i+=8){
            if ((float) i/width < xpPercentage)
                spr.draw("lvlBar3",x + i + parent.getX(), y + parent.getY(), 5, false);
            spr.draw("lvlBar1",x + i + parent.getX(),y + parent.getY(),5, false);
        }
        spr.draw("lvlBar2",x + width + parent.getX(),y + parent.getY(),5, false);


         */
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
