package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.items.effects.OnFire;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class FireBall extends Projectile {

    private final static float speed = 12.5f;
    private final static ParticleManager part = ParticleManager.getINSTANCE();
    private final static Random r = Game.getGeneralRandom();

    public FireBall(float x, float y) {
        super(x, y, 48, 32, 1, Team.PlayerProjectiles, 1);
    }

    @Override
    public void draw(DrawingManager spr) {
        // TODO : fireball sprites
        spr.drawGame("fireball" + ((Game.Time() / 3) % 4 + 1), x, y);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new FireBall(x, y);
    }

    @Override
    public void onDestroy() {
        // TODO : more sounds
        // TODO : explosion
        EntityManager.getINSTANCE().spawnEntity("micro explosion",x+32,y);
    }

    @Override
    protected void onEnemyHit(Enemy other) {
        other.applyEffect(new OnFire(100));
        destroy();
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        // TODO : more sounds
        x += speed;
        if (lvl.collidesWithLevel(this)){
            destroy();
        }

        if (Game.Time() % 2 == 0)
            part.addParticle("fire",x , y, -0.5f + r.nextInt(3), -2 + r.nextInt(4),0, 10 + r.nextInt(5));

    }
}
