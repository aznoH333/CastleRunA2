package com.mygdx.game.entities.player.playerClasses;

import com.mygdx.game.Game;
import com.mygdx.game.logic.level.tileCollums.IOnPlayerStep;
import com.mygdx.game.logic.level.tileCollums.TileCollum;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.data.enums.Team;
import com.mygdx.game.logic.SoundManager;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.ui.UIManager;

import java.util.Random;

public class PlayerKnight extends Entity {
    protected float lvlY;
    private float yM = 0;
    private boolean landed = false;
    protected int actionTimer = 0;
    private int particleTimer = 0;
    protected int iFrame = 0;
    private int healthPartTimer = 0;
    private int energyPartTimer = 0;

    //finals
    private static final float gravity = 1f;
    private static final float hopStrength = 5f;
    private static final float jumpStrength = 8f;
    private static final float moveSpeed = 8f;
    private static final float scrollBorder = (float) Math.ceil((float) LevelManager.getINSTANCE().getMapWidth() / 2  - 2) * 64;
    protected static final int actionTimerFull = 16;
    private final PlayerStats inv;
    private static final int particleTimerFull = 8;
    private static final int iFrameMax = 60;
    protected final static InputManager input = InputManager.getINSTANCE();
    private final SoundManager s = SoundManager.getINSTANCE();
    private final ParticleManager particleManager = ParticleManager.getINSTANCE();
    private int energyRecharge = getEnergyRechargeTime();



    public PlayerKnight(float x, float y) {
        super(x, y, 64, 64, 3, Team.Player);
        inv = PlayerStats.getINSTANCE();

        inv.updatePlayerStats(this);
    }

    @Override
    public void update(LevelManager lvl, Random r) {
        lvlY = lvl.getLevelY(this);
        if (actionTimer > 0) actionTimer--;

        //land
        landed = y <= lvlY - yM && yM <= 0 && lvl.collidesWithLevel(this);
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
                || input.getButtonCharge(Controls.AttackLeft) > input.getHoldSensitivity() && inv.isAttackAffordable(Controls.AttackLeft,true)) {
            if (particleTimer <= 0) {
                particleTimer = particleTimerFull;
                particleManager.addParticle(
                        "sparkle",
                        (int) (Math.random() * (lvl.getTileScale() - 16)) + x,
                        (int) (Math.random() * (lvl.getTileScale() - 16)) + y - 16,
                        0,
                        -0.5f,
                        0);
            } else particleTimer--; // TODO : rework particles to have an opening and a closing
        }


        if (iFrame > 0) iFrame--;

        // map hazards
        TileCollum tile = lvl.getOnPos(x + (lvl.getTileScale() - 1));
        if (tile.getHurts() && landed && moveTo == x) {
            takeDamage(1);
        }
        if (landed && tile instanceof IOnPlayerStep && moveTo == x)
            ((IOnPlayerStep) tile).onPlayerStep();


        // scroll camera
        if (x > scrollBorder) {
            lvl.advanceToTile(x - scrollBorder);
        }

        if (moveTo > x) x += moveSpeed;
        if (moveTo < x) x -= moveSpeed;
        y += yM;

        // spawn particles
        if (healthPartTimer > 0) {
            healthPartTimer--;
            if (healthPartTimer % 6 == 0) particleManager.addParticle("healthGain", x + (r.nextFloat() * xSize) - 4, y + (r.nextFloat() * 4),0,r.nextFloat()+1f,0);
        }
        if (energyPartTimer > 0) {
            energyPartTimer--;
            if (energyPartTimer % 6 == 0) particleManager.addParticle("energyGain", x + (r.nextFloat() * xSize) - 4, y + (r.nextFloat() * 4),0,r.nextFloat()+1f,0);
        }
        // FIXME : weird collisions with world border (remove border stop for knock back?)


        // energy recharge
        if (inv.getEnergy() < inv.getMaxEnergy()) energyRecharge--;
        if (energyRecharge == 0) {
            energyRecharge = getEnergyRechargeTime();
            s.playSound("energy");
            inv.gainEnergy(1);
        }
    }

    @Override
    public void draw(DrawingManager spr) {
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
        if (other.getTeam() == Team.Enemies || other.getTeam() == Team.EnemyProjectiles) {
            takeDamage(1);
        }

    }

    @Override
    public void takeDamage(int damage) {
        if (iFrame == 0) {
            hp = inv.getHp();
            hp -= damage;
            PlayerStats.getINSTANCE().setHp(hp);
            iFrame = iFrameMax;
            yM = hopStrength;
            landed = false;
            if (moveTo > 0)
                if (moveTo == x) moveTo -= LevelManager.tileScale;
                else             moveTo -= LevelManager.tileScale*2;
        }
    }


    @Override
    public Entity getCopy(float x, float y) {
        return new PlayerKnight(x, y);
    }

    @Override
    public void onDestroy() {
        if (inv.getHp() == 0)
            UIManager.getINSTANCE().transition(GameState.GameOver);
        // TODO : death animations
        // FIXME : player can sometimes clip through the ground (probably something with level scrolling)
        // hard to replicate though

    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public void spawnHealthParticles(int time){
        healthPartTimer += time;
    }

    public void spawnEnergyParticles(int time){
        energyPartTimer += time;
    }
    // this is very janky...... anyway
    protected int getEnergyRechargeTime(){
        return 240;
    }

}