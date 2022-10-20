package com.mygdx.game.entities.player.Projectiles.midasSword;

import com.mygdx.game.Game;
import com.mygdx.game.entities.environment.Coin;
import com.mygdx.game.entities.player.Projectiles.sword.SwordSwipe;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import java.util.Random;

public class MidasSwordSwipe extends SwordSwipe {
    public MidasSwordSwipe(float x, float y) {
        super(x, y);
    }

    private static final Random r = Game.getGeneralRandom();

    @Override
    public void draw(DrawingManager spr) {
        if (lifeTime>0)
            spr.drawGame("midas_sword" + (int) Math.ceil((13 - lifeTime) >> 1),x,y,1);
    }

    @Override
    protected void onEnemyHit(Enemy other) {
        super.onEnemyHit(other);
        if (r.nextFloat() > 0.66) {
            e.addEntity(new Coin(other.getX(), other.getY()));
        }
    }
}
