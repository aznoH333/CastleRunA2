package com.mygdx.game.data.weapons;

import com.mygdx.game.entities.player.Projectiles.fireball.FireBallProjectile;
import com.mygdx.game.entities.player.Projectiles.fireball.Meteorite;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.player.Weapon;

public class FireBall extends Weapon {
    private final static EntityManager ent = EntityManager.getINSTANCE();

    public FireBall() {
        super("fireball0", 2, 4);
    }

    @Override
    public void attack(float x, float y) {
        ent.addEntity(new FireBallProjectile(x, y + 17));
    }

    @Override
    public void chargedAttack(float x, float y) {
        ent.addEntity(new Meteorite( x - 64, y + 712));
    }
}
