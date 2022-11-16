package com.mygdx.game.entities.player.Projectiles.boneSword;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class BoneSwordProjectile extends Projectile {
    public BoneSwordProjectile(float x, float y) {
        super(x, y, 64, 64, 1, Team.PlayerProjectiles, 0);
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.draw("bone_sword0",x, y, 1);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new BoneSwordProjectile(x, y); // TODO : this
    }

    @Override
    public void onDestroy() {

    }

    @Override
    protected void onEnemyHit(Enemy other) {

    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {

    }
}
