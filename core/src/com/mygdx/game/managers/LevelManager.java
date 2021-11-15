package com.mygdx.game.managers;

import com.mygdx.game.data.*;

import java.util.Random;

public class LevelManager {


    //constants
    private final float advanceSpeed = 6f;
    private final int stepHeight = 16;
    private final int tileScale = 64;
    private final int mapWidth = 21;

    //vars
    private SpriteManager spr;
    private float distance = 0;
    private float advanceDistance = 0;
    private ITileCollum[] map = new ITileCollum[mapWidth];
    private Level lvl;
    private Random r;
    private EntityManager e;
    //lvl generation vars
    private int height = 0;
    private boolean grace = false;
    private Directions dir = Directions.None;
    private int changeFor = 0;

    public LevelManager(SpriteManager spr, Random r){
        this.spr = spr;
        this.r = r;
    }
    public void setE(EntityManager e){
        this.e = e;
    }

    //TODO: generate start & end to levels
    //TODO: fix generated entities having a 1 pixel offset
    //TODO: implement backgrounds
    private void generateLevel(int index){
        // tile selection & grace handling
        ITileCollum temp;
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
        for (int x = 0; x < mapWidth; x++) {
            ITileCollum curr = map[x];
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
            distance += advanceBy;

            //shift map & generate
            //shift only while advancing
            if(distance%tileScale < advanceBy){
                for (int i = 1; i < mapWidth; i++) {
                    map[i-1] = map[i];
                }
                generateLevel(20);
            }
        }


        renderLevel();
    }

    public ITileCollum getOnPos(float i){
        return map[(int) (i/tileScale)];
    }

    public int getTileScale(){return tileScale;}

    public void loadLevel(Level lvl){
        distance = 0;
        this.lvl = lvl;
        height = lvl.defaultHeight();

        // pre generate first screen
        for (int i = 0; i < mapWidth; i++) {
            generateLevel(i);
        }
    }
}
