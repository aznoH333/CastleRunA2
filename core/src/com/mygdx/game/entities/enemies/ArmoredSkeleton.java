package com.mygdx.game.entities.enemies;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Entity;

import java.util.Random;

public class ArmoredSkeleton extends Skeleton{
    private boolean hasBeenHit = false;
    public ArmoredSkeleton(float x, float y) {
        super(x, y, 64, 64, 2);
    }

    @Override
    public void draw(DrawingManager spr) {
        if (animationTimer > 30)
            spr.drawGame("armoredSkeleton3",x,y,2);
        else{
            spr.drawGame("armoredSkeleton" + danceAnimation,x,y,2);

        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new ArmoredSkeleton(x, y + 64);
    }


    @Override
    protected void onTakeDamage() {
        Random r = Game.getGeneralRandom();
        ParticleManager part = ParticleManager.getINSTANCE();
        SoundManager.getINSTANCE().playSound("enemyDeath1");
        DrawingManager.getINSTANCE().addScreenShake(5);
        // spawn 3 - 6 gore particles
        for (int i = 0; i < r.nextInt(3) + 3; i++)
            part.addParticle("boneGore" + r.nextInt(3),x+(xSize/2),y+(ySize/2),r.nextInt(6)-3,r.nextInt(20)-5,0.7f,r.nextInt(20) + 20);
    }

    @Override
    public int getXpOnKill() {
        return 60;
    }
}
