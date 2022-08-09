package com.mygdx.game.entities.enemies.bosses.mechboss;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class SawbladeProjectile extends Entity {
    private float yM = 10;
    private final static float xM = -4f;
    public SawbladeProjectile(float x, float y) {
        super(x, y, 64, 64, 1, Team.EnemyProjectiles);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        yM -= 0.5f;
        if (lvl.collidesWithLevel(this) && yM < 0){
            yM *= -1;
        }

        x += xM;
        y += yM;
    }

    @Override
    public void draw(DrawingManager spr) {
        // TODO : sprite
        spr.draw("player0",x ,y, 3);
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new SawbladeProjectile(x, y);
    }

    @Override
    public void onDestroy() {

    }
}
