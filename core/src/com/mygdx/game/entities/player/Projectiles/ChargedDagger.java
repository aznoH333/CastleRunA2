package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.logic.drawing.ColorType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.entities.ParticleManager;

import java.util.Random;

public class ChargedDagger extends Dagger {
    private float yM = 2;
    private final FollowerObject follower = new FollowerObject(0,1, ColorType.Opacity50);
    private final FollowerObject follower2 = new FollowerObject(0,2, ColorType.Opacity25);


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
        follower.addCoordinate(x, y, "dagger1");
        follower2.addCoordinate(x, y, "dagger1");
        follower.draw();
        follower2.draw();
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new ChargedDagger(x,y,xSize,ySize,hp);
    }

}
