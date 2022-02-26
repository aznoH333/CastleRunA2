package com.mygdx.game.entities.enemies.bosses;

import com.mygdx.game.Game;
import com.mygdx.game.entities.enemies.Slime;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.sprites.SpriteManager;

import java.util.Random;

public class SlimeBoss extends Slime {

    protected final static int animationSpeed = 64;
    protected final static float gravity = 0.5f;
    protected final static float hopStrength = 8.5f;
    protected final static int jumpTime = 120;
    protected final static float moveSpeed = 4f;
    private final static int slimeTimerMax = 240;
    private int slimeTimer = slimeTimerMax;
    private final int hpMax;
    private float targetX;

    public SlimeBoss(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
        hpMax = hp;
        targetX = x;
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (landed) jumpTimer++;
        float lvlY = lvl.getOnPos(x + (lvl.getTileScale() - 1)).getY() + lvl.getTileScale();

        //land
        landed = y <= lvlY - yM && yM <= 0;
        if (landed){
            yM = 0;
            y = lvlY;
            slimeTimer--;
        }else{
            yM -= gravity;
        }
        //hop
        if (jumpTimer > jumpTime){
            jumpTimer = 0;
            yM = hopStrength;
            if (direction)  moveTo = targetX;
            else            moveTo = targetX - (lvl.getTileScale()<<1);
            direction = !direction;
            SoundManager.getINSTANCE().playSound("slimeJump");
        }


        //update positions
        if (moveTo > x) x += moveSpeed;
        if (moveTo < x) x -= moveSpeed;
        targetX = (float) Math.ceil(((float)hp/(float)hpMax) * (LevelManager.mapWidth - 4)) * LevelManager.tileScale;
        y += yM;

        // spawn slimes
        if (slimeTimer < 1 && landed){
            slimeTimer = slimeTimerMax;
            e.spawnEntity("red slime", x,y);
        }
    }

    @Override
    public void draw(SpriteManager spr) {
        if (landed)
            if (jumpTimer%animationSpeed > animationSpeed/2){
                spr.drawGame("player0",x,y,1);
                spr.drawGame("player0",x,y+64,1);
                spr.drawGame("player0",x+64,y,1);
                spr.drawGame("player0",x+64,y+64,1);
            } else{
                spr.drawGame("player1",x,y,1);
                spr.drawGame("player1",x,y+64,1);
                spr.drawGame("player1",x+64,y,1);
                spr.drawGame("player1",x+64,y+64,1);
            }

        else{
            spr.drawGame("player2",x,y,1);
            spr.drawGame("player2",x,y+64,1);
            spr.drawGame("player2",x+64,y,1);
            spr.drawGame("player2",x+64,y+64,1);
        }
        // TODO : boss sprites
    }

    @Override
    public void onCollide(Entity other) {
        // TODO : this
        // position on screen related to boss hp
        // hp-- = x--
        // spawns red slimes (or some slime minion?)
    }

    @Override
    public Entity getCopy(float x, float y) {

        return new SlimeBoss(x,y+LevelManager.tileScale,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {
        Game.exitLevel();
        // TODO : some sort of exit level after few seconds function
    }
}
