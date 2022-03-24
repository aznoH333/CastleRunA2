package com.mygdx.game.ui.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.UIType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

public class Button implements IUIElement, IUIUpdatable {

    private final float x;
    private final float y;
    private final ILambdaFunction function;
    private final float width;
    private final float height;
    private final boolean actionButton;
    private final String sprite;
    private final String spritePressed;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    private boolean pressed = false;
    private final static float pressedOffset = 20;
    private final IUIElement parent;

    public Button(float x, float y, ButtonType type, IUIElement parent, ILambdaFunction function){
        switch (type){
            case Small:
            case SmallAction:
            default:
                sprite = "button0";
                spritePressed = "button1";
                height = 120;
                width = 336;
                break;
            case Large:
                sprite = "button_large0";
                spritePressed = "button_large1";
                height = 120;
                width = 668;
                break;
            case LargeItemSelect:
                sprite = "item_card0";
                spritePressed = "item_card1";
                height = 256;
                width = 216;
                break;
        }

        actionButton = type == ButtonType.SmallAction;

        this.x = x;
        this.y = y;
        this.function = function;
        this.parent = parent;

    }

    @Override
    public void draw() {
        if (pressed){
            spr.draw(spritePressed,x+parent.getX(),y+parent.getY(),5);
        }
        else{
            spr.draw(sprite,x+parent.getX(),y+parent.getY(),5);
        }
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
    public UIType[] getTypes() {
        return new UIType[]{UIType.Updatable};
    }

    @Override
    public void update() {
        // this is temp (made to work with lower than base resolution)
        // TODO: move calculation to input manager (this calculates input for every button)
        int mx = Gdx.input.getX()*2;
        int my = (640 - Gdx.input.getY())*2;
        //int mx = (int) (Gdx.input.getX() / 1.5);
        //int my = (int) ((Game.androidHeight - Gdx.input.getY()) / 1.5);

        boolean lFrameState = pressed;
        // temp
        if(mx > x+ parent.getX() && mx < x+ parent.getX() + width && my > y+ parent.getY() && my < y+ parent.getY() + height
                && (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isTouched())){
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
