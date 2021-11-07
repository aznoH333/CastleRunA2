package com.mygdx.game.managers;

import com.mygdx.game.data.Directions;
import com.mygdx.game.data.ILevel;
import com.mygdx.game.data.ITileCollum;
import com.mygdx.game.data.TileCollumSpecial;

import java.util.Random;

public class Level {


    //constants
    private final float advanceSpeed = 4f;
    private final int stepHeight = 16;
    private final int tileScale = 64;
    private final int mapWidth = 21;

    //vars
    private SpriteManager spr;
    private float distance = 0;
    private float advanceDistance = 0;
    private ITileCollum[] map = new ITileCollum[mapWidth];
    private ILevel lvl;
    private Random r;
    private EntityManager e;
    //lvl generation vars
    private int height = 0;
    private boolean grace = false;
    private Directions dir = Directions.None;
    private int changeFor = 0;

    public Level(SpriteManager spr, Random r){
        this.spr = spr;
        this.r = r;
    }
    public void setE(EntityManager e){
        this.e = e;
    }


    private void generateLevel(int index){
        // TODO : implement enemies
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


    public boolean isScrolling(){
        return distance < advanceDistance;
    }

    public void update(){
        //advance
        if (advanceDistance > distance){
            e.shiftAllEntities((float) Math.ceil(advanceSpeed*(((advanceDistance/tileScale)-(distance/tileScale)))));
            distance += (float) Math.ceil(advanceSpeed*(((advanceDistance/tileScale)-(distance/tileScale))));

            //shift map & generate
            //shift only while advancing
            if(distance%tileScale < advanceSpeed){
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

    public void loadLevel(ILevel lvl){
        distance = 0;
        this.lvl = lvl;
        height = lvl.defaultHeight();

        // pre generate first screen
        for (int i = 0; i < mapWidth; i++) {
            generateLevel(i);
        }
    }
}
