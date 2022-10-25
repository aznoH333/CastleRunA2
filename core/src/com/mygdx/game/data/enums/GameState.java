package com.mygdx.game.data.enums;

import com.mygdx.game.logic.gamestates.EquipMenu;
import com.mygdx.game.logic.gamestates.Game;
import com.mygdx.game.logic.gamestates.GameOver;
import com.mygdx.game.logic.gamestates.IGameState;
import com.mygdx.game.logic.gamestates.MainMenu;
import com.mygdx.game.logic.gamestates.MasteryScreen;
import com.mygdx.game.logic.gamestates.NewGameMenu;
import com.mygdx.game.logic.gamestates.Shop;
import com.mygdx.game.logic.gamestates.StageMenu;

public enum GameState {
    Game(new Game()),
    MainMenu(new MainMenu()),
    Shop(new Shop()),
    StageMenu(new StageMenu()),
    GameOver(new GameOver()),
    EquipMenu(new EquipMenu()),
    NewGameMenu(new NewGameMenu()),
    MasteryScreen(new MasteryScreen());


    public final IGameState state;

    GameState(IGameState state){
        this.state = state;
    }

}
