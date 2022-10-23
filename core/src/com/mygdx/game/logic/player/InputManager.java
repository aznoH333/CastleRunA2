package com.mygdx.game.logic.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.logic.drawing.DrawingManager;

public class InputManager {
    private static InputManager INSTANCE;
    public static InputManager getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new InputManager();
        return INSTANCE;
    }


    private final int holdSensitivity = 16;
    private final static DrawingManager spr = DrawingManager.getINSTANCE();
    //is button pressed
    private boolean left = false;
    private boolean right = false;
    private boolean aLeft = false;
    private boolean aRight = false;

    private boolean bLeft = false;
    private boolean bRight = false;
    private boolean bALeft = false;
    private boolean bARight = false;



    //control vals
    private int jumpLeft = 0;
    private int jumpRight = 0;
    private int chargeLeft = 0;
    private int chargeRight = 0;
    // TODO: sensitivity setting in menu

    // touch & mouse controls
    private float mouseX = 0;
    private float mouseY = 0;

    public float getMouseX(){
        return Gdx.input.getX()*2;
    }

    public float getMouseY(){
        return (Gdx.graphics.getHeight() - Gdx.input.getY()) * 2;
    }

    public void manageInput(){
        // TODO : touch input


        // left
        if (Gdx.input.isKeyPressed(Input.Keys.A) || bLeft){
            jumpLeft++;
            left = true;
        }else {
            left = false;
        }
        // right
        if (Gdx.input.isKeyPressed(Input.Keys.S) || bRight){
            jumpRight++;
            right = true;
        }else {
            right = false;
        }
        // left attack
        if (Gdx.input.isKeyPressed(Input.Keys.Q) || bALeft){
            chargeLeft++;
            aLeft = true;
        }else {
            aLeft = false;
        }
        // right attack
        if (Gdx.input.isKeyPressed(Input.Keys.W) || bARight){
            chargeRight++;
            aRight = true;
        }else {
            aRight = false;
        }

        mouseX = Gdx.input.getX()*2;
        mouseY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * 2;
    }

    public void resetInput(){
        if (!left)  jumpLeft = 0;
        if (!right) jumpRight = 0;
        if (!aLeft) chargeLeft = 0;
        if (!aRight)chargeRight = 0;
    }

    public boolean getButton(Controls control){
        // TODO : tohle je mrkda z krtka. přepsat všechno
        switch (control){
            case MoveLeft:
                return left;
            case MoveRight:
                return right;
            case AttackLeft:
                return aLeft;
            case AttackRight:
                return aRight;
            default:
                return false;
        }
    }

    public int getButtonCharge(Controls control){
        // this is pretty sad, but I am tired
        bRight = false;
        bLeft = false;
        bARight = false;
        bALeft = false;

        switch (control){
            case MoveLeft:
                return jumpLeft;
            case MoveRight:
                return jumpRight;
            case AttackLeft:
                return chargeLeft;
            case AttackRight:
                return chargeRight;
            default:
                return 0;
        }
    }

    public int getHoldSensitivity(){
        return holdSensitivity;
    }

    public void buttonHold(Controls button){

        if      (button == Controls.AttackLeft) bALeft = true;
        else if (button == Controls.AttackRight) bARight = true;
        else if (button == Controls.MoveLeft) bLeft = true;
        else if (button == Controls.MoveRight) bRight = true;
    }

    public boolean isInputHeld(){// TODO : touch input
        return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }
}
