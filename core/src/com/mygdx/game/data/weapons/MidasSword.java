package com.mygdx.game.data.weapons;

import com.mygdx.game.entities.player.Projectiles.midasSword.ChargedMidasSwordSwipe;
import com.mygdx.game.entities.player.Projectiles.midasSword.MidasSwordSwipe;
import com.mygdx.game.logic.player.Weapon;

public class MidasSword extends Weapon {

    public MidasSword() {
        super("Midas sword", "wIcon1","midas_sword0", 0, 1);
    }

    @Override
    public void attack(float x, float y) {

        ent.addEntity(new MidasSwordSwipe(x + 48, y));
    }

    @Override
    public void chargedAttack(float x, float y) {
        ent.addEntity(new ChargedMidasSwordSwipe(x + 48, y));

    }
}
