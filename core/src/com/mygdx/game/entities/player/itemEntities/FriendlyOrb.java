package com.mygdx.game.entities.player.itemEntities;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.entities.player.playerClasses.PlayerKnight;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.drawing.FollowerObject;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.player.PlayerStats;

import java.util.Random;

public class FriendlyOrb extends Entity {

    private static final InputManager input = InputManager.getINSTANCE();
    private static final ParticleManager particles = ParticleManager.getINSTANCE();
    private final PlayerKnight playerKnight;
    private float xM = 0;
    private float yM = 0;
    private final static float speed = 0.2f;
    private static final float maxSpeed = 3.5f;
    private int cooldown = 0;
    private static final int cooldownMax = 640;
    private final EntityManager ent = EntityManager.getINSTANCE();
    private final FollowerObject[] followers = {
            new FollowerObject(2, 3, 0.5f),
            new FollowerObject(2, 6, 0.25f)
    };

    public FriendlyOrb(float x, float y) {
        super(x, y,16,16,1, Team.Environment);
        PlayerStats stats = PlayerStats.getINSTANCE();
        playerKnight = stats.getPlayer();
        shifts = false;
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        // spawn particle
        if (Game.Time() % 3 == 0 && cooldown == 0)
            particles.addParticle("electricity",x + r.nextInt((int) xSize) - 8, y + r.nextInt((int) ySize) - 8,0,0,0.01f);
        if (Game.Time() % 7 == 0)
            particles.addParticle("electricity",x + r.nextInt((int) xSize) - 8, y + r.nextInt((int) ySize) - 8,0,0,0.01f);

        // orbit player
        x += xM;
        y += yM;
        if (playerKnight.getX() + 26 > x)      xM = Math.min(xM + speed, maxSpeed);
        else                             xM = Math.max(xM - speed, -maxSpeed);
        if (playerKnight.getY() + 36 > y)      yM = Math.min(yM + speed, maxSpeed);
        else                             yM = Math.min(yM - speed, -maxSpeed);

        // shoot projectile
        if (cooldown > 0) cooldown--;

        if (cooldown == 0){
            if (!input.getButton(Controls.AttackRight) && input.getButtonCharge(Controls.AttackRight) > 0
                    || !input.getButton(Controls.AttackLeft) && input.getButtonCharge(Controls.AttackLeft) > 0){
                cooldown = cooldownMax;
                ent.spawnEntity("orb attack",x,y+16);
            }
        }

    }

    @Override
    public void draw(DrawingManager spr) {
        spr.drawGame("miniorb0",x,y,2);
        for (FollowerObject f: followers) {
            f.addCoordinate(x, y, "miniorb0");
            f.draw();
        }
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new FriendlyOrb(x,y);
    }

    @Override
    public void onDestroy() {

    }
}
