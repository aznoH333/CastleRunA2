package com.mygdx.game.logic.UI;

import com.mygdx.game.data.IInputFunction;
import com.mygdx.game.logic.sprites.SpriteManager;

public class Button {

    private final String sprite;
    private final String spritePressed;
    private final String icon;
    private final String iconPressed;
    private final int x;
    private final int y;
    private boolean pressed = false;
    private final SpriteManager spr = SpriteManager.getINSTANCE();
    private final IInputFunction btnHold;
    private final IInputFunction btnRelease;


    public Button(String sprite, String spritePressed, String icon, String iconPressed, int x, int y, IInputFunction btnHold, IInputFunction btnRelease){
        this.sprite = sprite;
        this.spritePressed = spritePressed;
        this.icon = icon;
        this.iconPressed = iconPressed;
        this.x = x;
        this.y = y;
        this.btnHold = btnHold;
        this.btnRelease = btnRelease;
    }

    public void draw(){
        if (pressed){
            spr.drawAbsolute(spritePressed,x,y,5);
            spr.drawAbsolute(iconPressed,x,y,6);
        }
        else{
            spr.drawAbsolute(sprite,x,y,5);
            spr.drawAbsolute(icon,x,y,6);
        }
    }

    // TODO: this
}
