package com.mygdx.game.logic.stage;

import com.mygdx.game.Game;
import com.mygdx.game.logic.level.Level;
import com.mygdx.game.logic.level.LevelOwner;
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
    private LevelOwner lvb = LevelOwner.getINSTANCE();

    private static final int offset = 482;
    private static final int levelOffset = 128;
    private static final int levelLeftX = 160;
    private static final int levelRightX = 416;
    private static final int pathOffsetX = 64;
    private static final int pathOffsetY = 32;
    private int playerNumber = 256;
    private int waitNumber = 45;

    public StageMap(){
        spr = SpriteManager.getINSTANCE();
        stageManager = StageManager.getINSTANCE();
        updateCurrentStage();
    }

    public void update(){
        // draw back
        spr.draw(currentStage.getBackground(),0, offset, 0);
        for (Decorator d: currentStage.getDecorators()) {
            spr.draw(d.getSprite(),d.getX(),d.getY() + offset,1);
        }

        // levels
        for (int i = 0; i < currentStage.getLevels().length; i++) {

            // TODO : this is ugly as shit, rewrite pls

            Level lvl = lvb.getByName(currentStage.getLevels()[i]);
            if (i%2 == 0){
                spr.draw(lvl.getMapTile(),levelLeftX,i * levelOffset + offset + currentStage.getAdditionalLevelOffset(),1);
                spr.draw(lvl.getMapIcon(),levelLeftX,i * levelOffset + offset + currentStage.getAdditionalLevelOffset() + 14,1);

            }
            else{
                spr.draw(lvl.getMapTile(),levelRightX,i * levelOffset + offset + currentStage.getAdditionalLevelOffset(),1);
                spr.draw(lvl.getMapIcon(),levelRightX,i * levelOffset + offset + currentStage.getAdditionalLevelOffset() + 14,1);

            }


            // paths

            for (int j = 1; j <= 3 && i+1 < currentStage.getLevels().length; j++)
                if (i%2 == 0)
                    spr.draw("mTile1",levelLeftX + j * pathOffsetX,i * levelOffset + offset + j * pathOffsetY + currentStage.getAdditionalLevelOffset(),1);
                else
                    spr.draw("mTile1",levelRightX - j * pathOffsetX,i * levelOffset + offset + j * pathOffsetY + currentStage.getAdditionalLevelOffset(),1);
        }

        //player
        if (currentStageIndex % 2 == 0)
            spr.draw("player0",levelLeftX + 22 + playerNumber, currentStageIndex * levelOffset + offset + 14 - (playerNumber>>1) + currentStage.getAdditionalLevelOffset(),2);
        else
            spr.draw("player0",levelRightX + 22 - playerNumber, currentStageIndex * levelOffset + offset + 14 - (playerNumber>>1) + currentStage.getAdditionalLevelOffset(),2);
        if (playerNumber > 0 && waitNumber <= 0) playerNumber-=2;
        if (waitNumber > 0) waitNumber--;
        // TODO: add player map sprites
    }

    public void updateCurrentStage(){
        currentStage = stageManager.getCurrentStage();
        currentStageIndex = currentStage.getCurrentStageIndex();
        playerNumber = 256;
        waitNumber = 45;
    }
}
