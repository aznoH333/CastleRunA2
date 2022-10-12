package com.mygdx.game.ui.elements.regularElements;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.data.enums.UIActionStatus;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIParentElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class TransitionScreen implements IUIParentElement, IUIElement, IUIUpdatable {

    private static final float defaultPosition = 1312;
    private float y;
    private final static float closedPosition = -16;
    private final static float openPosition = -1312;
    private final static float animationSpeed = 60;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private UIActionStatus targetStatus = UIActionStatus.Closed;

    public TransitionScreen(){
        y = closedPosition;
    }

    @Override
    public void draw() {
        for (int i = 0;i < Gdx.graphics.getWidth() * (1280/Gdx.graphics.getHeight()); i += 720 )
            spr.draw("transition0",i,y,6, false);

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
        y = defaultPosition;
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

            y -= 48;
            if (targetStatus == UIActionStatus.Closed && y < closedPosition){
                y = closedPosition;
            }else if (targetStatus == UIActionStatus.Open && y < openPosition){
                y = openPosition;
            }
        }
    }
}
