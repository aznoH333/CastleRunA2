package com.mygdx.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.EntityFactory;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.SpriteManager;

import java.util.Random;

public class Player extends Entity {
    private float lvlY;
    private float yM = 0;
    private boolean landed = false;
    private int actionTimer = 0;
    private int particleTimer = 0;
    private int iFrame = 0;

    //finals
    private final float gravity = 1f;
    private final float hopStrength = 5f;
    private final float jumpStrength = 8f;
    private final float moveSpeed = 8f;
    private final float scrollBorder = 256;
    private final EntityFactory entityFactory;
    private final int actionTimerFull = 16;
    private final PlayerStats inv;
    private final int particleTimerFull = 8;
    // TODO: rework iframes to give invincibility until fall
    private final int iFrameMax = 60;

    public Player(float x, float y, float sizeX, float sizeY){
        super(x, y, sizeX, sizeY, 3, Team.Player);
        entityFactory = EntityFactory.getInstance();
        inv = PlayerStats.getINSTANCE();
    }
    @Override
    public void update(LevelManager lvl, Random r) {
        PlayerStats ps = PlayerStats.getINSTANCE();
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
        // TODO: sprite offsets || a workaround
        // TODO: air attacks?
        if (!aRight && actionTimer == 0 && chargeRight > 0 && landed){
            if(chargeRight < holdSensitivity){
                inv.getRightWeapon().attack(x,y);

            }else {
                inv.getRightWeapon().chargedAttack(x,y);
            }
            actionTimer = actionTimerFull;
        }

        if (!aLeft && actionTimer == 0 && chargeLeft > 0 && landed){
            if(chargeLeft < holdSensitivity){
                inv.getLeftWeapon().attack(x,y);
            }else {
                inv.getLeftWeapon().chargedAttack(x,y);
            }
            actionTimer = actionTimerFull;
        }

        // spawn particle
        if (chargeLeft > holdSensitivity || chargeRight > holdSensitivity){
            if (particleTimer <= 0){
                particleTimer = particleTimerFull;
                ParticleManager.getINSTANCE().addParticle(
                        "sparkle",
                        (int) (Math.random() * (lvl.getTileScale()-16)) + x,
                        (int) (Math.random() * (lvl.getTileScale()-16)) + y - 16,
                        0,
                        -0.5f,
                        0,
                        (int) (Math.random() * 20 + 10));
            }else particleTimer--;
        }
        if (iFrame > 0) iFrame--;


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
        // TODO: re do player sprites
        // player rendering
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
        if(other.getTeam() == Team.Enemies && iFrame == 0){
            hp--;
            PlayerStats.getINSTANCE().setHp(hp);
            iFrame = iFrameMax;

            // TODO : knock back on hit Castlevania style
        }
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
    // TODO: sensitivity setting in menu
    // TODO: touch controls
    private final int holdSensitivity = 16;
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
