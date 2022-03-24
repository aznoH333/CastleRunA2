package com.mygdx.game.ui.elements;

import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.ui.interfaces.IUIElement;

public class Button implements IUIElement {

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

    }

    @Override
    public void draw() {
        if (pressed){
            spr.draw(spritePressed,x,y,5);
        }
        else{
            spr.draw(sprite,x,y,5);
        }
    }
}
