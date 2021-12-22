package com.mygdx.game.entities.environment;

import com.mygdx.game.Game;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.player.PlayerStats;

public class Coin extends Pickup{
    private byte animation = 0;
    private final byte animationOffset;
    private static final int animationLength = 4;
    public Coin(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
        animationOffset = (byte) (Math.random() * animationLength);
    }

    @Override
    public void draw(SpriteManager spr) {
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
    public Entity getCopy(float x, float y) {
        return new Coin(x,y,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {
        PlayerStats.getINSTANCE().addCoins(1);
    }
}
