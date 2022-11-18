package com.mygdx.game.entities.player.Projectiles.fireball;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.entities.player.itemEntities.MicroExplosion;
import com.mygdx.game.items.effects.OnFire;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class FireBallProjectile extends Projectile {

    private final static float speed = 12.5f;
    private final static ParticleManager part = ParticleManager.getINSTANCE();
    private final static Random r = Game.getGeneralRandom();
    private final FollowerObject[] followers = {
            new FollowerObject(1, 1, 0.25f),
            new FollowerObject(1, 2, 0.20f),
            new FollowerObject(1, 3, 0.15f),
    };

    public FireBallProjectile(float x, float y) {
        super(x, y, 64, 48, 1, Team.PlayerProjectiles, 1);
    }

    @Override
    public void draw(DrawingManager spr) {
        String sprite = "fireball" + ((Game.Time() / 3) % 4 + 1);
        spr.drawGame(sprite, x, y,1);

        for (FollowerObject f: followers) {
            f.addCoordinate(x, y, sprite);
            f.draw();
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new FireBallProjectile(x, y);
    }

    @Override
    public void onDestroy() {
        // TODO : more sounds
        // TODO : explosion
        e.addEntity(new MicroExplosion(x+32, y-16));
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
