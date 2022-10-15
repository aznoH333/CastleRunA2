package com.mygdx.game.entities.player.playerClasses;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;

public class PlayerHunter extends PlayerKnight{


    public PlayerHunter(float x, float y) {
        super(x, y);
    }


    @Override
    public void draw(DrawingManager spr) {
        // player rendering
        if (!(iFrame > 0 && (Game.Time()>>2)%2 == 0))
            if ((input.getButtonCharge(Controls.MoveLeft) > input.getHoldSensitivity() || input.getButtonCharge(Controls.MoveRight) > input.getHoldSensitivity()) && y == lvlY)
                spr.drawGame("hunter1", x, y,2);
            else if (y != lvlY)
                spr.drawGame("hunter2", x, y,2);
            else if (actionTimer > actionTimerFull / 2)
                spr.drawGame("hunter3", x, y,2);
            else if (actionTimer > 0)
                spr.drawGame("hunter4", x, y,2);
            else
                spr.drawGame("hunter0", x, y,2);
    }

    @Override
    public int getEnergyRechargeTime() {
        return 180;
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new PlayerHunter(x,y);
    }
}
