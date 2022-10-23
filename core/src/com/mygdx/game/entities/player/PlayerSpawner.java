package com.mygdx.game.entities.player;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.entities.player.playerClasses.PlayerKnight;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityFactory;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.PlayerStats;

import java.util.Random;

public class PlayerSpawner extends Entity {
    public PlayerSpawner(float x, float y) {
        super(x, y, 0, 0, 1, Team.Player);
        PlayerKnight p = (PlayerKnight) EntityFactory.getInstance().getByName(PlayerStats.getINSTANCE().getPlayerClass().playerObject, x, y);
        PlayerStats.getINSTANCE().updatePlayerStats(p);
        e.addEntity(p);
        destroy();
    }

    @Override
    public void update(LevelManager lvl, Random r) {
    }

    @Override
    public void draw(DrawingManager spr) {

    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new PlayerSpawner(x, y);
    }

    @Override
    public void onDestroy() {


    }
}
