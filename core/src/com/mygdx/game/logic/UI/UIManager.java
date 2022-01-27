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

    private static final float barYPos = 1224;
    private static final float barCenterPositionA = 320;
    private static final float barCenterPositionB = 380;
    private static final float barStart = 64;
    private static final float barEnd = 632;



    public static final int buttonWidth = 84 * 4;
    public static final int buttonHeight = 48 * 4;
    private static final byte buttonOffset = 16;
    private static final int bottomButtonOffset = 32;
    private static final int iconOffsetX = 128;
    private static final int iconOffsetY = 96;
    private final SpriteManager spr = SpriteManager.getINSTANCE();
    private final PlayerStats ps = PlayerStats.getINSTANCE();
    private final InputManager input = InputManager.getINSTANCE();
    private final Button[] buttons = {
            new Button("button0","button1","player2","player3"
                    ,buttonOffset, buttonOffset + bottomButtonOffset,iconOffsetX,iconOffsetY,()-> input.buttonHold(Controls.MoveLeft)),
            new Button("button0","button1","icon0","icon1"
                    ,buttonOffset * 2 + buttonWidth , buttonOffset + bottomButtonOffset,iconOffsetX,iconOffsetY,()-> input.buttonHold(Controls.MoveRight)),
            new Button("button0","button1","icon4","icon5"
                    ,buttonOffset , buttonOffset * 2 + buttonHeight + bottomButtonOffset,iconOffsetX,iconOffsetY,()-> input.buttonHold(Controls.AttackLeft)),
            new Button("button0","button1","icon4","icon5"
                    ,buttonOffset * 2 + buttonWidth , buttonOffset * 2 + buttonHeight + bottomButtonOffset, iconOffsetX,iconOffsetY,()-> input.buttonHold(Controls.AttackRight)),
    };

    // FIXME : when the player dies and his health is negative, the health bar overflows
    // TODO : add a bit more wood to the ui
    // FIXME : player sometimes takes damage from spikes when jumping over them (happen only if the spikes are higher than the player)


    public void drawGameUI(){

        // TopUi
        spr.draw("hudTop0",0,1184,4);
        spr.draw("hudTop1",0,1184,4);
        spr.draw("hudTop2",0,1184,8);

        // hpbar
        for (int i = 0; i < ps.getMaxHp(); i++) {
            final float x = barStart + (i * ((barCenterPositionA-barStart)/ps.getMaxHp()));
            final boolean b = i >= ps.getMaxHp() - ps.getHp();
            if (b)
                spr.draw("barUI0",x,barYPos,7);
            else
                spr.draw("barUI5",x,barYPos,7);
        }
        for (int i = (int) (barStart + ((ps.getMaxHp() - ps.getHp()) * ((barCenterPositionA-barStart)/ps.getMaxHp())))+24; i < barCenterPositionA; i+=24)
            spr.draw("barUI1",i,barYPos,6);

        for (int i = (int) (barStart+24); i < (barStart + ((ps.getMaxHp() - ps.getHp()) * ((barCenterPositionA-barStart)/ps.getMaxHp())))+24; i+=24)
            spr.draw("barUI4",i,barYPos,5);


        // energybar
        for (int i = ps.getMaxEnergy(); i > 0; i--) {
            final float x = barCenterPositionB + (i * ((barEnd - barCenterPositionB)/ps.getMaxEnergy()));
            final boolean b = i <= ps.getEnergy();
            if (b)
                spr.draw("barUI3",x,barYPos,7);
            else
                spr.draw("barUI6",x,barYPos,7);
        }
        for (int i = (int) Math.ceil(barEnd - ((ps.getMaxEnergy() - ps.getEnergy()) * ((barEnd - barCenterPositionB)/ps.getMaxEnergy())))-24; i > barCenterPositionB; i-=24)
            spr.draw("barUI2",i,barYPos,6);
        for (int i = (int) barEnd-24; i > (int) Math.ceil(barEnd - ((ps.getMaxEnergy() - ps.getEnergy()) * ((barEnd - barCenterPositionB)/ps.getMaxEnergy())))-24; i-=24)
            spr.draw("barUI4",i,barYPos,5);



        // touch buttons
        spr.draw("hudBot0",0,0,4);
        // buttons
        for(Button button: buttons){
            button.manageInput();
            button.draw();
        }

    }


}
