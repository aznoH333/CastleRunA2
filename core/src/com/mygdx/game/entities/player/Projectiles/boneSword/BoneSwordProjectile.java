package com.mygdx.game.entities.player.Projectiles.boneSword;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class BoneSwordProjectile extends Projectile {

    private float yM;
    private boolean falling = true;
    private int destructionTimer = 30;
    private float opacity = 1;

    public BoneSwordProjectile(float x, float y, float startingYM) {
        super(x, y, 64, 64, 1, Team.PlayerProjectiles, 120);
        yM = -startingYM;
    }

    @Override
    public void draw(DrawingManager spr) {
        //spr.draw("bone_sword0",x, y - 64, -1);
        spr.draw("bone_sword0", x, y - 64, -1, 1, 1, new Color(1,1,1, opacity), true);

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new BoneSwordProjectile(x, y, 0);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    protected void onEnemyHit(Enemy other) {
        if (falling)
            other.takeDamage(1);
    }

    @Override
    protected void projectileUpdate(LevelManager lvl, Random r) {
        if (falling){
            yM -= 0.5f;
            y += yM;

            if (lvl.collidesWithLevel(this)){

                e.addEntity(new BoneSwordShockWave(x + 8, y + 8, true));
                e.addEntity(new BoneSwordShockWave(x + 8, y + 8, false));
                falling = false;
                DrawingManager.getINSTANCE().addScreenShake(5);
                y = lvl.getLevelY(this);
            }
        }
        else {
            if (destructionTimer == 0){
                opacity-=0.1f;
                if (opacity <= 0) destroy();
            }
            else
                destructionTimer--;
        }
    }
}
