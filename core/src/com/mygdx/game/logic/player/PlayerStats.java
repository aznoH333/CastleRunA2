package com.mygdx.game.logic.player;

import com.mygdx.game.Game;
import com.mygdx.game.data.PlayerClass;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.entities.player.playerClasses.PlayerKnight;
import com.mygdx.game.logic.SoundManager;

import java.util.Random;

public class PlayerStats {
    private static PlayerStats INSTANCE;

    public static PlayerStats getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new PlayerStats();
        return INSTANCE;
    }

    private Weapon currentLeftWeapon;
    private Weapon currentRightWeapon;

    private int maxHp = 3;
    private int maxEnergy = 1;
    private int hp = 3;
    private int energy = 1;
    private int coins = 0;
    private PlayerKnight playerKnight;

    private int coinCounter = 0;
    private final Random r = Game.getGeneralRandom();
    private PlayerClass pc = PlayerClass.Knight;

    private final InventoryManager inventoryManager;
    private static final SoundManager sound = SoundManager.getINSTANCE();
    private static final ProgressManager prgrs = ProgressManager.getINSTANCE();

    private PlayerStats() {
        // temporary
        inventoryManager = InventoryManager.getINSTANCE();


    }

    // equips a weapon
    public void equipWeapon(String weaponName, Controls slot) {
        if (inventoryManager.isWeaponUnlocked(weaponName))
            if (slot == Controls.AttackLeft)
                currentLeftWeapon = inventoryManager.getWeapon(weaponName);
            else currentRightWeapon = inventoryManager.getWeapon(weaponName);
    }

    public void equipWeapon(Weapon weapon, Controls slot) {
        if (slot == Controls.AttackLeft) currentLeftWeapon = weapon;
        else currentRightWeapon = weapon;
    }

    public boolean isAttackAffordable(Controls slot, boolean isCharged) {
        if (pc == PlayerClass.Midas){
            if (isCharged) {
                if (slot == Controls.AttackLeft)
                    return currentLeftWeapon.getChargedAttackCost()*5 <= coins;
                else return currentRightWeapon.getChargedAttackCost()*5 <= coins;
            } else {
                if (slot == Controls.AttackLeft) return currentLeftWeapon.getAttackCost()*5 <= coins;
                else return currentRightWeapon.getAttackCost()*5 <= coins;
            }
        }

        if (isCharged) {
            if (slot == Controls.AttackLeft)
                return currentLeftWeapon.getChargedAttackCost() <= energy;
            else return currentRightWeapon.getChargedAttackCost() <= energy;
        } else {
            if (slot == Controls.AttackLeft) return currentLeftWeapon.getAttackCost() <= energy;
            else return currentRightWeapon.getAttackCost() <= energy;
        }
    }

    public void updatePlayerStats(PlayerKnight playerKnight) {
        this.playerKnight = playerKnight;
        playerKnight.setHp(hp);
    }

    public void update() {
        if (coinCounter > 0) {
            sound.playSound("coin");
            coinCounter--;
        }
    }

    public PlayerKnight getPlayer() {
        return playerKnight;
    }

    public void useWeapon(float x, float y, Controls slot) {
        Weapon w;
        if (slot == Controls.AttackLeft) w = currentLeftWeapon;
        else w = currentRightWeapon;

        if (w.getAttackCost() <= energy) {
            w.attack(x, y);
            if (w.getUseSound() != null) sound.playSound(w.getUseSound());
            energy -= w.getAttackCost();
        } else if (pc == PlayerClass.Midas && coins >= w.getAttackCost() * 5) {
            w.attack(x, y);
            if (w.getUseSound() != null) sound.playSound(w.getUseSound());
            coins -= w.getAttackCost() * 5;
        }
    }

    public void useChargedWeapon(float x, float y, Controls slot) {
        Weapon w;
        if (slot == Controls.AttackLeft) w = currentLeftWeapon;
        else w = currentRightWeapon;

        if (w.getChargedAttackCost() <= energy) {
            w.chargedAttack(x, y);
            if (w.getChargedUseSound() != null) sound.playSound(w.getChargedUseSound());
            energy -= w.getChargedAttackCost();
        } else if (pc == PlayerClass.Midas && coins >= w.getChargedAttackCost() * 5) {
            w.chargedAttack(x, y);
            if (w.getUseSound() != null) sound.playSound(w.getUseSound());
            coins -= w.getChargedAttackCost() * 5;
        }
    }

    public void restoreStats() {
        hp = maxHp;
        energy = maxEnergy;
        coinCounter = 0;
    }

    public void resetStats() {
        maxHp = pc.startingHp + (int) prgrs.getBonus("Health");
        maxEnergy = pc.startingEnergy + (int) prgrs.getBonus("Energy");
        hp = maxHp;
        energy = maxEnergy;
        coins = 0;
        coinCounter = 0;

        inventoryManager.unlockStartingWeapon(pc.startingWeapon);
        equipWeapon("Nothing", Controls.AttackLeft);
        equipWeapon(pc.startingWeapon, Controls.AttackRight);
    }

    public void upgradeHp() {
        maxHp++;
        hp++;
    }

    public void upgradeEnergy() {
        maxEnergy++;
        energy++;
    }


    public Weapon getLeftWeapon() {
        return currentLeftWeapon;
    }

    public Weapon getRightWeapon() {
        return currentRightWeapon;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getHp() {
        return hp;
    }

    public int getEnergy() {
        return energy;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);

    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int coins) {
        if (prgrs.getBonus("Double coin chance") > r.nextFloat()) {
            this.coins++;
        }
        this.coins += coins;
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) hp = maxHp;
        playerKnight.spawnHealthParticles(amount * 20);
    }

    public void gainEnergy(int amount) {
        energy += amount;
        if (energy > maxEnergy) energy = maxEnergy;
        playerKnight.spawnEnergyParticles(amount * 20);
    }

    public void gainCoin() {
        addCoins(1);
        coinCounter++;
    }

    public PlayerClass getPlayerClass() {
        return pc;
    }

    public void setPlayerClass(PlayerClass pc) {
        this.pc = pc;
    }
}
