package com.mygdx.game.ui.elements.parents;

import com.mygdx.game.Config;
import com.mygdx.game.data.enums.UIActionStatus;
import com.mygdx.game.data.enums.UIType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIParentElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class UIBox implements IUIParentElement, IUIUpdatable, IUIElement {
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private final float closedPosition = 1280;
    private final float openPosition;
    private float y = closedPosition;
    private final float x = 64;
    private UIActionStatus targetStatus = UIActionStatus.Closed;
    private final static float animationSpeed = Config.getAnimationSpeed();

    public UIBox(float openPosition){
        this.openPosition = openPosition;
    }

    @Override
    public void draw() {
        // TODO : sprite
        spr.draw("player0" ,x, y, 4);
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
