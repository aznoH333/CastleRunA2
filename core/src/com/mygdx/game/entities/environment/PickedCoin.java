package com.mygdx.game.entities.environment;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class PickedCoin extends Entity {
    private final static int destinationX = 64;
    private final static int destinationY = 64;
    private final float startX;
    private final float startY;
    private final static float maxSpeed = 32f;
    private byte animation = 0;
    private final byte animationOffset;
    private static final int animationLength = 4;
    private static final float speed = 0.5f;
    private static final ParticleManager part = ParticleManager.getINSTANCE();


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

        if (Math.abs(xM) > maxSpeed) xM = maxSpeed * Math.signum(xM);
        if (Math.abs(yM) > maxSpeed) yM = maxSpeed * Math.signum(yM);

        if (Math.abs(xM) < 1) xM = Math.signum(xM);
        if (Math.abs(yM) < 1) yM = Math.signum(yM);

        x += xM;
        y += yM;
        if (Game.Time() % 8 == 0 && r.nextInt(10) == 1)
            part.addParticle("coinSparkle",x +  r.nextInt((int)xSize >> 1) - 16,y + r.nextInt((int)ySize >> 1) - 16,0,0,0);

    }

    @Override
    public void draw(DrawingManager spr) {
        switch (animation){
            case 0:
                spr.draw("pickup1",x,y,1);
                break;
            case 1:
            case 3:
                spr.draw("pickup2",x,y,1);
                break;
            case 2:
                spr.draw("pickup3",x,y,1);
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
        Random r = new Random();
        for (int i = 0; i  < 3; i++)
            part.addParticle("coinSparkle",x +  r.nextInt((int)xSize) - 16,y + r.nextInt((int)ySize) - 16,0,0,0);

    }
}
