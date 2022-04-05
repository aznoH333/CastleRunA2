package com.mygdx.game.logic.menus;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.logic.player.InventoryManager;
import com.mygdx.game.logic.player.ItemManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.stage.StageManager;
import com.mygdx.game.ui.UIManager;

public class NewGameMenu {
    private static NewGameMenu INSTANCE;
    public static NewGameMenu getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new NewGameMenu();
        return INSTANCE;
    }

    public void draw(){
        // TODO: stage selection
        // TODO: starting equipment selection
    }

    public void startNewGame(int stageIndex, long newSeed){
        Game.getSeededRandom().setSeed(newSeed);
        StageManager s = StageManager.getINSTANCE();
        s.startNewGameFromStage(stageIndex);
        PlayerStats.getINSTANCE().resetStats();
        ItemManager.getINSTANCE().clearItems();
        InventoryManager.getINSTANCE().resetState();
        s.startLevel();
        UIManager.getINSTANCE().transition(GameState.Game);
    }
}
