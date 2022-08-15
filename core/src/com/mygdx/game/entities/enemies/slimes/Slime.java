package com.mygdx.game.entities.enemies.slimes;

import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class Slime extends Enemy {
    // constants
    protected final static int animationSpeed = 32;
    protected final static float gravity = 0.5f;
    protected final static float hopStrength = 3.5f;
    protected final static int jumpTime = 60;
    protected final static float moveSpeed = 4f;

    // vars
    protected int jumpTimer = 0;
    protected boolean landed = false;
    protected float yM = 0;
    protected boolean direction = false;

    public Slime(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.Enemies);
        tags = new EntityTags[]{EntityTags.Grounded};
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (landed) jumpTimer++;
        float lvlY = lvl.getLevelY(this);

        //land
        landed = y <= lvlY - yM && yM <= 0;
        if (landed){
            x = Math.round(x);
            yM = 0;
            y = lvlY;
        }else{
            yM -= gravity;
        }
        //hop
        if (jumpTimer > jumpTime){
            jumpTimer = 0;
            yM = hopStrength;
            if (direction)  moveTo = x + lvl.getTileScale();
            else            moveTo = x - lvl.getTileScale();
            direction = !direction;
            SoundManager.getINSTANCE().playSound("slimeJump");
        }


        //update positions
        if (moveTo > x) x += moveSpeed;
        if (moveTo < x) x -= moveSpeed;
        y += yM;
    }

    @Override
    public void draw(DrawingManager spr) {
        if (landed)
            if (jumpTimer%animationSpeed > animationSpeed/2)
                spr.drawGame("slime0",x,y,1);
            else
                spr.drawGame("slime1",x,y,1);
        else
            spr.drawGame("slime2",x,y,1);
    }

    @Override
    public void onCollide(Entity other) {}

    @Override
    public Entity getCopy(float x, float y) {
        return new Slime(x,y + LevelManager.tileScale, xSize, ySize, hp);
    }

    @Override
    public void onDestroy() {
        // spawn particles
        ParticleManager part = ParticleManager.getINSTANCE();
        SoundManager.getINSTANCE().playSound("enemyDeath1");
        Random r = new Random();
        part.addParticle("greenSlimeDeath",x,y,0,0,0);
        DrawingManager.getINSTANCE().addScreenShake(5);
        // spawn 5 - 10 gore particles
        for (int i = 0; i < r.nextInt(5) + 5; i++)
            part.addParticle("greenGore" + r.nextInt(3),x,y,r.nextInt(10)-5,r.nextInt(10)-5,0.5f,r.nextInt(10) + 10);


    }
}
