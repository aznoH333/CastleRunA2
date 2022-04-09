package com.mygdx.game.entities.enemies.projectiles;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class Bone extends Entity {

    private final float xM;
    private final static float gravity = 0.5f;
    private float yM;

    public Bone(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.EnemyProjectiles);
        xM = (float) -((Math.random() * 4) + 3);
        yM = (float) (Math.random() * 5) + 5;
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        x += xM;
        y += yM;
        yM -= gravity;
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.drawGame("bone" + Game.Time()%6,x,y,2);
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Bone(x,y,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {

    }
}
