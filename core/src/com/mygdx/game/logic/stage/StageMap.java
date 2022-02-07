package com.mygdx.game.logic.stage;

import com.mygdx.game.data.Level;
import com.mygdx.game.logic.level.LevelBuilder;
import com.mygdx.game.logic.sprites.SpriteManager;

public class StageMap {
    public static StageMap INSTANCE;

    public static StageMap getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new StageMap();
        return INSTANCE;
    }

    private SpriteManager spr;
    private StageManager stageManager;
    private Stage currentStage;
    private byte currentStageIndex;
    private LevelBuilder lvb = LevelBuilder.getINSTANCE();

    private static final int offset = 482;
    private static final int levelOffset = 128;
    private static final int levelLeftX = 160;
    private static final int levelRightX = 416;
    private static final int pathOffsetX = 64;
    private static final int pathOffsetY = 32;
    private int playerNumber = 256;

    public StageMap(){
        spr = SpriteManager.getINSTANCE();
        stageManager = StageManager.getINSTANCE();
        updateCurrentStage();
    }

    public void update(){
        // draw back
        // temp
        // TODO : this
        spr.draw("player0",0,offset,-1);

        // levels
        for (int i = 0; i < currentStage.getLevels().length; i++) {
            Level lvl = lvb.getByName(currentStage.getLevels()[i]);
            if (i%2 == 0){
                spr.draw(lvl.getMapTile(),levelLeftX,i * levelOffset + offset,1);
                spr.draw(lvl.getMapIcon(),levelLeftX,i * levelOffset + offset + 14,1);
            }

            else{
                spr.draw(lvl.getMapTile(),levelRightX,i * levelOffset + offset,1);
                spr.draw(lvl.getMapIcon(),levelRightX,i * levelOffset + offset + 14,1);

            }

            // paths

            for (int j = 1; j <= 3 && i+1 < currentStage.getLevels().length; j++)
                if (i%2 == 0)
                    spr.draw("mTile1",levelLeftX + j * pathOffsetX,i * levelOffset + offset + j * pathOffsetY,1);
                else
                    spr.draw("mTile1",levelRightX - j * pathOffsetX,i * levelOffset + offset + j * pathOffsetY,1);
        }


        //player
        if (currentStageIndex % 2 == 0)
            spr.draw("player0",levelLeftX + 16, currentStageIndex * levelOffset + offset + currentStageIndex * pathOffsetY,2);
        else
            spr.draw("player0",levelRightX + 16, currentStageIndex * levelOffset + offset + currentStageIndex * pathOffsetY,2);

    }

    public void updateCurrentStage(){
        currentStage = stageManager.getCurrentStage();
        currentStageIndex = currentStage.getCurrentStageIndex();
        playerNumber = 256;
    }
}
