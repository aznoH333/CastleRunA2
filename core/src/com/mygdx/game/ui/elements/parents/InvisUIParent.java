package com.mygdx.game.ui.elements.parents;

import com.mygdx.game.Config;
import com.mygdx.game.data.enums.UIActionStatus;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIParentElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class InvisUIParent implements IUIParentElement, IUIElement, IUIUpdatable {


    private final float closedPosition;
    private final float openPosition;
    private float y;
    private final static float animationSpeed = Config.getAnimationSpeed();
    private UIActionStatus targetStatus = UIActionStatus.Closed;

    public InvisUIParent(){
        closedPosition = 1280;
        openPosition = 0;
        y = closedPosition;
    }

    public InvisUIParent(float openPosition, float closedPosition){
        this.closedPosition = closedPosition;
        this.openPosition = openPosition;
        y = closedPosition;
    }

    @Override
    public void draw() {

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
                y += (Math.abs(y-openPosition) / animationSpeed) * -Math.signum(y-openPosition);
                if (Math.abs(y-openPosition) < 3) y = openPosition;

            }
            // close ui
            else if (targetStatus == UIActionStatus.Closed){
                y += (Math.abs(y-closedPosition) / animationSpeed) * -Math.signum(y-closedPosition);
                if (Math.abs(y-closedPosition) < 3) y = closedPosition;
            }
        }
    }
}
