package com.mygdx.game.logic.stage;

import com.mygdx.game.data.Background;
import com.mygdx.game.data.levelgeneration.TileWeightData;
import com.mygdx.game.data.tilesets.castle.CastleRegular;
import com.mygdx.game.data.tilesets.cave.CaveRegular;
import com.mygdx.game.data.tilesets.forest.ForestRegular;
import com.mygdx.game.data.tilesets.forest.ForestRegular2;
import com.mygdx.game.data.tilesets.forest.ForestRegular3;
import com.mygdx.game.logic.level.Backgrounds;


public enum LevelTemplate {

    Castle(new TileWeightData[]{
            new TileWeightData(30f, new CastleRegular())
    }, Backgrounds.getINSTANCE().castle(), 0.5f, 2, 3),

    Cave(new TileWeightData[]{
            new TileWeightData(25f, new CaveRegular())
    }, Backgrounds.getINSTANCE().cave(), 0.7f, 1, 2),

    Forest(new TileWeightData[]{
            new TileWeightData(10, new ForestRegular()),
            new TileWeightData(10f, new ForestRegular2()),
            new TileWeightData(10f, new ForestRegular3()),
    }, Backgrounds.getINSTANCE().forest(), 0.7f, 2, 3),

    CastleYard(new TileWeightData[]{
            new TileWeightData(30, new CastleRegular()),
    }, Backgrounds.getINSTANCE().yard(), 0.5f, 3, 5);

    public final TileWeightData[] defaultTiles;
    public final Background background;
    public final float cChance;
    public final int cMin;
    public final int cMax;

    LevelTemplate(TileWeightData[] defaultTiles, Background background, float cChance, int cMin, int cMax){
        this.defaultTiles = defaultTiles;
        this.background = background;
        this.cChance = cChance;
        this.cMin = cMin;
        this.cMax = cMax;
    }
}
