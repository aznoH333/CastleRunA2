package com.mygdx.game.ui.elements;

import com.mygdx.game.data.enums.UIActionStatus;
import com.mygdx.game.data.enums.UIType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIParentElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class BottomHudGame implements IUIElement, IUIParentElement, IUIUpdatable {

    private float y = closedPosition;
    private final static float closedPosition = -515f;
    private final static float openPosition = -150f;
    private final static float animationSpeed = 20;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private UIActionStatus targetStatus = UIActionStatus.Closed;

    @Override
    public void draw() {
        spr.draw("hudBot0",0,y,4);
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
        return new UIType[]{UIType.Updatable, UIType.Parent};
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
                y += (float) Math.ceil(Math.abs(y-openPosition)/ animationSpeed);
            }
            // close ui
            else if (targetStatus == UIActionStatus.Closed){
                y -= (float) Math.max(Math.ceil(Math.abs(y-openPosition)/ animationSpeed),1);
            }
        }
    }
}
