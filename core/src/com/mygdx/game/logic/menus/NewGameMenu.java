package com.mygdx.game.logic.menus;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.player.InventoryManager;
import com.mygdx.game.logic.player.ItemManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.stage.LevelProgressionManager;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.InvisUIParent;
import com.mygdx.game.ui.elements.regularElements.Button;

public class NewGameMenu {
    private static NewGameMenu INSTANCE;
    public static NewGameMenu getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new NewGameMenu();
        return INSTANCE;
    }

    private static final LevelProgressionManager lvlMan = LevelProgressionManager.getINSTANCE();

    public void draw(){
    }

    public void onTransition(){
        // TODO : class selection
        // TODO : class unlocks
        // TODO : mastery point screen

    }

    public void startNewGame(long newSeed){
        EntityManager.getINSTANCE().clear();
        ItemManager.getINSTANCE().clearItems();
        Game.getSeededRandom().setSeed(newSeed);
        InventoryManager.getINSTANCE().resetState();
        PlayerStats.getINSTANCE().resetStats();
        lvlMan.resetProgress();
        lvlMan.progressLevel();
        lvlMan.startLevel();
        UIManager.getINSTANCE().transition(GameState.Game);
    }
}
