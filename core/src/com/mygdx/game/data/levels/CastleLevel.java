package com.mygdx.game.data.levels;

import com.mygdx.game.data.ILevel;
import com.mygdx.game.data.ITileCollum;
import com.mygdx.game.data.tilesets.Gap;
import com.mygdx.game.data.tilesets.castle.CastleRegular;
import java.util.Random;

public class CastleLevel implements ILevel {

    private final ITileCollum[] tileSet = {new CastleRegular(), new Gap()};
    private final float[] tileWeight = {20, 5};
    private final float totalWeight;

    public CastleLevel(){
        float temp = 0;
        for (float f: tileWeight) temp += f;
        totalWeight = temp;
    }

    @Override
    public ITileCollum[] getTileset() {
        return tileSet;
    }

    @Override
    public ITileCollum randomTile(Random r, int y) {
        float rng = r.nextFloat() * totalWeight;
        float currentWeight = 0;
        for (int i = 0; i < tileSet.length; i++) {
            currentWeight += tileWeight[i];
            if (rng < currentWeight) return tileSet[i].getNew(y);
        }
        //fall back
        return tileSet[0];
    }

    // height related stuff
    @Override
    public int defaultHeight() {return 128;}
    @Override
    public int maxHeight() {return 192;}
    @Override
    public int minHeight() {return 64;}

    // height change chance
    @Override
    public float changeChance() {return 0.8f;}
    @Override
    public int changeLengthMax() {return 3;}
    @Override
    public int changeLengthMin() {return 2;}


}
