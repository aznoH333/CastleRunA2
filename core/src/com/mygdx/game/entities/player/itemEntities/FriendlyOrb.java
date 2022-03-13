package com.mygdx.game.entities.player.itemEntities;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.entities.player.Player;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.drawing.DrawingManager;

import java.util.Random;

public class FriendlyOrb extends Entity {

    private static final InputManager input = InputManager.getINSTANCE();
    private static final ParticleManager particles = ParticleManager.getINSTANCE();
    private final Player player;
    private float xM = 0;
    private float yM = 0;
    private final static float speed = 0.2f;
    private static final float maxSpeed = 3.5f;
    private int cooldown = 0;
    private static final int cooldownMax = 120;
    private final EntityManager ent = EntityManager.getINSTANCE();

    public FriendlyOrb(float x, float y, float xSize, float ySize, int hp) {
        super(x, y, xSize, ySize, hp, Team.PlayerProjectiles);
        PlayerStats stats = PlayerStats.getINSTANCE();
        player = stats.getPlayer();
        shifts = false;

        // FIXME : start at player position
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        // spawn particle
        if (Game.Time() % 3 == 0)
            particles.addParticle("electricity",x + r.nextInt((int) xSize) - 8, y + r.nextInt((int) ySize) - 8,0,0,0.01f);

        // orbit player
        x += xM;
        y += yM;
        if (player.getX() + 26 > x)      xM = Math.min(xM + speed, maxSpeed);
        else                             xM = Math.max(xM - speed, -maxSpeed);
        if (player.getY() + 26 > y)      yM = Math.min(yM + speed, maxSpeed);
        else                             yM = Math.min(yM - speed, -maxSpeed);

        // shoot projectile
        if (cooldown > 0) cooldown--;

        if (cooldown == 0){
            if (!input.getButton(Controls.AttackRight) && input.getButtonCharge(Controls.AttackRight) > 0
                    || !input.getButton(Controls.AttackLeft) && input.getButtonCharge(Controls.AttackLeft) > 0){
                cooldown = cooldownMax;
                // TODO: unique projectile
                ent.spawnEntity("charged dagger",x,y);
            }
        }

    }

    @Override
    public void draw(DrawingManager spr) {
        //spr.drawGame("player0",x,y,2);
    }

    @Override
    public void onCollide(Entity other) {

    }

    @Override
    public Entity getCopy(float x, float y) {
        return new FriendlyOrb(x,y,xSize,ySize,hp);
    }

    @Override
    public void onDestroy() {

    }
}
