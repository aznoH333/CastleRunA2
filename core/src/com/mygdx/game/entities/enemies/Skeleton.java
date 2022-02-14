package com.mygdx.game.entities.enemies;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.sprites.SpriteManager;

import java.util.Random;

public class Skeleton extends Entity {

    private int throwTimer = throwTimerMax;
    private final static int throwTimerMax = 100;
    private final static int timerVariance = 40;
    private final static Random r = new Random();

    public Skeleton(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.Enemies);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (throwTimer > 0){
            throwTimer--;
        }else{
            EntityManager.getINSTANCE().spawnEntity("bone",x,y);
            throwTimer = r.nextInt(timerVariance) + throwTimerMax;
        }


    }

    @Override
    public void draw(SpriteManager spr) {
        spr.drawGame("skeleton0",x,y,2);
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Skeleton(x,y+64,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {
        // TODO: more enemy sounds
        Random r = new Random();
        ParticleManager part = ParticleManager.getINSTANCE();
        SoundManager.getINSTANCE().playSound("enemyDeath1");
        // spawn 10 - 20 gore particles
        for (int i = 0; i < r.nextInt(10) + 10; i++)
            part.addParticle("boneGore" + r.nextInt(3),x+(xSize/2),y+(ySize/2),r.nextInt(6)-3,r.nextInt(20)-5,0.7f,r.nextInt(20) + 20);
    }
}
