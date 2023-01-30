package com.mygdx.game.entities.enemies.frogs;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.entities.environment.Giblet;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class Frog extends Enemy {
    // constants
    protected final static float gravity = 0.5f;
    protected final static float jumpStrength = 6.8f;
    protected final static int jumpTime = 60;
    protected final static float moveSpeed = 4f;
    private final String baseSprite;
    // vars
    protected int jumpTimer;
    protected boolean landed = false;
    protected float yM = 0;

    public Frog(float x, float y, String baseSprite, int hp) {
        super(x, y, 64, 64, hp, Team.Enemies);
        tags = new EntityTags[]{EntityTags.Grounded};
        jumpTimer = Game.getGeneralRandom().nextInt(32);
        this.baseSprite = baseSprite;
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

            moveTo = x - (lvl.getTileScale() * 2);
            yM = jumpStrength;

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
            if (jumpTimer < 45)
                spr.draw(baseSprite + "0",x,y,1);
            else
                spr.draw(baseSprite + "1",x,y,1);
        else
            spr.draw(baseSprite + "2",x,y,1);
    }

    @Override
    public void onCollide(Entity other) {}

    @Override
    public Entity getCopy(float x, float y) {
        return new Frog(x,y + LevelManager.tileScale, baseSprite, hp);
    }

    @Override
    public void onDestroy() {
        SoundManager.getINSTANCE().playSound("enemyDeath1");
        Random r = Game.getGeneralRandom();
        DrawingManager.getINSTANCE().addScreenShake(5);

        for (int i = 0; i < r.nextInt(3) + 4; i++)
            e.addEntity(new Giblet(x, y,r.nextInt(20)-10,r.nextInt(20)-10,"gore" + (r.nextInt(3) + 4)));
    }

    @Override
    public int getXpOnKill() {
        return 15;
    }
}
