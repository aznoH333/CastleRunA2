package com.mygdx.game.ui.elements.parents;

import com.mygdx.game.Config;
import com.mygdx.game.Game;
import com.mygdx.game.data.enums.UIActionStatus;
import com.mygdx.game.data.enums.UIType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIParentElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class MessageBox implements IUIParentElement, IUIUpdatable, IUIElement {

    private final float closedPosition = 1400;
    private final float openPosition = 1000;
    private float y = closedPosition;
    private final static float animationSpeed = Config.getAnimationSpeed();
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private UIActionStatus targetStatus = UIActionStatus.Closed;
    private long closeTimer = 0;

    @Override
    public void uiOpen() {targetStatus = UIActionStatus.Open;}

    @Override
    public void uiClose() {targetStatus = UIActionStatus.Closed;}

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
        if (Game.Time() == closeTimer) uiClose();
    }

    @Override
    public void draw() {
        spr.drawText(text, (Game.gameWorldWidth/2) - ((float) (text.length() * 25)/2), y, 3);
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
    private String text = "";
    public void setText(String text){
        this.text = text;
        closeTimer = Game.Time() + 90;
    }
}
