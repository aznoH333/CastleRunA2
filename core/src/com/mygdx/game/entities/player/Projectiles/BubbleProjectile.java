package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class BubbleProjectile extends Projectile {

    private final static float missChance = 0.66f;
    private final static float gravity = 0.1f;

    private final float xM;
    private float yM;
    private int timer = 0;
    private final int timerMax;

    public BubbleProjectile(float x, float y) {
        super(x, y, 32, 32, 1, Team.PlayerProjectiles, 0);
        xM = Game.getGeneralRandom().nextFloat() * 3 + 6;
        yM = Game.getGeneralRandom().nextFloat() * 5 - 2.5f;
        timerMax = Game.getGeneralRandom().nextInt(20) + 20;
    }

    @Override
    public void draw(DrawingManager spr) {
        if (timer < 20) spr.draw("bubble0", x, y, 1);
        else if (timer < 40) spr.draw("bubble1", x, y, 1);
        else spr.draw("bubble2", x, y, 1);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new BubbleProjectile(x, y);
    }

    @Override
    public void onDestroy() {
        ParticleManager.getINSTANCE().addParticle("bubble_splash",x,y,0,0,0,(int)(Math.random()*10 + 10));
    }

    @Override
    protected void onEnemyHit(Enemy other) {
        if (Game.getGeneralRandom().nextFloat() > missChance) other.takeDamage(1);
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        this.x += xM;
        this.y += yM;
        this.yM += gravity;

        timer++;
        if (timer == timerMax || lvl.collidesWithLevel(this)) destroy();
    }
}
