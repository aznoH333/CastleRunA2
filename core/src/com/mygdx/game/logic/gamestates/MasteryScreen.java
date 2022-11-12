package com.mygdx.game.logic.gamestates;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.logic.player.ProgressManager;
import com.mygdx.game.ui.UIManager;
import com.mygdx.game.ui.elements.parents.InvisUIParent;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.elements.regularElements.Text;
import com.mygdx.game.ui.interfaces.IUIElement;

import java.util.ArrayList;

public class MasteryScreen implements IGameState{
    private final static ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final UIManager ui = UIManager.getINSTANCE();
    private final ProgressManager prgrs = ProgressManager.getINSTANCE();
    public MasteryScreen(){
        uiElements.add(new InvisUIParent());


        String[] bonuses = prgrs.getBonuses().keySet().toArray(new String[]{});
        // stats
        for (int i = 0; i < bonuses.length; i++){
            int finalI = i;
            uiElements.add(new Button(16, 640 + 258 - (i * 128), ButtonType.Box, uiElements.get(0), ()-> prgrs.refundPoint(bonuses[finalI])));
            uiElements.add(new Sprite(8,8,"icon3", uiElements.get(uiElements.size()-1)));

            uiElements.add(new Text((Game.gameWorldWidth/2 - (5*25)),640 + 256 + 32 - (i * 128),bonuses[finalI],uiElements.get(0)));

            uiElements.add(new Button(Game.gameWorldWidth - 120 - 16, 640 + 256 - (i * 128), ButtonType.Box, uiElements.get(0), ()-> prgrs.spendPoint(bonuses[finalI])));
            uiElements.add(new Sprite(8,8,"icon2", uiElements.get(uiElements.size()-1)));
        }

        uiElements.add(new Button(16,64,ButtonType.Large,uiElements.get(0),()-> ui.transition(GameState.NewGameMenu)));
        uiElements.add(new Sprite((Game.gameWorldWidth/2 - 64)  / 2, 38,"icon0", uiElements.get(uiElements.size()-1)));
    }

    @Override
    public void update() {

    }

    @Override
    public IUIElement[] getUI() {
        return uiElements.toArray(new IUIElement[0]);
    }
}
