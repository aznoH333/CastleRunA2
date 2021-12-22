package com.mygdx.game.entities.environment;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class Chest extends Entity {

    private boolean isOpen = false;
    private static final float gravity = 0.5f;
    private float yM = 0f;
    private static final float hopStrength = 5f;
    private boolean airborne = false;
    private int coinsToSpawn = 30;

    public Chest(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.Environment);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if(airborne){
            yM -= gravity;
            y += yM;
            final int lvlY = lvl.getOnPos(x + (lvl.getTileScale() -1)).getY() + lvl.getTileScale();

            if (y <= lvlY - yM && yM <= 0){
                yM = 0;
                y = lvlY;
                airborne = false;
            }
        }
        //spawn coins
        if (isOpen && coinsToSpawn > 0){
            int coinCounter = coinsToSpawn >> 3;
            if (coinCounter < 1) coinCounter = 1;

            for (int i = 0; i < coinCounter; i++) {
                EntityManager.getINSTANCE().spawnEntity("coin pickup",x+((int)xSize>>2),y+((int)xSize>>2));
            }
            coinsToSpawn -= coinCounter;
        }
    }

    @Override
    public void draw(SpriteManager spr) {
        if (isOpen) spr.draw("chest1",x,y, 0);
        else        spr.draw("chest0",x,y,0);
    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.PlayerProjectiles && !isOpen){
            isOpen = true;
            airborne = true;
            yM = hopStrength;
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Chest(x,y+ySize,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {}
}
