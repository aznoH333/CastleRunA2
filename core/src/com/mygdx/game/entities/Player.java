package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.data.Entity;
import com.mygdx.game.data.Team;
import com.mygdx.game.managers.EntityFactory;
import com.mygdx.game.managers.LevelManager;
import com.mygdx.game.managers.SpriteManager;

import java.util.Random;

public class Player extends Entity {
    private float lvlY;
    private float yM = 0;
    private boolean landed = false;
    private int actionTimer = 0;


    //finals
    private final float gravity = 1f;
    private final float hopStrength = 5f;
    private final float jumpStrength = 8f;
    private final float moveSpeed = 8f;
    private final float scrollBorder = 256;
    private final EntityFactory entityFactory;
    private final int actionTimerFull = 16;

    public Player(float x, float y, float sizeX, float sizeY){
        super(x, y, sizeX, sizeY, 3, Team.Player);
        entityFactory = EntityFactory.getInstance();
    }
    @Override
    public void update(LevelManager lvl, Random r) {
        manageInput();
        lvlY = lvl.getOnPos(x + (lvl.getTileScale() -1)).getY() + lvl.getTileScale();
        if (actionTimer > 0) actionTimer--;

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

        // attacks
        // temporary
        // TODO: Equip system
        // TODO: sprite offsets || a workaround
        // TODO: air attacks?
        if (!aRight && actionTimer == 0 && chargeRight > 0 && landed){
            if(chargeRight < holdSensitivity){
                e.addEntity(entityFactory.getByName("dagger", x, y));
            }else {
                // TODO: charge attacks
            }
            actionTimer = actionTimerFull;
        }


        // scroll camera
        if (x > scrollBorder){
            lvl.advanceToTile(x - scrollBorder);
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
            if (actionTimer > actionTimerFull/2)
                spr.draw("player4",x,y);
            else if(actionTimer > 0)
                spr.draw("player5",x,y);
            else
                spr.draw("player0",x,y);

    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return null;
    }

    @Override
    public void onDestroy() {

    }

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

    private void resetInput(){
        if (!left)  jumpLeft = 0;
        if (!right) jumpRight = 0;
        if (!aLeft) chargeLeft = 0;
        if (!aRight)chargeRight = 0;
    }
}
