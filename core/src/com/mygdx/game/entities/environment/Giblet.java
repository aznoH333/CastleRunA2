package com.mygdx.game.entities.environment;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;

import java.util.Random;

public class Giblet extends Entity {

    private final String sprite;
    private final FollowerObject follower;
    private float xM;
    private float yM;
    private int deSpawnTimer = 120;
    private float deSpawnPercentage = 1f;



    public Giblet(float x, float y, float xM, float yM, String sprite) {
        super(x, y, 32, 32, 1, Team.Neutral);
        this.sprite = sprite;
        this.xM = xM;
        this.yM = yM;
        follower = new FollowerObject(0, 3, 0.5f);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        if (lvl.collidesWithLevel(this)){
            if (Math.abs(yM) > 1f){
                yM = Math.abs(yM) * 0.6f;
                xM *= 0.6f;
            }
            else{
                yM = 0;
                xM = 0;
                deSpawnTimer--;
            }
        }
        else yM -= 0.5f;
        y += yM;
        x += xM;

        if (deSpawnTimer <= 0) deSpawnPercentage -= 0.05f;
        if (deSpawnPercentage <= 0) destroy();
        if (Math.abs(xM) > 1 || Math.abs(yM) > 1) follower.addCoordinate(x, y, sprite);
    }

    @Override
    public void draw(DrawingManager spr) {
        follower.draw();
        spr.draw(sprite, x + ((1 - deSpawnPercentage) * 16), y, 0, false, deSpawnPercentage);
    }

    @Override
    public void onCollide(Entity other) {
        if ((other.getTeam() == Team.PlayerProjectiles || other.getTeam() == Team.EnemyProjectiles) && yM == 0){
            yM = (Game.getGeneralRandom().nextFloat()) * 15;
            xM = (Game.getGeneralRandom().nextFloat() - 0.5f) * 10;
        }

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new Giblet(x, y, xM, yM, sprite);
    }

    @Override
    public void onDestroy() {

    }
}
