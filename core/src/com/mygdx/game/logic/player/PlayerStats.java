package com.mygdx.game.logic.player;

import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.entities.player.Player;
import com.mygdx.game.logic.SoundManager;

public class PlayerStats {
    private static PlayerStats INSTANCE;
    public static PlayerStats getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new PlayerStats();
        return INSTANCE;
    }

    private Weapon currentLeftWeapon;
    private Weapon currentRightWeapon;

    private int maxHp = 3;
    private int maxEnergy = 2;
    private int hp = 3;
    private int energy = 2;
    private int coins = 0;
    private Player player;

    private static final InventoryManager inventoryManager = InventoryManager.getINSTANCE();
    private static final SoundManager sound = SoundManager.getINSTANCE();
    private PlayerStats(){
        // TODO: starting equipment
        // temporary
        equipWeapon("Nothing", Controls.AttackLeft);
        equipWeapon("Sword", Controls.AttackRight);
    }
    // equips a weapon
    public void equipWeapon(String weaponName, Controls slot){
        if (inventoryManager.isWeaponUnlocked(weaponName))
            if (slot == Controls.AttackLeft)    currentLeftWeapon = inventoryManager.getWeapon(weaponName);
            else                                currentRightWeapon = inventoryManager.getWeapon(weaponName);
    }

    public void equipWeapon(Weapon weapon, Controls slot){
        if (slot == Controls.AttackLeft)    currentLeftWeapon = weapon;
        else                                currentRightWeapon = weapon;
    }

    public boolean isAttackAffordable(Controls slot, boolean isCharged){
        if (isCharged){
            if (slot == Controls.AttackLeft)    return currentLeftWeapon.getChargedAttackCost() <= energy;
            else                                return currentRightWeapon.getChargedAttackCost() <= energy;
        }else{
            if (slot == Controls.AttackLeft)    return currentLeftWeapon.getAttackCost() <= energy;
            else                                return currentRightWeapon.getAttackCost() <= energy;
        }
    }

    public void updatePlayerStats(Player player){
        this.player = player;
        player.setHp(hp);
    }

    public Player getPlayer(){
        return player;
    }

    public void useWeapon(float x, float y, Controls slot){
        Weapon w;
        if (slot == Controls.AttackLeft)    w = currentLeftWeapon;
        else                                w = currentRightWeapon;

        if (w.getAttackCost() <= energy){
            w.attack(x,y);
            if (w.getUseSound() != null) sound.playSound(w.getUseSound());
            energy -= w.getAttackCost();
        }
    }

    public void useChargedWeapon(float x, float y, Controls slot){
        Weapon w;
        if (slot == Controls.AttackLeft)    w = currentLeftWeapon;
        else                                w = currentRightWeapon;

        if (w.getChargedAttackCost() <= energy){
            w.chargedAttack(x,y);
            if (w.getChargedUseSound() != null) sound.playSound(w.getChargedUseSound());
            energy -= w.getChargedAttackCost();
        }
    }

    public void restoreStats(){
        hp = maxHp;
        energy = maxEnergy;
    }

    public void resetStats(){
        maxHp = 3;
        maxEnergy = 2;
        hp = 3;
        energy = 2;
        coins = 0;
        equipWeapon("Sword", Controls.AttackRight);
        equipWeapon("Nothing", Controls.AttackLeft);
    }

    public void upgradeHp(){
        maxHp++;
        hp++;
    }

    public void upgradeEnergy(){
        maxEnergy++;
        energy++;
    }


    public Weapon getLeftWeapon(){
        return currentLeftWeapon;
    }

    public Weapon getRightWeapon(){
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
        this.coins += coins;
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) hp = maxHp;
        // TODO : some heal particle
    }
}
