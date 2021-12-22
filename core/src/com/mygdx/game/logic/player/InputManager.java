package com.mygdx.game.logic.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.data.enums.Controls;

public class InputManager {
    private static InputManager INSTANCE;
    public static InputManager getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new InputManager();
        return INSTANCE;
    }


    private int holdSensitivity = 16;
    //is button pressed
    private boolean left = false;
    private boolean right = false;
    private boolean aLeft = false;
    private boolean aRight = false;



    //control vals
    private int jumpLeft = 0;
    private int jumpRight = 0;
    private int chargeLeft = 0;
    private int chargeRight = 0;
    // TODO: sensitivity setting in menu
    // TODO: touch controls
    public void manageInput(){
        // left
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            jumpLeft++;
            left = true;
        }else {
            left = false;
        }
        // right
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            jumpRight++;
            right = true;
        }else {
            right = false;
        }
        // left attack
        if (Gdx.input.isKeyPressed(Input.Keys.Q)){
            chargeLeft++;
            aLeft = true;
        }else {
            aLeft = false;
        }
        // right attack
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            chargeRight++;
            aRight = true;
        }else {
            aRight = false;
        }
    }

    public void resetInput(){
        if (!left)  jumpLeft = 0;
        if (!right) jumpRight = 0;
        if (!aLeft) chargeLeft = 0;
        if (!aRight)chargeRight = 0;
    }

    public boolean getButton(Controls control){
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
}
