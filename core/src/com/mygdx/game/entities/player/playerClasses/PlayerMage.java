package com.mygdx.game.entities.player.playerClasses;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;

public class PlayerMage extends PlayerKnight{


    public PlayerMage(float x, float y) {
        super(x, y);
    }


    @Override
    public void draw(DrawingManager spr) {
        // player rendering
        if (!(iFrame > 0 && (Game.Time()>>2)%2 == 0))
            if ((input.getButtonCharge(Controls.MoveLeft) > input.getHoldSensitivity() || input.getButtonCharge(Controls.MoveRight) > input.getHoldSensitivity()) && y == lvlY)
                spr.drawGame("mage1", x, y,2);
            else if (y != lvlY)
                spr.drawGame("mage2", x, y,2);
            else if (actionTimer > actionTimerFull / 2)
                spr.drawGame("mage3", x, y,2);
            else if (actionTimer > 0)
                spr.drawGame("mage4", x, y,2);
            else
                spr.drawGame("mage0", x, y,2);
    }

    @Override
    public int getEnergyRechargeTime() {
        return 180;
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new PlayerMage(x,y);
    }
}
