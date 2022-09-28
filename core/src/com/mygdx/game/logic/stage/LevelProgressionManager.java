package com.mygdx.game.logic.stage;

import com.mygdx.game.Game;
import com.mygdx.game.data.levelgeneration.TileWeightData;
import com.mygdx.game.data.tilesets.traps.BreakingPlatform;
import com.mygdx.game.data.tilesets.traps.Gap;
import com.mygdx.game.data.tilesets.traps.GhostPlatform;
import com.mygdx.game.data.tilesets.traps.SpikeTrap;
import com.mygdx.game.logic.level.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class LevelProgressionManager {

    private static LevelProgressionManager INSTANCE;
    private static LevelProgressionManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new LevelProgressionManager();
        return INSTANCE;
    }

    int currentLevelIndex = 0;
    Level currentLevel;
    LevelTemplate lTemplate = LevelTemplate.Castle;
    private final static Random r = Game.getSeededRandom();

    public void startLevel(){

    }

    public void progressLevel(){
        currentLevelIndex++;
        switch (Game.getSeededRandom().nextInt(Math.max(Math.min(3,currentLevelIndex),1))){
            default:
            case 0: lTemplate = LevelTemplate.Castle; break;
            case 1: lTemplate = LevelTemplate.Cave; break;
            case 2: lTemplate = LevelTemplate.Forest; break;
            case 3: lTemplate = LevelTemplate.CastleYard; break;
        }
        if (currentLevelIndex % 5 == 0){
            // boss level
        }else{
            // regular level
            // add tiles
            ArrayList<TileWeightData> temp = new ArrayList<>(Arrays.asList(lTemplate.defaultTiles));

            // add traps
            // TODO : this is kinda dumb. think of a rework
            temp.add(new TileWeightData(5f, new Gap()));
            if (currentLevelIndex >= 1)     temp.add(new TileWeightData(2f, new SpikeTrap()));
            if (currentLevelIndex >= 2)     temp.add(new TileWeightData(2f, new BreakingPlatform()));
            if (currentLevelIndex >= 4)     temp.add(new TileWeightData(2f, new GhostPlatform()));

            currentLevel = new Level.LevelBuilder(temp.toArray(), );
        }

    }
}
