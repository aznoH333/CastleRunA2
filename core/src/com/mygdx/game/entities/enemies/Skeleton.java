package com.mygdx.game.entities.enemies;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class Skeleton extends Enemy {

    private int throwTimer = throwTimerMin;
    private int animationTimer = 0;
    private final static int throwTimerMin = 200;
    private final static int timerVariance = 40;
    private final static Random r = new Random();
    private boolean calcGravity = false;
    private static final float gravity = 0.5f;
    private float yM = 0f;
    private int danceAnimation = 0;
    private boolean danceToggle = true;

    public Skeleton(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.Enemies);
        tags = new EntityTags[]{EntityTags.Grounded, EntityTags.Undead};
    }
    @Override
    public void update(LevelManager lvl, Random r) {
        if (throwTimer > 0){
            throwTimer--;
        }else{
            EntityManager.getINSTANCE().spawnEntity("bone",x,y);
            throwTimer = r.nextInt(timerVariance) + throwTimerMin;
            animationTimer = 45;
        }

        if (animationTimer > 0) animationTimer--;
        if (Game.Time()%20 == 0) {
            if (danceToggle){
                danceAnimation++;
                if (danceAnimation == 2) danceToggle = false;
            }else {
                danceAnimation--;
                if (danceAnimation == 0) danceToggle = true;
            }
        }

        if(calcGravity){
            calcGravity = LevelManager.getINSTANCE().getOnPos(x).getSpecial() == TileCollumSpecial.DisappearingPlatform;

            yM -= gravity;
            y += yM;
            final float lvlY = lvl.getLevelY(this);
            if (y <= lvlY - yM && yM <= 0 && lvl.collidesWithLevel(this)) {
                yM = 0;
                y = lvlY;
            }
        }
    }

    @Override
    public void draw(DrawingManager spr) {
        if (animationTimer > 30)
            spr.drawGame("skeleton3",x,y,2);
        else{
            spr.drawGame("skeleton" + danceAnimation,x,y,2);

        }
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
        Random r = Game.getGeneralRandom();
        ParticleManager part = ParticleManager.getINSTANCE();
        SoundManager.getINSTANCE().playSound("enemyDeath1");
        DrawingManager.getINSTANCE().addScreenShake(10);
        // spawn 10 - 20 gore particles
        for (int i = 0; i < r.nextInt(10) + 10; i++)
            part.addParticle("boneGore" + r.nextInt(3),x+(xSize/2),y+(ySize/2),r.nextInt(6)-3,r.nextInt(20)-5,0.7f,r.nextInt(20) + 20);
    }
}
