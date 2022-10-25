package com.mygdx.game.logic.gamestates;

import com.mygdx.game.ui.interfaces.IUIElement;

public interface IGameState {
    void update();
    IUIElement[] getUI();
}
