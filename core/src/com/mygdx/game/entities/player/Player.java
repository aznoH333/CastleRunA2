package com.mygdx.game.entities.player;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.player.PlayerStats;

import java.util.Random;

public class Player extends Entity {
    private float lvlY;
    private float yM = 0;
    private boolean landed = false;
    private int actionTimer = 0;
    private int particleTimer = 0;
    private int iFrame = 0;

    //finals
    private static final float gravity = 1f;
    private static final float hopStrength = 5f;
    private static final float jumpStrength = 8f;
    private static final float moveSpeed = 8f;
    private static final float scrollBorder = 256;
    private static final int actionTimerFull = 16;
    private final PlayerStats inv;
    private static final int particleTimerFull = 8;
    private static final int iFrameMax = 60;
    private final static InputManager input = InputManager.getINSTANCE();
    private final SoundManager s = SoundManager.getINSTANCE();

    public Player(float x, float y, float sizeX, float sizeY) {
        super(x, y, sizeX, sizeY, 3, Team.Player);
        inv = PlayerStats.getINSTANCE();

        hp = inv.getMaxHp();
        inv.setHp(hp);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        lvlY = lvl.getOnPos(x + (lvl.getTileScale() - 1)).getY() + lvl.getTileScale();
        if (actionTimer > 0) actionTimer--;

        //land
        landed = y <= lvlY - yM && yM <= 0;
        if (landed) {
            yM = 0;
            y = lvlY;
        } else {
            yM -= gravity;
        }

        // execute movement
        // jump right

        if (!input.getButton(Controls.MoveRight) && y == lvlY && input.getButtonCharge(Controls.MoveRight) > 0) {
            // hop
            if (input.getButtonCharge(Controls.MoveRight) < input.getHoldSensitivity()) {
                yM = hopStrength;
                moveTo = x + lvl.getTileScale();
                s.playSound("hop");
            } else {
                yM = jumpStrength;
                moveTo = x + lvl.getTileScale() * 2;
                s.playSound("jump");
            }
            if (moveTo > lvl.getLevelLength() - lvl.getDistance() + (lvl.getMapWidth()-2) * lvl.getTileScale()) moveTo = (lvl.getMapWidth()-2) * lvl.getTileScale();
        }
        // jump left
        if (!input.getButton(Controls.MoveLeft) && y == lvlY && input.getButtonCharge(Controls.MoveLeft) > 0) {
            // hop
            if (input.getButtonCharge(Controls.MoveLeft) < input.getHoldSensitivity()) {
                yM = hopStrength;
                moveTo = x - lvl.getTileScale();
                s.playSound("hop");
            } else {
                yM = jumpStrength;
                moveTo = x - lvl.getTileScale() * 2;
                s.playSound("jump");
            }
            //prevent player moving out off bounds
            if (moveTo < 0) moveTo = 0;
        }

        // attacks
        // TODO: sprite offsets || a workaround

        if (!input.getButton(Controls.AttackRight) && actionTimer == 0 && input.getButtonCharge(Controls.AttackRight) > 0 && landed) {
            if (input.getButtonCharge(Controls.AttackRight) < input.getHoldSensitivity()) {
                inv.useWeapon(x, y, Controls.AttackRight);
                if (inv.isAttackAffordable(Controls.AttackRight, false))
                    actionTimer = actionTimerFull;
            } else {
                inv.useChargedWeapon(x, y, Controls.AttackRight);
                if (inv.isAttackAffordable(Controls.AttackRight, true))
                    actionTimer = actionTimerFull;
            }
        }

        if (!input.getButton(Controls.AttackLeft) && actionTimer == 0 && input.getButtonCharge(Controls.AttackLeft) > 0 && landed) {


            if (input.getButtonCharge(Controls.AttackLeft) < input.getHoldSensitivity()) {
                inv.useWeapon(x, y, Controls.AttackLeft);
                if (inv.isAttackAffordable(Controls.AttackLeft, false))
                    actionTimer = actionTimerFull;
            } else {
                inv.useChargedWeapon(x, y, Controls.AttackLeft);
                if (inv.isAttackAffordable(Controls.AttackLeft, true))
                    actionTimer = actionTimerFull;
            }

        }


        // spawn particle
        if (input.getButtonCharge(Controls.AttackRight) > input.getHoldSensitivity() && inv.isAttackAffordable(Controls.AttackRight,true)
                || input.getButtonCharge(Controls.AttackLeft) > input.getHoldSensitivity() && inv.isAttackAffordable(Controls.AttackRight,true)) {
            if (particleTimer <= 0) {
                particleTimer = particleTimerFull;
                ParticleManager.getINSTANCE().addParticle(
                        "sparkle",
                        (int) (Math.random() * (lvl.getTileScale() - 16)) + x,
                        (int) (Math.random() * (lvl.getTileScale() - 16)) + y - 16,
                        0,
                        -0.5f,
                        0,
                        (int) (Math.random() * 20 + 10));
            } else particleTimer--;
        }
        if (iFrame > 0) iFrame--;

        // map hazards
        if (lvl.getOnPos(x + (lvl.getTileScale() - 1)).getHurts() && landed) takeDamage(1);

        // scroll camera
        if (x > scrollBorder) {
            lvl.advanceToTile(x - scrollBorder);
        }

        if (moveTo > x) x += moveSpeed;
        if (moveTo < x) x -= moveSpeed;
        y += yM;
    }

    @Override
    public void draw(SpriteManager spr) {
        // player rendering
        if (!(iFrame > 0 && (Game.Time()>>2)%2 == 0))
            if ((input.getButtonCharge(Controls.MoveLeft) > input.getHoldSensitivity() || input.getButtonCharge(Controls.MoveRight) > input.getHoldSensitivity()) && y == lvlY)
                spr.drawGame("player1", x, y,2);
            else if (y != lvlY)
                spr.drawGame("player2", x, y,2);
            else if (actionTimer > actionTimerFull / 2)
                spr.drawGame("player3", x, y,2);
            else if (actionTimer > 0)
                spr.drawGame("player4", x, y,2);
            else
                spr.drawGame("player0", x, y,2);

    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.Enemies) {
            takeDamage(1);
        }

    }

    @Override
    public void takeDamage(int damage) {
        if (iFrame == 0) {
            hp -= damage;
            PlayerStats.getINSTANCE().setHp(hp);
            iFrame = iFrameMax;
            yM = hopStrength;
            if (moveTo > 0)
                if (moveTo == x) moveTo -= LevelManager.tileScale;
                else             moveTo -= LevelManager.tileScale*2;
        }
    }

    @Override
    public Entity getCopy(float x, float y) {
        return null;
    }

    @Override
    public void onDestroy() {

        // TODO : death animations
        // FIXME : player can sometimes clip through the ground (probably something with level scrolling)
        // hard to replicate though

    }


}
