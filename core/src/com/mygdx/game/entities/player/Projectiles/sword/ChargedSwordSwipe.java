package com.mygdx.game.entities.player.Projectiles.sword;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class ChargedSwordSwipe extends SwordSwipe {

    private static final float xM = 5f;
    private final FollowerObject follower = new FollowerObject(0,2, 0.5f);
    private final FollowerObject follower2 = new FollowerObject(0,4, 0.25f);
    public ChargedSwordSwipe(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        x += xM;
        lifeTime--;
        if (lifeTime == 0) destroy();

        //particles
        //if(particleTimer == 0){
        //    particleTimer = particleTimerMax;
        //    ParticleManager.getINSTANCE().addParticle("miniSparkle",x+16,y+10,-0.5f,0f,0f);
        //}else particleTimer--;
    }

    @Override
    public void draw(DrawingManager spr) {

        if (lifeTime>0){
            String sprite = "sword" + (int) Math.ceil((13 - lifeTime) >> 1);
            spr.draw(sprite,x,y,1);
            follower.addCoordinate(x, y, sprite);
            follower2.addCoordinate(x, y, sprite);
            follower.draw();
            follower2.draw();
        }
    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.Enemies && hurts){
            other.takeDamage(2);
            hurts = false;
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new ChargedSwordSwipe(x,y);
    }
}
