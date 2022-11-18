package com.mygdx.game.data;

public enum PlayerClass {

    // class ideas
    // mage : faster energy regen ; starting energy increased by 1| starting health decreased by 1 ; worse different starting weapon
    // midas : starts with midas sword and fortune crown (sword gives money on damage) + 1 starting health | energy attacks cost money ; doesn't have energy
    // haunted knight : +2 health; +2 energy; better sword | chase by ghosts (pseudo time limit)
    // summoner : starts with orb | -1hp -1energy

    Knight("playerKnight", 3, 1, "Sword", "player"),
    Hunter("playerHunter", 2, 2, "Small daggers", "hunter" ),
    Midas("playerMidas", 3, 0, "Midas Sword", "midas"),
    Summoner("playerSummoner", 2, 0, "Sword", "slime"), // TODO
    Haunted("playerHaunted", 5, 3, "Bone Sword", "haunted");

    public final String playerObject;
    public final int startingHp;
    public final int startingEnergy;
    public final String startingWeapon;
    public final String sprite;

    PlayerClass(String playerObject, int startingHp, int startingEnergy, String startingWeapon, String sprite){
        this.playerObject = playerObject;
        this.startingHp = startingHp;
        this.startingEnergy = startingEnergy;
        this.startingWeapon = startingWeapon;
        this.sprite = sprite;
    }
}
