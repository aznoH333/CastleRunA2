package com.mygdx.game.managers;

import com.mygdx.game.data.ITileCollum;
import com.mygdx.game.data.Level;
import com.mygdx.game.data.tilesets.Gap;
import com.mygdx.game.data.tilesets.castle.CastleRegular;


public class LevelBuilder {
    private static LevelBuilder INSTANCE;
    public static LevelBuilder getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new LevelBuilder();
        return INSTANCE;
    }

    private ITileCollum[] tileSet = {new CastleRegular(), new Gap()};
    private float[] tileWeight = {20,5};
    private String[] enemySet = {"slime"};
    private float[] enemyWeight = {10,5};
    private EnemyEntityFactory e = EnemyEntityFactory.getInstance();

    //lvl height
    private int defaultH = 128;
    private int maxH = 192;
    private int minH = 64;

    private float cChange = 0.8f;
    private int clMax = 3;
    private int clMin = 2;

    public Level build(){
        Level temp = new Level(tileSet, tileWeight, enemySet, enemyWeight, defaultH, maxH, minH, cChange, clMax, clMin);
        reset();
        return temp;
    }

    private void reset(){
        defaultH = 128;
        maxH = 192;
        minH = 64;
        cChange = 0.8f;
        clMax = 3;
        clMin = 2;
        tileSet = new ITileCollum[]{new CastleRegular(), new Gap()};
        tileWeight = new float[]{20,5};
        enemySet = new String[]{"slime"};
        enemyWeight = new float[]{10,5};

    }
}
