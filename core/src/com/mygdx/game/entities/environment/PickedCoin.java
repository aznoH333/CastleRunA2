package com.mygdx.game.entities.environment;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.sprites.SpriteManager;

import java.util.Random;

public class PickedCoin extends Entity {
    private final static int destinationX = 64;
    private final static int destinationY = 64;
    private final float startX;
    private final float startY;
    private byte animation = 0;
    private final byte animationOffset;
    private static final int animationLength = 4;
    private static final float speed = 0.5f;


    public PickedCoin(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.Environment);
        shifts = false;
        x = Math.round(x);
        y = Math.round(y);
        startX = x;
        startY = y;
        animationOffset = (byte) (Math.random() * animationLength);
    }


    @Override
    public void update(LevelManager lvl, Random r) {
        // suicide
        if (Math.abs(x-destinationX) < 1 && Math.abs(y-destinationY) < 1) destroy();

        float xM = (destinationX / startX) * (x - destinationX) * -speed;
        float yM = (destinationY / startY) * (y - destinationY) * -speed;

        if (Math.abs(xM) < 1) xM = Math.signum(xM);
        if (Math.abs(yM) < 1) yM = Math.signum(yM);

        x += xM;
        y += yM;
    }

    @Override
    public void draw(SpriteManager spr) {
        switch (animation){
            case 0:
                spr.drawGame("pickup1",x,y,1);
                break;
            case 1:
            case 3:
                spr.drawGame("pickup2",x,y,1);
                break;
            case 2:
                spr.drawGame("pickup3",x,y,1);
                break;
        }

        animation = (byte) ((animationOffset + (Game.Time()>>2))%animationLength);
    }

    @Override
    public void onCollide(Entity other) {
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new PickedCoin(x,y,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {
        PlayerStats.getINSTANCE().addCoins(1);
        SoundManager.getINSTANCE().playSound("coin");
    }
}
