package com.mygdx.game.ui.elements.parents;

import com.mygdx.game.Game;
import com.mygdx.game.Config;
import com.mygdx.game.data.enums.UIActionStatus;
import com.mygdx.game.data.enums.UIType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIParentElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class TopHud implements IUIElement, IUIParentElement, IUIUpdatable {


    private final float closedPosition = 1280;
    private final float openPosition = 1184;
    private float y = closedPosition;
    private final static float animationSpeed = Config.getAnimationSpeed();
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private UIActionStatus targetStatus = UIActionStatus.Closed;

    @Override
    public void draw() {
        for (int i = 0; i < Game.gameWorldWidth; i += 720)
            spr.draw("hudTop0",i, y, 5, false);
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public UIType[] getTypes() {
        return new UIType[]{UIType.Parent,UIType.Updatable};
    }

    @Override
    public void uiOpen() {
        targetStatus = UIActionStatus.Open;
    }

    @Override
    public void uiClose() {
        targetStatus = UIActionStatus.Closed;
    }

    @Override
    public UIActionStatus getStatus() {
        if (y == closedPosition)        return UIActionStatus.Closed;
        else if (y == openPosition)     return UIActionStatus.Open;
        else                            return UIActionStatus.Transitioning;
    }

    @Override
    public void update() {
        if (getStatus() != targetStatus){
            // open ui
            if (targetStatus == UIActionStatus.Open){
                y -= (float) Math.ceil(Math.abs(y-openPosition)/ animationSpeed);
            }
            // close ui
            else if (targetStatus == UIActionStatus.Closed){
                y += (float) Math.max(Math.ceil(Math.abs(y-openPosition)/ animationSpeed),1);
                if (y > closedPosition) y = closedPosition;
            }
        }
    }
}
