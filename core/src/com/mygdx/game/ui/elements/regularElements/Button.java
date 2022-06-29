package com.mygdx.game.ui.elements.regularElements;

import com.mygdx.game.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.UIType;
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
    private final IUIElement parent;
    private final static InputManager input = InputManager.getINSTANCE();

    public Button(float x, float y, ButtonType type, IUIElement parent, ILambdaFunction function){
        switch (type){
            case Small:
            case SmallAction:
            default:
                height = 120;
                //width = 336;
                width = (Game.gameWorldWidth - 32) / 2 - 16; // TODO : smart scaling
                break;
            case Large:
                height = 120;
                //width = 668;
                width = Game.gameWorldWidth - 4;
                break;
            case LargeItemSelect:
                // TODO : large buttons
                height = 200;
                width = 668;
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
            spr.draw("button3",x+parent.getX(),y+parent.getY(),5, false);
            for (int i = 12; i < width - 12; i+= 12 ){
                spr.draw("button4", x+parent.getX()+i,y +parent.getY(), 5, false);
            }
            spr.draw("button5",x+parent.getX() + width - 12,y+parent.getY(),5, false);


        }
        else{

            spr.draw("button0",x+parent.getX(),y+parent.getY(),5, false);
            for (int i = 12; i < width - 12; i+= 12 ){
                spr.draw("button1", x+parent.getX()+i,y +parent.getY(), 5, false);
            }
            spr.draw("button2",x+parent.getX() + width - 12,y+parent.getY(),5, false);

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



        boolean lFrameState = pressed;
        // FIXME : this realy sucks
        if(input.getMouseX() > (x+ parent.getX())/4*spr.getPixelScale() && input.getMouseX() < (x+ parent.getX() + width)/4* spr.getPixelScale() && input.getMouseY() > (y + parent.getY()) / 4 * spr.getPixelScale() && input.getMouseY() < (y+ parent.getY() + height)/4* spr.getPixelScale()
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
