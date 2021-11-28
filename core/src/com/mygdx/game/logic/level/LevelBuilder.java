package com.mygdx.game.logic.level;

import com.mygdx.game.data.ITileCollum;
import com.mygdx.game.data.Level;
import com.mygdx.game.data.tilesets.Gap;
import com.mygdx.game.data.tilesets.castle.CastleRegular;
import com.mygdx.game.logic.entities.EntityFactory;

import java.util.HashMap;


public class LevelBuilder {
    private static LevelBuilder INSTANCE;
    public static LevelBuilder getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new LevelBuilder();
        return INSTANCE;
    }
    private HashMap<String, Level> levels = new HashMap<>();

    private ITileCollum[] tileSet = {new CastleRegular(), new Gap()};
    private float[] tileWeight = {20,5};
    private String[] enemySet = {"slime"};
    private float[] enemyWeight = {10,5};
    private EntityFactory e = EntityFactory.getInstance();

    //lvl height
    private int defaultH = 128;
    private int maxH = 192;
    private int minH = 64;

    private float cChange = 0.8f;
    private int clMax = 3;
    private int clMin = 2;
    // TODO: make a background builder or somethin
    private String[][] back = {
            {"castleBack0", "castleBack1", "castleBack0", "castleBack1", "castleBack2", "castleBack2", "castleBack2", "castleBack2", "castleBack2"},
            {"castleBack1", "castleBack0", "castleBack1", "castleBack0", "castleBack3", "castleBack3", "castleBack3", "castleBack3", "castleBack3"},
            {"castleBack0", "castleBack1", "castleBack0", "castleBack1", "castleBack4", "castleBack4", "castleBack4", "castleBack4", "castleBack4"},
            {"castleBack1", "castleBack0", "castleBack1", "castleBack0", "castleBack4", "castleBack4", "castleBack4", "castleBack5", "castleBack4"},
            {"castleBack0", "castleBack1", "castleBack0", "castleBack1", "castleBack4", "castleBack4", "castleBack4", "castleBack4", "castleBack4"},
            {"castleBack1", "castleBack0", "castleBack1", "castleBack0", "castleBack4", "castleBack4", "castleBack4", "castleBack4", "castleBack4"},
            {"castleBack0", "castleBack1", "castleBack0", "castleBack1", "castleBack4", "castleBack4", "castleBack4", "castleBack5", "castleBack4"},
            {"castleBack1", "castleBack0", "castleBack1", "castleBack0", "castleBack4", "castleBack4", "castleBack4", "castleBack4", "castleBack4"},
    };
    private float parallax = 1;

    public Level build(){
        Level temp = new Level(tileSet, tileWeight, enemySet, enemyWeight, defaultH, maxH, minH, cChange, clMax, clMin, back, parallax);
        reset();
        return temp;
    }

    public LevelBuilder(){
        // stage 1
        s(
                new ITileCollum[]{new CastleRegular(), new Gap()},
                new float[]{20,5},
                new String[]{"slime"},
                new float[]{10,5}
        );
        p(0.25f);
        levels.put("1-1",build());
    }

    public Level getByName(String name){
        return levels.get(name);
    }


    //builder stuff
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
        // TODO: background builder
        back = new String[][]{{"player0"}};
        parallax = 1;
    }

    private void h(int defaultH, int minH, int maxH){
        this.defaultH = defaultH;
        this.minH = minH;
        this.maxH = maxH;
    }

    private void c(float cChange,int clMinm, int clMax){
        this.cChange = cChange;
        this.clMin = clMinm;
        this.clMax = clMax;
    }

    private void s(ITileCollum[] tileSet, float[] tileWeight, String[] enemySet, float[] enemyWeight) {
        this.tileSet = tileSet;
        this.tileWeight = tileWeight;
        this.enemySet = enemySet;
        this.enemyWeight = enemyWeight;
    }

    private void p(float parallax){
        this.parallax = parallax;
    }
}
