package com.mygdx.game.ui.elements.regularElements;

import com.mygdx.game.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.logic.drawing.ColorType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class Button implements IUIElement, IUIUpdatable {

    private final float x;
    private final float y;
    private final ILambdaFunction function;
    private final float width;
    private final float height;
    private final boolean actionButton;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private boolean pressed = false;
    private final static float pressedOffset = 20;
    private final float buttonMiddleSegmentWidth;
    private final IUIElement parent;
    private final static InputManager input = InputManager.getINSTANCE();

    public Button(float x, float y, ButtonType type, IUIElement parent, ILambdaFunction function){

        this.width = type.width;
        this.height = type.height;
        actionButton = type == ButtonType.SmallAction;
        this.buttonMiddleSegmentWidth = ((width - 32) / 16) + 0.5f;

        this.x = x;
        this.y = y;
        this.function = function;
        this.parent = parent;
    }

    @Override
    public void draw() {
        spr.draw("button" + ((pressed) ? 3 : 0),x+parent.getX(),y+parent.getY(),5, false);
        spr.draw("button" + ((pressed) ? 4 : 1), x+parent.getX() + 16,y +parent.getY(), 5, false, buttonMiddleSegmentWidth, ColorType.Normal, true);
        spr.draw("button" + ((pressed) ? 5 : 2),x+parent.getX() + width - 12,y+parent.getY(),5, false);

    }

    @Override
    public float getX() {
        return x+ parent.getX();
    }

    @Override
    public float getY() {
        if (pressed)    return y - pressedOffset + parent.getY();
        else            return y + parent.getY();
    }

    @Override
    public void update() {
        boolean lFrameState = pressed;
        // FIXME : this realy sucks
        if(input.getMouseX() > (x+ parent.getX())/4*spr.getPixelScale() &&
                input.getMouseX() < (x+ parent.getX() + width)/4* spr.getPixelScale() &&
                input.getMouseY() > (y + parent.getY()) / 4 * spr.getPixelScale() &&
                input.getMouseY() < (y+ parent.getY() + height)/4* spr.getPixelScale() &&
                (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isTouched())){
            pressed = true;
            if (actionButton)
                function.function();
        }else {
            pressed = false;
        }
        if (lFrameState && !pressed && !actionButton)
            function.function();
    }
}
