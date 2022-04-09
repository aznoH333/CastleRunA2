package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
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
        switch ((byte) ((Game.Time()>>2) % 4)){
            case 0:
            default:
                spr.drawGame("axe0",x,y,1);
                break;
            case 1:
                spr.drawGame("axe1",x,y,1);
                break;
            case 2:
                spr.drawGame("axe2",x,y,1);
                break;
            case 3:
                spr.drawGame("axe3",x,y,1);
                break;
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
