package com.mygdx.game.entities.player.playerClasses;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
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
                spr.draw("hunter1", x, y,2);
            else if (y != lvlY)
                spr.draw("hunter2", x, y,2);
            else if (actionTimer > actionTimerFull / 2)
                spr.draw("hunter3", x, y,2);
            else if (actionTimer > 0)
                spr.draw("hunter4", x, y,2);
            else
                spr.draw("hunter0", x, y,2);

        for (FollowerObject f: followers) {
            if (longJumping) f.addCoordinate(x, y, "hunter2");
            f.draw();
        }
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
