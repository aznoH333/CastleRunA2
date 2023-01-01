package com.mygdx.game.ui.elements.regularElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class Button implements IUIElement, IUIUpdatable {

    private final float x;
    private final float y;
    private final ILambdaFunction function;
    private final boolean actionButton;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private boolean pressed = false;
    private final static float pressedOffset = 20;
    private final float buttonMiddleSegmentWidth;
    private final IUIElement parent;
    private final static InputManager input = InputManager.getINSTANCE();
    private final ButtonType type;

    public Button(float x, float y, ButtonType type, IUIElement parent, ILambdaFunction function){
        this.type = type;

        actionButton = type == ButtonType.SmallAction;
        this.buttonMiddleSegmentWidth = ((type.width - 32) / 16) + 0.5f;

        this.x = x;
        this.y = y;
        this.function = function;
        this.parent = parent;
    }

    @Override
    public void draw() {
        spr.draw(type.sprite + ((pressed) ? 3 : 0),x+parent.getX(),y+parent.getY(), 5, 1f, 1f, Color.WHITE, false);
        spr.draw(type.sprite + ((pressed) ? 4 : 1), x+parent.getX() + 16,y +parent.getY(), 5, buttonMiddleSegmentWidth, 1f, Color.WHITE, false);
        spr.draw(type.sprite + ((pressed) ? 5 : 2),x+parent.getX() + type.width - 12,y+parent.getY(), 5, 1f, 1f, Color.WHITE, false);


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
                input.getMouseX() < (x+ parent.getX() + type.width)/4* spr.getPixelScale() &&
                input.getMouseY() > (y + parent.getY()) / 4 * spr.getPixelScale() &&
                input.getMouseY() < (y+ parent.getY() + type.height)/4* spr.getPixelScale() &&
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
