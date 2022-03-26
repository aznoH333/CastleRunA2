package com.mygdx.game.logic.stage;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.entities.player.Player;
import com.mygdx.game.logic.Shop;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.level.LevelOwner;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.ItemManager;

import java.util.ArrayList;

public class StageManager {

    private static StageManager INSTANCE;

    public static StageManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new StageManager();
        return INSTANCE;
    }

    // TODO : shops
    // TODO : save stage unlocks

    private static final ArrayList<Stage> stages = new ArrayList<>();
    private static final Shop shop = Shop.getINSTANCE();

    private Stage currentStage;
    // ty kokos wtf
    private int stageCompletionIndex = -1;

    public StageManager(){
        stages.add(new Stage(
                new String[]{"1-1","1-2","1-3","1-4"},
                "mapBack0",
                new Decorator[]{
                        new Decorator("islandDecorator0",-96,-2),
                        new Decorator("islandDecorator3",260,130),
                        new Decorator("islandDecorator2",-20,270),
                        new Decorator("islandDecorator1",340,385),
                },
                120,4));

        advanceStage();
    }

    public void startLevel(){
        LevelManager.getINSTANCE().loadLevel(LevelOwner.getINSTANCE().getByName(currentStage.getCurrentLevel()));
        EntityManager.getINSTANCE().addEntity(new Player(64, 0, 64, 64));
        ItemManager.getINSTANCE().onLevelStart();
    }

    public void advanceInStage(){
        Game.changeState(GameState.StageMenu);

        currentStage.advanceInStage();
        if (currentStage.getLevels().length == currentStage.getCurrentStageIndex()){
            // TODO : this
            advanceStage();
            System.out.println("progressed");
        }
        StageMap.getINSTANCE().updateCurrentStage();
    }

    public void advanceStage(){
        stageCompletionIndex++;
        currentStage = stages.get(stageCompletionIndex);
        shop.restock(currentStage.getShopLevel());

    }

    public Stage getCurrentStage(){
        return currentStage;
    }
}
