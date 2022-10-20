package com.mygdx.game.entities.player.Projectiles.midasSword;

import com.mygdx.game.Game;
import com.mygdx.game.entities.environment.Coin;
import com.mygdx.game.entities.player.Projectiles.sword.ChargedSwordSwipe;
import com.mygdx.game.logic.drawing.ColorType;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.abstracts.Enemy;

import java.util.Random;

public class ChargedMidasSwordSwipe extends ChargedSwordSwipe {

    private static final Random r = Game.getGeneralRandom();

    public ChargedMidasSwordSwipe(float x, float y) {
        super(x, y);
    }

    private final FollowerObject[] followers = {
            new FollowerObject(1, 1, ColorType.Opacity50),
            new FollowerObject(1, 3, ColorType.Opacity25),
    };

    @Override
    public void draw(DrawingManager spr) {
        if (lifeTime>0){
            String sprite = "midas_sword" + (int) Math.ceil((13 - lifeTime) >> 1);
            spr.drawGame(sprite,x,y,1);
            for (FollowerObject f: followers) {
                f.addCoordinate(x, y, sprite);
                f.draw();
            }
        }
    }

    @Override
    protected void onEnemyHit(Enemy other) {
        super.onEnemyHit(other);
        if (r.nextFloat() > 0.66) {
            e.addEntity(new Coin(other.getX(), other.getY()));
        }
    }
}
