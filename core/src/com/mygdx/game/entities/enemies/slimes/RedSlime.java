package com.mygdx.game.entities.enemies.slimes;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.EntityTags;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.entities.environment.Giblet;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Enemy;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class RedSlime extends Slime {
    public RedSlime(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp);
        tags = new EntityTags[]{EntityTags.Grounded};
    }



    @Override
    public void draw(DrawingManager spr) {
        if (landed)
            if (jumpTimer%animationSpeed > animationSpeed/2)
                spr.draw("red_slime0",x,y,1);
            else
                spr.draw("red_slime1",x,y,1);
        else
            spr.draw("red_slime2",x,y,1);
    }

    @Override
    public void onCollide(Entity other) {
    }

    @Override
    public void takeDamage(int damage){
        // TODO: hurt sound
        Random r = Game.getGeneralRandom();
        SoundManager.getINSTANCE().playSound("enemyDeath1");
        // spawn 1 - 3 gore particles
        for (int i = 0; i < r.nextInt(2) + 1; i++)
            e.addEntity(new Giblet(x, y,r.nextInt(20)-10,r.nextInt(20)-10,"gore" + (r.nextInt(3) + 4)));
        super.takeDamage(damage);
    }

    @Override
    public int getXpOnKill() {
        return 25;
    }

    @Override
    public Entity getCopy(float x, float y) {
        return new RedSlime(x,y + LevelManager.tileScale, xSize, ySize, hp);
    }

    @Override
    public void onDestroy() {
        // spawn particles
        SoundManager.getINSTANCE().playSound("enemyDeath1");
        Random r = Game.getGeneralRandom();
        DrawingManager.getINSTANCE().addScreenShake(7);
        ParticleManager.getINSTANCE().addParticle("redSlimeDeath",x,y,0,0,0);
        // spawn 5 - 10 gore particles
        for (int i = 0; i < r.nextInt(5) + 5; i++)
            e.addEntity(new Giblet(x, y,r.nextInt(20)-10,r.nextInt(20)-10,"gore" + (r.nextInt(3) + 4)));

    }
}
