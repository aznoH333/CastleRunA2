package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;
import com.mygdx.game.data.TileCollum;
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
    private final EntityFactory e = EntityFactory.getInstance();

    //lvl height
    protected final int defaultH;
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

    public Level(LevelBuilder builder){
        this.tileSet = builder.tileSet;
        this.tileWeight = builder.tileWeight;
        this.enemySet = builder.enemySet;
        this.enemyWeight = builder.enemyWeight;
        this.length = builder.length;
        this.defaultH = builder.defaultH;
        this.maxH = builder.maxH;
        this.minH = builder.minH;
        this.cChange = builder.cChange;
        this.clMax = builder.clMax;
        this.clMin = builder.clMin;
        this.background = builder.background;
        this.mapIcon = builder.mapIcon;
        this.mapTile = builder.mapTile;



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

    public boolean isBossLevel(){
        return false;
    }

    public String getMapIcon(){
        return mapIcon;
    }


    public static class LevelBuilder{

        private final TileCollum[] tileSet;
        private final float[] tileWeight;
        private final Background background;
        private final int length;

        private String[] enemySet = {"slime"};
        private float[] enemyWeight = {10,5};

        //lvl height
        protected final int defaultH;
        private int maxH = 192;
        private int minH = 64;

        private float cChange = 0.8f;
        private int clMax = 3;
        private int clMin = 2;

        //map stuff
        private String mapIcon = "mIconCastle0";
        private String mapTile = "mTile0";

        public LevelBuilder(TileCollum[] tileSet, float[] tileWeight, int length, int defaultH, Background background){
            this.tileSet = tileSet;
            this.tileWeight = tileWeight;
            this.length = length * LevelManager.tileScale;
            this.defaultH = defaultH;
            this.background = background;
        }

        public LevelBuilder enemies(String[] enemySet,float[] enemyWeight){
            this.enemySet = enemySet;
            this.enemyWeight = enemyWeight;
            return this;
        }

        public LevelBuilder height(int maxH, int minH){
            this.maxH = maxH;
            this.minH = minH;
            return this;
        }

        public LevelBuilder chance(float cChange, int clMax, int clMin){
            this.cChange = cChange;
            this.clMax = clMax;
            this.clMin = clMin;
            return this;
        }

        public LevelBuilder mapTiles(String mapIcon, String mapTile){
            this.mapTile = mapTile;
            this.mapIcon = mapIcon;
            return this;
        }

        public Level build(){
            Level lvl = new Level(this);
            reset();
            return lvl;
        }

        public BossLevel buildBossLevel(){
            BossLevel lvl = new BossLevel(this);
            reset();
            return lvl;
        }

        private void reset(){
            enemySet = new String[]{"slime"};
            enemyWeight = new float[]{10,5};
            maxH = 192;
            minH = 64;
            cChange = 0.8f;
            clMax = 3;
            clMin = 2;
            mapIcon = "mIconCastle0";
            mapTile = "mTile0";
        }
    }
}
