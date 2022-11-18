package com.mygdx.game.entities.player.Projectiles.dagger;

import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class ChargedDagger extends Dagger {
    private float yM = 2;
    private final FollowerObject[] followers = {
            new FollowerObject(0,1, 0.6f),
            new FollowerObject(0,2, 0.5f),
            new FollowerObject(0,3, 0.4f),
            new FollowerObject(0,4, 0.3f),
            new FollowerObject(0,5, 0.2f)
    };

    public ChargedDagger(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        float speed = 18f;
        float fallSpeed = 0.5f;

        x += speed;
        y += yM;

        yM -= fallSpeed;
        //delete if out of bounds
        if (x >= (lvl.getMapWidth()-1) * lvl.getTileScale()) destroy();
        else{
            float lvlY = lvl.getOnPos(x + (lvl.getTileScale() - 1)).getY() + lvl.getTileScale();
            //detect ground collision
            if (lvlY - lvl.getTileScale() > y - ySize) destroy();
        }
    }

    @Override
    public void draw(DrawingManager spr) {
        super.draw(spr);
        for (FollowerObject f: followers) {
            f.addCoordinate(x, y, "dagger1");
            f.draw();
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new ChargedDagger(x,y,xSize,ySize,hp);
    }

}
