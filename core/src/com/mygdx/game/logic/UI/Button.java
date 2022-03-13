package com.mygdx.game.logic.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.logic.drawing.DrawingManager;

public class Button {

    private final String sprite;
    private final String spritePressed;
    private final String icon;
    private final int x;
    private final int y;
    private boolean pressed = false;
    private final DrawingManager spr = DrawingManager.getINSTANCE();
    private final ILambdaFunction btnHold;
    private final int xIconOffset;
    private final int yIconOffset;
    private final int width;
    private final int height;
    private final boolean actionButton;
    private final int textX;
    private final int textY;
    private final String text;


    public Button(ButtonType type,
                  String icon,
                  int x, int y,
                  ILambdaFunction btnHold,
                  String text){
        switch (type){
            case Small:
            case SmallAction:
            default:
                sprite = "button0";
                spritePressed = "button1";
                height = 120;
                width = 336;
                xIconOffset = 136;
                yIconOffset = 38;
                textX = 136;
                textY = 38;
                break;
            case Large:
                sprite = "button_large0";
                spritePressed = "button_large1";
                height = 120;
                width = 668;
                xIconOffset = 302;
                yIconOffset = 38;
                textX = 302;
                textY = 38;
                break;
            case LargeItemSelect:
                sprite = "button_large0";
                spritePressed = "button_large1";
                height = 120;
                width = 668;
                xIconOffset = 32;
                yIconOffset = 38;
                textX = 128;
                textY = 38;
                break;
        }

        actionButton = type == ButtonType.SmallAction;

        this.icon = icon;
        this.x = x;
        this.y = y;
        this.btnHold = btnHold;
        this.text = text;
    }

    public void draw(){
        if (pressed){
            spr.draw(spritePressed,x,y,5);
            spr.draw(icon,x + xIconOffset,y + yIconOffset - 10,6);
            if (text != null) spr.drawText(text, textX + x, textY + y - 10);
        }
        else{
            spr.draw(sprite,x,y,5);
            spr.draw(icon,x + xIconOffset,y + yIconOffset,6);
            if (text != null) spr.drawText(text, textX + x, textY + y);
        }
    }

    private boolean lFrameState = false;
    public void manageInput(){
        // this is temp (made to work with lower than base resolution)
        int mx = Gdx.input.getX()*2;
        int my = (640 - Gdx.input.getY())*2;
        //int mx = (int) (Gdx.input.getX() / 1.5);
        //int my = (int) ((Game.androidHeight - Gdx.input.getY()) / 1.5);

        lFrameState = pressed;
        // temp
        if(mx > x && mx < x + width && my > y && my < y + height
        && (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isTouched())){
            pressed = true;
            if (actionButton)
                btnHold.function();
        }else {
            pressed = false;
        }
        if (lFrameState && !pressed && !actionButton)
            btnHold.function();


    }

}
