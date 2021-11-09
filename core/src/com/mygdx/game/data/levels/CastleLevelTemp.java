package com.mygdx.game.data.levels;

import com.mygdx.game.data.Entity;
import com.mygdx.game.data.ILevel;
import com.mygdx.game.data.ITileCollum;
import com.mygdx.game.data.tilesets.Gap;
import com.mygdx.game.data.tilesets.castle.CastleRegular;
import com.mygdx.game.managers.EnemyEntityFactory;

import java.util.Random;

public class CastleLevelTemp implements ILevel {

    //TODO: rewrite levels as abstract classes
    //TODO: MAKE SOME FUCKING SPRITES, YOU LAZY BASTARD!
    private final ITileCollum[] tileSet = {new CastleRegular(), new Gap()};
    private final float[] tileWeight = {20, 5};
    private EnemyEntityFactory e = EnemyEntityFactory.getInstance();
    // note enemies are way rarer than tiles bcs of grace
    private final String[] enemySet = {"slime"};
    private final float[] enemyWeight = {10,5};
    private final float totalWeight;
    private final float totalEnemyWeight;

    public CastleLevelTemp(){
        float temp = 0;
        for (float f: tileWeight) temp += f;
        totalWeight = temp;
        temp = 0;
        for (float f: enemyWeight) temp += f;
        totalEnemyWeight = temp;
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

    @Override
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
