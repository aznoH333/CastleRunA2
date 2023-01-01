package com.mygdx.game.entities.player.Projectiles.boneSword;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class BoneSwordShockWave extends Projectile {

    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    private final boolean left;
    private int lifeTime = 12;
    public BoneSwordShockWave(float x, float y, boolean left) {
        super(x, y, 32, 32, 1, Team.PlayerProjectiles, 120);
        this.left = left;
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.draw("shockwave" + ((2 - (lifeTime/4)) + (left ? 0 : 3)), x, y, 1, 1, 1, new Color(1,1,1, (float) lifeTime/12), true);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new BoneSwordShockWave(x, y, left);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    protected void onEnemyHit(Enemy other) {
        other.takeDamage(1);
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        lifeTime--;
        if (lifeTime <= 0) destroy();
        x += 8f * (left ? -1 : 1);
    }
}
