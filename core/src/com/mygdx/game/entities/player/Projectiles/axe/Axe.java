package com.mygdx.game.entities.player.Projectiles.axe;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.ColorType;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.abstracts.Projectile;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class Axe extends Projectile {

    private static final int pierceTimerMax = 16;
    private static final float xM = 6f;
    private float yM = 10f;
    protected static final float gravity = 0.5f;
    private final FollowerObject[] followers = {
            new FollowerObject(1, 2, ColorType.Opacity50),
            new FollowerObject(1, 4, ColorType.Opacity25)
    };

    public Axe(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles, pierceTimerMax);
    }



    @Override
    public void projectileUpdate(LevelManager lvl, Random r) {
        yM -= gravity;
        x += xM;
        y += yM;
    }

    @Override
    public void draw(DrawingManager spr) {
        String sprite = "axe" + ((Game.Time()>>2) % 4);
        spr.draw(sprite, x, y, 1);
        for (FollowerObject f: followers) {
            f.addCoordinate(x, y , sprite);
            f.draw();
        }

    }

    @Override
    protected void onEnemyHit(Enemy other) {
        other.takeDamage(2);
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Axe(x, y, xSize, ySize, hp);
    }

    @Override
    public void onDestroy() {
    }
}
