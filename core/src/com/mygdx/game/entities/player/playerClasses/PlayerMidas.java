package com.mygdx.game.entities.player.playerClasses;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.abstracts.Entity;

public class PlayerMidas extends PlayerKnight{
    public PlayerMidas(float x, float y) {
        super(x, y);
    }

    @Override
    public void draw(DrawingManager spr) {
        // player rendering
        if (!(iFrame > 0 && (Game.Time()>>2)%2 == 0))
            if ((input.getButtonCharge(Controls.MoveLeft) > input.getHoldSensitivity() || input.getButtonCharge(Controls.MoveRight) > input.getHoldSensitivity()) && y == lvlY)
                spr.draw("midas1", x, y,2);
            else if (y != lvlY)
                spr.draw("midas2", x, y,2);
            else if (actionTimer > actionTimerFull / 2)
                spr.draw("midas3", x, y,2);
            else if (actionTimer > 0)
                spr.draw("midas4", x, y,2);
            else
                spr.draw("midas0", x, y,2);

        for (FollowerObject f: followers) {
            if (longJumping) f.addCoordinate(x, y, "midas2");
            f.draw();
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new PlayerMidas(x, y);
    }
}
