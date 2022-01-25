package com.mygdx.game.logic.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private final int xIconOffset;
    private final int yIconOffset;


    public Button(String sprite,
                  String spritePressed,
                  String icon,
                  String iconPressed,
                  int x, int y,
                  int xIconOffset, int yIconOffset,
                  IInputFunction btnHold){
        this.sprite = sprite;
        this.spritePressed = spritePressed;
        this.icon = icon;
        this.iconPressed = iconPressed;
        this.x = x;
        this.y = y;
        this.btnHold = btnHold;
        this.xIconOffset = xIconOffset;
        this.yIconOffset = yIconOffset;
    }

    public void draw(){
        if (pressed){
            spr.draw(spritePressed,x,y,5);
            spr.draw(iconPressed,x + xIconOffset,y + yIconOffset - 8,6);
        }
        else{
            spr.draw(sprite,x,y,5);
            spr.draw(icon,x + xIconOffset,y + yIconOffset,6);
        }
    }

    public void manageInput(){
        // this is temp (made to work with lower than base resolution)
        int mx = Gdx.input.getX()*2;
        int my = (640 - Gdx.input.getY())*2;
        //int mx = (int) (Gdx.input.getX() / 1.5);
        //int my = (int) ((Game.androidHeight - Gdx.input.getY()) / 1.5);


        // temp
        if(mx > x && mx < x + UIManager.buttonWidth && my > y && my < y + UIManager.buttonHeight
        && (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isTouched())){
            pressed = true;
            btnHold.function();
        }else {
            pressed = false;
        }



    }

}
