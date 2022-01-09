package com.mygdx.game.logic.stage;

import com.mygdx.game.entities.player.Player;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.level.LevelBuilder;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.PlayerStats;

import java.util.ArrayList;

public class StageManager {

    private static StageManager INSTANCE;

    public static StageManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new StageManager();
        return INSTANCE;
    }

    // TODO : stage progression
    // TODO : stage backdrops
    // TODO : some interstage screen
    // TODO : shops
    // TODO : save stage unlocks

    private static final ArrayList<Stage> stages = new ArrayList<>();
    private Stage currentStage;

    public StageManager(){
        // I hate this
        String[] bruh = {"1-1","1-2"};
        stages.add(new Stage(bruh));

        currentStage = stages.get(0);
    }

    public void startLevel(){
        LevelManager.getINSTANCE().loadLevel(LevelBuilder.getINSTANCE().getByName(currentStage.getCurrentLevel()));
        // xtreme lidl
        // respawns the player
        EntityManager.getINSTANCE().addEntity(new Player(64, 0, 64, 64));
    }

    public void advanceInStage(){
        currentStage.advanceInStage();
        startLevel();
    }
}
