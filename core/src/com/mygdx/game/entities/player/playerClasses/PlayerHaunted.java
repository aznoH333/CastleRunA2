package com.mygdx.game.entities.player.playerClasses;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.abstracts.Entity;

public class PlayerHaunted extends PlayerKnight{
    public PlayerHaunted(float x, float y) {
        super(x, y);
    }

    @Override
    public void draw(DrawingManager spr) {
        // player rendering
        if (!(iFrame > 0 && (Game.Time()>>2)%2 == 0))
            if ((input.getButtonCharge(Controls.MoveLeft) > input.getHoldSensitivity() || input.getButtonCharge(Controls.MoveRight) > input.getHoldSensitivity()) && y == lvlY)
                spr.draw("haunted1", x, y,2, true,1);
            else if (y != lvlY)
                spr.draw("haunted2", x, y,2, true,1);
            else if (actionTimer > actionTimerFull / 2)
                spr.draw("haunted3", x, y,2, true,1);
            else if (actionTimer > 0)
                spr.draw("haunted4", x, y,2, true,1);
            else
                spr.draw("haunted0", x, y,2, true,1);

        for (FollowerObject f: followers) {
            if (longJumping) f.addCoordinate(x, y, "haunted2");
            f.draw();
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new PlayerHaunted(x, y);
    }
}
