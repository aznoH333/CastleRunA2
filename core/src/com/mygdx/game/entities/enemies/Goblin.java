package com.mygdx.game.entities.enemies;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class Goblin extends Enemy {

    private String sprite = "goblin0";
    private int hopTimer = 0;
    private final static int timerMax = 25;
    private boolean direction = true;
    private float yM = 0;

    // TODO : sound
    // TODO : proper spawning

    public Goblin(float x, float y) {
        super(x, y, 64, 64, 2, Team.Enemies);
    }

    @Override
    public void update(LevelManager lvl, Random r) {

        float lvlY = lvl.getLevelY(this);
        boolean landed = y <= lvlY - yM && yM <= 0;
        if (landed){
            yM = 0;
            y = lvlY;
        }else{
            yM -= 0.8f;
        }


        //jump
        if (hopTimer == 0){
            hopTimer = timerMax;
            direction = r.nextBoolean();
            if (direction){
                if (lvl.getOnPos(x - (lvl.getTileScale() >> 1)).getSpecial() == TileCollumSpecial.Gap){
                    moveTo = x - (lvl.getTileScale() * 2);
                } else {
                    moveTo = x - lvl.getTileScale();
                }
            }
            else {
                if (lvl.getOnPos(x + xSize + (lvl.getTileScale() >> 1)).getSpecial() == TileCollumSpecial.Gap){
                    moveTo = x + (lvl.getTileScale() * 2);
                } else {
                    moveTo = x + lvl.getTileScale();
                }
            }
            yM = 4;

        }

        if (moveTo > x) x += 8;
        if (moveTo < x) x -= 8;
        y += yM;
        if (landed) hopTimer--;

        //update sprite
        int bruh = 0;
        if (!direction) bruh += 2;
        if (!landed) bruh++;

        sprite = "goblin" + bruh;
    }

    @Override
    public void draw(DrawingManager spr) {
        spr.draw(sprite, x, y, 1);
    }

    @Override
    public void onCollide(Entity other) {
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Goblin(x,y);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getXpOnKill() {
        return 30;
    }
}
