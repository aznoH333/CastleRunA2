package com.mygdx.game.entities.enemies;

import com.mygdx.game.Game;
import com.mygdx.game.entities.environment.Giblet;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;

import java.util.Random;

public class ArmoredSkeleton extends Skeleton{
    public ArmoredSkeleton(float x, float y) {
        super(x, y, 64, 64, 2);
    }

    @Override
    public void draw(DrawingManager spr) {
        if (animationTimer > 30)
            spr.draw("armoredSkeleton3",x,y,2);
        else{
            spr.draw("armoredSkeleton" + danceAnimation,x,y,2);
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new ArmoredSkeleton(x, y + 64);
    }


    @Override
    protected void onTakeDamage() {
        Random r = Game.getGeneralRandom();
        SoundManager.getINSTANCE().playSound("enemyDeath1");
        DrawingManager.getINSTANCE().addScreenShake(3);
        // spawn 3 - 6 gore particles
        for (int i = 0; i < r.nextInt(3) + 3; i++)
            e.addEntity(new Giblet(x, y,r.nextInt(20)-10,r.nextInt(20)-10,"gore" + (r.nextInt(4) + 7)));
    }

    @Override
    public int getXpOnKill() {
        return 60;
    }
}
