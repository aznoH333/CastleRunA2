package com.mygdx.game.logic.level;

import com.mygdx.game.data.*;
import com.mygdx.game.data.enums.Directions;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.SpriteManager;

import java.util.Random;

public class LevelManager {


    //constants
    private final float advanceSpeed = 6f;
    private final int stepHeight = 16;
    public static final int tileScale = 64;
    public static final int mapWidth = 21;

    //vars
    private final SpriteManager spr;
    private float distance = 0;
    private float advanceDistance = 0;
    private final TileCollum[] map = new TileCollum[mapWidth];
    private final BackgroundRenderer b;
    private Level lvl;
    private Random r;
    private EntityManager e;
    private ParticleManager part = ParticleManager.getINSTANCE();
    //lvl generation vars
    private int height = 0;
    private boolean grace = false;
    private Directions dir = Directions.None;
    private int changeFor = 0;

    public LevelManager(SpriteManager spr, Random r){
        this.spr = spr;
        this.r = r;
        this.b = BackgroundRenderer.getINSTANCE();
    }
    public void setE(EntityManager e){
        this.e = e;
    }

    //TODO: generate start & end to levels
    private void generateLevel(int index){
        // tile selection & grace handling
        TileCollum temp;
        while (true) {
            temp = lvl.randomTile(r, height);
            if (!grace || !temp.grace()){
                if (temp.grace()){
                    grace = true;
                    height = lastHeight;
                }
                break;
            }
        }
        //add enemies
        if (!grace){
            Entity tempE = lvl.randomEnemy(r, index * tileScale - (distance % tileScale), height);
            if (tempE != null){
                e.addEntity(tempE);
            }
        }
        moveInDirection();
        changeDirection();
        if (!temp.grace()) grace = false;
        map[index] = temp;

    }

    private void changeDirection(){
        //change direction
        if (changeFor < 1){
            if (r.nextFloat() < lvl.changeChance()){
                if (r.nextBoolean()) dir = Directions.Up;
                else                 dir = Directions.Down;
            }   else                 dir = Directions.None;
            changeFor = r.nextInt(lvl.changeLengthMax()-lvl.changeLengthMin()) + lvl.changeLengthMin();
        }
    }
    private int lastHeight = 0;
    private void moveInDirection(){
        // move in direction
        if (!grace){
            lastHeight = height;
            switch (dir){
                case Down:
                    if (height > lvl.minHeight())
                        height -= stepHeight;
                    break;
                case Up:
                    if (height < lvl.maxHeight())
                        height += stepHeight;
                    break;
            }
            changeFor--;
        }
    }

    private void renderLevel(){
        b.draw(spr);

        for (int x = 0; x < mapWidth; x++) {
            TileCollum curr = map[x];
            if (curr.getSpecial() != TileCollumSpecial.Gap)
                for (int y = 0; y < curr.getY()/tileScale+2; y++) {
                    spr.draw(curr.getTexture(y), x*tileScale-(distance%tileScale), curr.getY() - y*tileScale);
                }
        }
    }

    public void advanceTiles(int distanceInTiles){
        advanceDistance += distanceInTiles * tileScale;
    }

    public void advanceToTile(float dist){
        if (dist + distance > advanceDistance)
            advanceDistance = dist + distance;
    }


    public boolean isScrolling(){
        return distance < advanceDistance;
    }

    public int getMapWidth(){
        return mapWidth;
    }

    public void update(){
        //advance
        if (advanceDistance > distance){
            float advanceBy = (float) Math.ceil(advanceSpeed * Math.abs((distance/tileScale)-(advanceDistance/tileScale)));
            e.shiftAllEntities(advanceBy);
            part.shiftPartsBy(advanceBy);
            distance += advanceBy;
            b.advance(advanceBy * lvl.getParallax());

            //shift map & generate
            //shift only while advancing
            if(distance%tileScale < advanceBy){
                System.arraycopy(map, 1, map, 0, mapWidth - 1);
                generateLevel(20);
            }
        }


        renderLevel();
    }

    public TileCollum getOnPos(float i){
        return map[(int) (i/tileScale)];
    }

    public int getTileScale(){return tileScale;}

    public void loadLevel(Level lvl){
        b.setBackground(lvl.getBackground());
        distance = 0;
        this.lvl = lvl;
        height = lvl.defaultHeight();

        // pre generate first screen
        for (int i = 0; i < mapWidth; i++) {
            generateLevel(i);
        }
    }
}
