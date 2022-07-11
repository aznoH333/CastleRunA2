package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;
import com.mygdx.game.data.levelgeneration.EntityWeightData;
import com.mygdx.game.data.levelgeneration.TileWeightData;
import com.mygdx.game.logic.level.tileCollums.TileCollum;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.EntityFactory;

import java.util.Random;

public class Level {
    private final TileWeightData[] tileSet;
    private final EntityWeightData[] enemySet;
    private final float emptyChance;
    private final float totalWeight;
    private final float totalEnemyWeight;
    private final int length;
    private final EntityFactory e = EntityFactory.getInstance();
    private final int activationRate;

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
        this.enemySet = builder.enemySet;
        this.emptyChance = builder.emptyChance;
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
        this.activationRate = builder.activationRate;



        float temp = 0;
        for (TileWeightData f: tileSet) temp += f.getWeight();
        totalWeight = temp;
        temp = emptyChance;
        for (EntityWeightData f: enemySet) temp += f.getWeight();
        totalEnemyWeight = temp;
    }

    // it returns tiles according to their spawn chance
    public TileCollum randomTile(Random r, int y) {
        float rng = r.nextFloat() * totalWeight;
        float currentWeight = 0;

        for (TileWeightData t: tileSet) {
            currentWeight += t.getWeight();
            if (rng < currentWeight) return t.getTile().getNew(y);
        }

        //fall back
        return tileSet[0].getTile().getNew(y);
    }

    public Entity randomEnemy(Random r, float x, int y) {
        float rng = r.nextFloat() * totalEnemyWeight;
        float currentWeight = 0;
        for (EntityWeightData d: enemySet){
            currentWeight += d.getWeight();
            if (rng < currentWeight)
                return e.getByName(d.getEntity(),x,y);
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

    public String getBoss(){ return null; }

    public int getActivationRate(){
        return activationRate;
    }


    public static class LevelBuilder{

        private final TileWeightData[] tileSet;
        private final Background background;
        private final int length;

        private EntityWeightData[] enemySet = {};
        private float emptyChance = 1;

        //lvl height
        protected final int defaultH;
        private int maxH = 192;
        private int minH = 64;

        private float cChange = 0.8f;
        private int clMax = 3;
        private int clMin = 2;
        private String boss;

        private int activationRate = 240;

        //map stuff
        private String mapIcon = "mIconCastle0";
        private String mapTile = "mTile0";

        public LevelBuilder(TileWeightData[] tileSet, int length, int defaultH, Background background){
            this.tileSet = tileSet;

            this.length = length * LevelManager.tileScale;
            this.defaultH = defaultH;
            this.background = background;
        }

        public LevelBuilder enemies(float emptyChance, EntityWeightData[] enemySet){
            this.enemySet = enemySet;
            this.emptyChance = emptyChance;
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
            BossLevel lvl = new BossLevel(this, boss);
            reset();
            return lvl;
        }

        public LevelBuilder setBoss(String boss){
            this.boss = boss;
            return this;
        }

        public LevelBuilder setActivationRate(int value){
            this.activationRate = value;
            return this;
        }

        private void reset(){
            enemySet = new EntityWeightData[]{};
            emptyChance = 1;
            boss = null;
            maxH = 192;
            minH = 64;
            cChange = 0.8f;
            clMax = 3;
            clMin = 2;
            activationRate = 240;
            mapIcon = "mIconCastle0";
            mapTile = "mTile0";
        }


    }
}
