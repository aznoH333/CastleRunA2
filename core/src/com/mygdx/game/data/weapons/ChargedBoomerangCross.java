package com.mygdx.game.data.weapons;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.sprites.SpriteManager;

import java.util.Random;

public class ChargedBoomerangCross extends Entity {

    private final float startPosition;
    private final static float returnForce = 0.1f;
    private final static float upwardForce = 0.05f;
    private final static float startForce = 5f;

    private float xM = 0;
    private float yM = 0;



    public ChargedBoomerangCross(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles);
        startPosition = x;
    }

    public ChargedBoomerangCross(float x, float y, float xSize, float ySize, int hp, int param){
        super(x,y,xSize,ySize,hp, Team.PlayerProjectiles);
        startPosition = x;
        switch (param){
            case 1:
                xM = startForce;
                break;
            case 2:
                xM = -startForce;
                break;
        }
    }

    @Override
    public void update(LevelManager lvl, Random r) {

    }

    @Override
    public void draw(SpriteManager spr) {

    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new ChargedBoomerangCross(x,y,xSize,ySize,hp);
    }

    @Override
    public Entity getCopy(float x, float y, int specialParam) {
        return new ChargedBoomerangCross(x,y,xSize,ySize,hp,specialParam);
    }



    @Override
    public void onDestroy() {

    }

}
