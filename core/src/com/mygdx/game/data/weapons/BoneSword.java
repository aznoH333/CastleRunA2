package com.mygdx.game.data.weapons;

import com.mygdx.game.entities.player.Projectiles.boneSword.BoneSwordProjectile;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.player.Weapon;

public class BoneSword extends Weapon {

    private static final EntityManager ent = EntityManager.getINSTANCE();

    public BoneSword() {
        super("Bone Sword", "wIcon1", "sword0", 0, 2);
    }


    @Override
    public void attack(float x, float y) {
        ent.addEntity(new BoneSwordProjectile(x + 64, 1280, 10));
    }

    @Override
    public void chargedAttack(float x, float y) {
        ent.addEntity(new BoneSwordProjectile(x + 64, 1280, 20));
        ent.addEntity(new BoneSwordProjectile(x + 128, 1280, 10));
        ent.addEntity(new BoneSwordProjectile(x + 192, 1280, 0));
    }
}
