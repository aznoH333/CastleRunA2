package com.mygdx.game.logic.entities.abstracts;

import com.mygdx.game.data.enums.Team;
import com.mygdx.game.items.interfaces.IStatusEffect;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.ItemManager;

import java.util.ArrayList;
import java.util.Random;


public abstract class Projectile extends Entity{

    private final ArrayList<IStatusEffect> effects = new ArrayList<>();
    private final int pierceTimerMax;
    private int pierceTimer = 0;

    public Projectile(float x, float y, float xSize, float ySize, int hp, Team team, int pierceTimerMax) {
        super(x, y, xSize, ySize, hp, team);
        this.pierceTimerMax = pierceTimerMax;
        ItemManager.getINSTANCE().onAttack(this);
    }

    public void addStatusEffect(IStatusEffect effect){
        effects.add(effect);
    }


    protected abstract void onEnemyHit(Enemy other);

    protected abstract void projectileUpdate(LevelManager lvl, Random r);

    @Override
    public void update(LevelManager lvl, Random r) {
        if (pierceTimer > 0) pierceTimer--;
        projectileUpdate(lvl, r);
    }

    @Override
    public void onCollide(Entity other) {
        if (other.getTeam() == Team.Enemies && pierceTimer == 0 && other instanceof Enemy){
            Enemy e = (Enemy) other;
            // apply status effect
            for (IStatusEffect effect: effects) {
                e.applyEffect(effect);

            }
            onEnemyHit(e);
            if (pierceTimerMax == 0) destroy();
            pierceTimer = pierceTimerMax;

        }
    }
}
