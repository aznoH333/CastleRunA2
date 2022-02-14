package com.mygdx.game.logic.level;

import com.mygdx.game.data.Background;
import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.Level;
import com.mygdx.game.data.tilesets.cave.CaveRegular;
import com.mygdx.game.data.tilesets.traps.Gap;
import com.mygdx.game.data.tilesets.castle.CastleRegular;
import com.mygdx.game.data.tilesets.traps.SpikeTrap;
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

    private TileCollum[] tileSet = {new CastleRegular(), new Gap()};
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

    private int length = 6400;

    private String mapIcon = "mIconCastle0";
    private String mapTile = "mTile0";

    public Level build(){
        Level temp = new Level(tileSet, tileWeight, enemySet, enemyWeight, defaultH, maxH, minH, cChange, clMax, clMin, back, length, mapTile, mapIcon);
        reset();
        return temp;
    }

    public LevelBuilder(){
        // stage 1
        s(
                new TileCollum[]{new CastleRegular(), new Gap(), new SpikeTrap()},
                new float[]{20,5,2},
                new String[]{"slime","chest"},
                new float[]{20,5,1}
        );
        b(Backgrounds.castle());
        l(30);
        m("mIconCastle0","mTile0");
        levels.put("1-1",build());

        s(
                new TileCollum[]{new CaveRegular(), new Gap()},
                new float[]{15,5},
                new String[]{"slime","chest"},
                new float[]{10,5,1}
        );
        c(0.9f,1,3);
        h(96,64,128);
        b(Backgrounds.cave());
        l(120);
        m("mIconCave0","mTile0");
        levels.put("1-2",build());

        s(
                new TileCollum[]{new CastleRegular(), new Gap(), new SpikeTrap()},
                new float[]{20,5,5},
                new String[]{"slime","chest"},
                new float[]{10,5,1}
        );
        c(0.9f,1,3);
        h(96,64,128);
        b(Backgrounds.castle());
        l(120);
        m("mIconCastle1","mTile0");
        levels.put("1-3",build());

        s(
                new TileCollum[]{new CaveRegular(), new Gap()},
                new float[]{15,5},
                new String[]{"slime","chest"},
                new float[]{10,5,1}
        );
        c(0.9f,1,3);
        h(96,64,128);
        b(Backgrounds.cave());
        l(120);
        m("mIconCave1","mTile2");
        levels.put("1-4",build());

    }

    public Level getByName(String name){
        return levels.get(name);
    }

    private Background back;

    //builder stuff
    private void reset(){
        defaultH = 128;
        maxH = 192;
        minH = 64;
        cChange = 0.8f;
        clMax = 3;
        clMin = 2;
        tileSet = new TileCollum[]{new CastleRegular(), new Gap()};
        tileWeight = new float[]{20,5};
        enemySet = new String[]{"slime"};
        enemyWeight = new float[]{10,5};
        back = Backgrounds.castle();
        length = 6400;
        mapIcon = "mIconCastle0";
        mapTile = "mTile0";
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

    private void s(TileCollum[] tileSet, float[] tileWeight, String[] enemySet, float[] enemyWeight) {
        this.tileSet = tileSet;
        this.tileWeight = tileWeight;
        this.enemySet = enemySet;
        this.enemyWeight = enemyWeight;
    }

    private void m(String icon, String tile){
        this.mapIcon = icon;
        this.mapTile = tile;
    }

    private void l(int length){
        this.length = length * LevelManager.tileScale;
    }
    private void b(Background back){
        this.back = back;
    }
}
