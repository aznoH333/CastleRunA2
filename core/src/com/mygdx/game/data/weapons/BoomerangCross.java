package com.mygdx.game.data.weapons;

import com.mygdx.game.entities.player.Projectiles.cross.ChargedCross;
import com.mygdx.game.entities.player.Projectiles.cross.Cross;
import com.mygdx.game.logic.player.Weapon;

public class BoomerangCross extends Weapon {
    public BoomerangCross() {
        super("Cross", "wIcon4","cross0", 1, 3);
    }

    @Override
    public void attack(float x, float y) {
        ent.addEntity(new Cross(x, y));
    }

    @Override
    public void chargedAttack(float x, float y) {
        ent.addEntity(new ChargedCross(x-16,y-32, 1));
        ent.addEntity(new ChargedCross(x-16,y-32, 2));
        ent.addEntity(new ChargedCross(x-16,y-32, 3));
        ent.addEntity(new ChargedCross(x-16,y-32, 4));
    }
}
