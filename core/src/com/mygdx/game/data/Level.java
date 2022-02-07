package com.mygdx.game.data;

import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.EntityFactory;

import java.util.Random;

public class Level {
    private final TileCollum[] tileSet;
    private final float[] tileWeight;
    private final String[] enemySet;
    private final float[] enemyWeight;
    private final float totalWeight;
    private final float totalEnemyWeight;
    private final int length;
    private EntityFactory e = EntityFactory.getInstance();

    //lvl height
    private final int defaultH;
    private final int maxH;
    private final int minH;

    private final float cChange;
    private final int clMax;
    private final int clMin;

    //back
    private final Background background;

    //map stuff
    private final String mapTile;
    private final String mapIcon;

    public Level(TileCollum[] tileSet,
                 float[] tileWeight,
                 String[] enemySet,
                 float[] enemyWeight,
                 int defaultH,
                 int maxH,
                 int minH,
                 float cChange,
                 int clMax,
                 int clMin,
                 Background background,
                 int length,
                 String mapTile,
                 String mapIcon){
        this.tileSet = tileSet;
        this.tileWeight = tileWeight;
        this.enemySet = enemySet;
        this.enemyWeight = enemyWeight;
        this.defaultH = defaultH;
        this.maxH = maxH;
        this.minH = minH;
        this.cChange = cChange;
        this.clMax = clMax;
        this.clMin = clMin;
        this.background = background;
        this.length = length;
        this.mapIcon = mapIcon;
        this.mapTile = mapTile;

        float temp = 0;
        for (float f: tileWeight) temp += f;
        totalWeight = temp;
        temp = 0;
        for (float f: enemyWeight) temp += f;
        totalEnemyWeight = temp;
    }

    // it returns tiles according to their spawn chance
    public TileCollum randomTile(Random r, int y) {
        float rng = r.nextFloat() * totalWeight;
        float currentWeight = 0;
        for (int i = 0; i < tileSet.length; i++) {
            currentWeight += tileWeight[i];
            if (rng < currentWeight) return tileSet[i].getNew(y);
        }
        //fall back
        return tileSet[0];
    }

    public Entity randomEnemy(Random r, float x, int y) {
        float rng = r.nextFloat() * totalEnemyWeight;
        float currentWeight = 0;
        for (int i = 0; i < enemyWeight.length; i++) {
            currentWeight += enemyWeight[i];
            if (rng < currentWeight)
                if (i == 0) return null;
                else return e.getByName(enemySet[i-1],x,y);
        }
        return null;
    }

    //height
    public int maxHeight(){return maxH;}
    public int minHeight(){return minH;}

    //height change
    public float changeChance(){return cChange;}
    public int changeLengthMax(){return clMax;}
    public int changeLengthMin(){return clMin;}

    public Background getBackground(){return background;}

    public int getLength(){
        return length;
    }

    public String getMapTile(){
        return mapTile;
    }

    public String getMapIcon(){
        return mapIcon;
    }
}
