package com.mygdx.game.logic.gamestates;

import com.mygdx.game.data.enums.BarType;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.ui.elements.parents.BottomHud;
import com.mygdx.game.ui.elements.parents.TopHud;
import com.mygdx.game.ui.elements.regularElements.Button;
import com.mygdx.game.ui.elements.regularElements.CoinCounter;
import com.mygdx.game.ui.elements.regularElements.HudBar;
import com.mygdx.game.ui.elements.regularElements.Sprite;
import com.mygdx.game.ui.elements.regularElements.XpBar;
import com.mygdx.game.ui.interfaces.IUIElement;

import java.util.ArrayList;

public class Game implements IGameState{

    private final static ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final LevelManager lvl = LevelManager.getINSTANCE();
    private final ParticleManager part = ParticleManager.getINSTANCE();
    private final EntityManager ent = EntityManager.getINSTANCE();
    private final PlayerStats stats = PlayerStats.getINSTANCE();
    private final DrawingManager spr = DrawingManager.getINSTANCE();
    private final InputManager input = InputManager.getINSTANCE();
    public Game(){
        // bottom hud
        uiElements.add(new BottomHud(-515f,-150f));
        uiElements.add(new Button(16,182, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.MoveLeft)));
        uiElements.add(new Sprite((com.mygdx.game.Game.gameWorldWidth/2 - 64)  / 2, 38,"icon1", uiElements.get(uiElements.size()-1)));
        uiElements.add(new Button(com.mygdx.game.Game.gameWorldWidth/2 + 8,182, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.MoveRight)));
        uiElements.add(new Sprite((com.mygdx.game.Game.gameWorldWidth/2 - 64) / 2, 38,"icon0", uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(16,315, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.AttackLeft)));
        uiElements.add(new Sprite((com.mygdx.game.Game.gameWorldWidth/2 - 64)  / 2, 38,"icon3", uiElements.get(uiElements.size()-1)));

        uiElements.add(new Button(com.mygdx.game.Game.gameWorldWidth/2 + 8,315, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.AttackRight)));
        uiElements.add(new Sprite((com.mygdx.game.Game.gameWorldWidth/2 - 64)  / 2, 38,"icon2", uiElements.get(uiElements.size()-1)));

        // top hud
        TopHud topHud = new TopHud();
        uiElements.add(topHud);
        PlayerStats playerStats = PlayerStats.getINSTANCE();
        uiElements.add(new HudBar(16,80,"barStart0", BarType.RED, topHud, playerStats::getHp, playerStats::getMaxHp));
        uiElements.add(new HudBar(16, 40,  "barStart1", BarType.BLU,  topHud, playerStats::getEnergy, playerStats::getMaxEnergy));
        uiElements.add(new CoinCounter(com.mygdx.game.Game.gameWorldWidth - 232, 40, topHud));
        uiElements.add(new XpBar(16, 0, topHud));
    }

    @Override
    public void update() {
        lvl.update();
        part.update();
        part.draw(spr);
        ent.update();
        stats.update();
    }
    
    @Override
    public IUIElement[] getUI() {
        return uiElements.toArray(new IUIElement[0]);
    }
}
