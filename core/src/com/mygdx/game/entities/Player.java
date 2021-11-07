package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.data.Entity;
import com.mygdx.game.managers.Level;
import com.mygdx.game.managers.SpriteManager;

import java.util.Random;

public class Player extends Entity {
    private float lvlY;
    private float yM = 0;
    private final float scrollBorder = 256;

    //finals
    private final float gravity = 1f;
    private final float hopStrength = 5f;
    private final float jumpStrength = 8f;
    private final float moveSpeed = 8f;
    private boolean landed = false;

    public Player(float x, float y, float sizeX, float sizeY){
        super(x, y, sizeX, sizeY, 3);
    }
    @Override
    public void update(Level lvl, Random r) {
        manageInput();
        lvlY = lvl.getOnPos(x + (lvl.getTileScale() -1)).getY() + lvl.getTileScale();


        //land
        landed = y <= lvlY - yM && yM <= 0;
        if (landed){
            yM = 0;
            y = lvlY;
        }else{
            yM -= gravity;
        }

        // execute movement
        // jump left
        if (!right && y == lvlY && jumpRight > 0){
            // hop
            if (jumpRight < holdSensitivity){
                yM = hopStrength;
                moveTo = x + lvl.getTileScale();
            }else {
                yM = jumpStrength;
                moveTo = x + lvl.getTileScale() * 2;
            }
        }
        // jump right
        if (!left && y == lvlY && jumpLeft > 0){
            // hop
            if (jumpLeft < holdSensitivity){
                yM = hopStrength;
                moveTo = x - lvl.getTileScale();
            }else {
                yM = jumpStrength;
                moveTo = x - lvl.getTileScale() * 2;
            }
            //prevent player moving out off bounds
            if (moveTo < 0) moveTo = 0;
        }

        // TODO : rework scrolling
        // scroll camera
        if (x > scrollBorder && x % lvl.getTileScale() == 0 && !lvl.isScrolling() && landed){
            if ((int) Math.ceil(Math.abs(scrollBorder - x)/lvl.getTileScale()) > 1) System.out.println("gothere");
            lvl.advanceTiles((int) Math.ceil(Math.abs(scrollBorder - x)/lvl.getTileScale()));
        }

        if (moveTo > x) x += moveSpeed;
        if (moveTo < x) x -= moveSpeed;
        y += yM;
        resetInput();
    }
    @Override
    public void draw(SpriteManager spr) {
        // yabba dabba dooo
        if ((jumpLeft > holdSensitivity || jumpRight > holdSensitivity) && y == lvlY)
            spr.draw("player2",x,y);
        else if(y != lvlY)
            spr.draw("player3",x,y);
        else
            spr.draw("player0",x,y);

    }

    //is button pressed
    private boolean left = false;
    private boolean right = false;
    private boolean aLeft = false;
    private boolean aRight = false;


    //control vals
    private int jumpLeft = 0;
    private int jumpRight = 0;
    private int holdSensitivity = 16;
    private void manageInput(){
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
    }

    private void resetInput(){
        if (!left)  jumpLeft = 0;
        if (!right) jumpRight = 0;
    }
}
