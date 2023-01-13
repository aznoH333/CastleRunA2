package com.mygdx.game.entities.enemies.slimes;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.entities.environment.Giblet;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class Slime extends Enemy {
    // constants
    protected final static float gravity = 0.5f;
    protected final static float hopStrength = 4f;
    protected final static float jumpStrength = 8f;
    protected final static int jumpTime = 60;
    protected final static float moveSpeed = 4f;
    protected final static int animationSpeed = 32;

    // vars
    protected int jumpTimer;
    protected boolean landed = false;
    protected float yM = 0;
    protected boolean direction = false;

    public Slime(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.Enemies);
        tags = new EntityTags[]{EntityTags.Grounded};
        jumpTimer = Game.getGeneralRandom().nextInt(32);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (landed) jumpTimer++;
        float lvlY = lvl.getLevelY(this);

        //land
        landed = y <= lvlY - yM && yM <= 0;
        if (landed){
            yM = 0;
            y = lvlY;
        }else{
            yM -= gravity;
        }
        //hop
        if (jumpTimer > jumpTime){
            jumpTimer = 0;

            if (lvl.getOnPos(x + (lvl.getTileScale() -1) - lvl.getTileScale()).getSpecial() == TileCollumSpecial.Gap){
                moveTo = x - (lvl.getTileScale() * 2);
                yM = jumpStrength;
            } else {
                moveTo = x - lvl.getTileScale();
                yM = hopStrength;
            }

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
                spr.draw("slime0",x,y,1);
            else
                spr.draw("slime1",x,y,1);
        else
            spr.draw("slime2",x,y,1);
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
        SoundManager.getINSTANCE().playSound("enemyDeath1");
        Random r = Game.getGeneralRandom();
        ParticleManager.getINSTANCE().addParticle("greenSlimeDeath",x,y,0,0,0);
        DrawingManager.getINSTANCE().addScreenShake(5);
        // spawn 5 - 10 gore particles
        for (int i = 0; i < r.nextInt(3) + 4; i++)
            e.addEntity(new Giblet(x, y,r.nextInt(20)-10,r.nextInt(20)-10,"gore" + r.nextInt(3)));
            //part.addParticle("greenGore" + r.nextInt(3),x,y,r.nextInt(10)-5,r.nextInt(10)-5,0.5f,r.nextInt(10) + 10);


    }

    @Override
    public int getXpOnKill() {
        return 15;
    }
}
