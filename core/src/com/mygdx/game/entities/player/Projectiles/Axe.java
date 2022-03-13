package com.mygdx.game.entities.player.Projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class Axe extends Entity {

    protected int pierceTimer = 0;
    private static final int pierceTimerMax = 16;
    private static final float xM = 6f;
    private float yM = 10f;
    protected static final float gravity = 0.5f;

    public Axe(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (pierceTimer > 0) pierceTimer--;
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
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.Enemies && pierceTimer == 0){
            other.takeDamage(2);
            pierceTimer = pierceTimerMax;
        }

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Axe(x, y, xSize, ySize, hp);
    }

    @Override
    public void onDestroy() {
    }
}
