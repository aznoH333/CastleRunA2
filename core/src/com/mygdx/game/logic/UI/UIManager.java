package com.mygdx.game.logic.UI;

import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.sprites.SpriteManager;

public class UIManager {

    private static UIManager INSTANCE;
    public static UIManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new UIManager();
        return INSTANCE;
    }
    // 336 px per button

    private final int xOffset = 32;
    private final int yOffset = 640;
    private final int energyOffset = 48;
    private static final byte uiHeight = 9;
    public static final int buttonWidth = 84 * 4;
    public static final int buttonHeight = 48 * 4;
    private static final byte buttonOffset = 16;
    private static final int iconOffsetX = 128;
    private static final int iconOffsetY = 96;
    private SpriteManager spr = SpriteManager.getINSTANCE();
    private PlayerStats ps = PlayerStats.getINSTANCE();
    private InputManager input = InputManager.getINSTANCE();
    private Button[] buttons = {
            new Button("button0","button1","player2","player3"
                    ,buttonOffset, buttonOffset,iconOffsetX,iconOffsetY,()-> input.buttonHold(Controls.MoveLeft)),
            new Button("button0","button1","icon0","icon1"
                    ,buttonOffset * 2 + buttonWidth , buttonOffset,iconOffsetX,iconOffsetY,()-> input.buttonHold(Controls.MoveRight)),
            new Button("button0","button1","icon4","icon5"
                    ,buttonOffset , buttonOffset * 2 + buttonHeight,iconOffsetX,iconOffsetY,()-> input.buttonHold(Controls.AttackLeft)),
            new Button("button0","button1","icon4","icon5"
                    ,buttonOffset * 2 + buttonWidth , buttonOffset * 2 + buttonHeight, iconOffsetX,iconOffsetY,()-> input.buttonHold(Controls.AttackRight)),
    };

    // TODO: re-do ui to fit the screen perfectly


    public void drawGameUI(){

        // TopUi
        spr.draw("hudTop0",0,1184,4);
        spr.draw("hudTop1",0,1184,4);
        spr.draw("hudTop2",0,1184,4);

        // bars
        // health
        spr.drawGame("barUI2",xOffset-32,yOffset);
        for (int i = 0; i < ps.getMaxHp(); i++) {
            if (i < ps.getHp()) spr.drawGame("barUI0",xOffset + i*32,yOffset);
            else                spr.drawGame("barUI1",xOffset + i*32,yOffset);
        }
        spr.drawGame("barUI4",xOffset+ ps.getMaxHp()*32,yOffset);

        // energy
        spr.drawGame("barUI3",xOffset-32,yOffset - energyOffset);
        for (int i = 0; i < ps.getMaxEnergy(); i++) {
            if (i < ps.getEnergy()) spr.drawGame("barUI5",xOffset + i*32,yOffset - energyOffset);
            else                    spr.drawGame("barUI6",xOffset + i*32,yOffset - energyOffset);
        }
        spr.drawGame("barUI4",xOffset+ ps.getMaxEnergy()*32,yOffset - energyOffset);


        // touch buttons
        spr.draw("hudBot0",0,0,4);
        // buttons
        for(Button button: buttons){
            button.manageInput();
            button.draw();
        }

    }


}
