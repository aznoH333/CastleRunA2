package com.mygdx.game.data.load;

import com.mygdx.game.entities.enemies.*;
import com.mygdx.game.entities.enemies.bosses.mechboss.MechBoss;
import com.mygdx.game.entities.enemies.bosses.mechboss.MechBossSpawner;
import com.mygdx.game.entities.enemies.bosses.mechboss.RocketProjectile;
import com.mygdx.game.entities.enemies.bosses.mechboss.SawbladeProjectile;
import com.mygdx.game.entities.enemies.bosses.slimeboss.SlimeBoss;
import com.mygdx.game.entities.enemies.bosses.slimeboss.SlimeBossDeathAnimation;
import com.mygdx.game.entities.enemies.projectiles.*;
import com.mygdx.game.entities.environment.*;
import com.mygdx.game.entities.player.Projectiles.*;
import com.mygdx.game.entities.player.itemEntities.Explosion;
import com.mygdx.game.entities.player.itemEntities.FriendlyOrb;
import com.mygdx.game.entities.player.itemEntities.MicroBomb;
import com.mygdx.game.entities.player.itemEntities.MicroExplosion;
import com.mygdx.game.logic.entities.abstracts.Entity;

import java.util.HashMap;

public class EntityLoadList {
    public static HashMap<String,Entity> loadEntities(){
        HashMap<String, Entity> entities = new HashMap<>();

        //add enemies
        entities.put("slime", new Slime(0,0,64,64,1));
        entities.put("red slime", new RedSlime(0,0,64,64,2));
        entities.put("skeleton", new Skeleton(0,0,64,64,1));
        entities.put("ghost skull", new GhostSkull(0,0));
        entities.put("goblin", new Goblin(0,0));
        entities.put("rocket skeleton", new RocketSkeleton(0,0));
        entities.put("saw knight", new SawKnight(0,0));

        // bosses
        entities.put("slime boss", new SlimeBoss(0,0,128,128,30));
        entities.put("slime boss death", new SlimeBossDeathAnimation(0,0,128,128,1));

        entities.put("mech boss", new MechBossSpawner(0,0));
        entities.put("mech boss actual", new MechBoss(0,0));


        // add enemy projectiles
        entities.put("bone", new Bone(0,0,64,64,1));
        entities.put("sawblade", new SawbladeProjectile(0,0));
        entities.put("rocket", new RocketProjectile(0,0));


        //add projectiles
        entities.put("sword swipe", new SwordSwipe(0,0,48,64, 1));
        entities.put("charged sword swipe", new ChargedSwordSwipe(0,0,48,64, 1));
        entities.put("dagger", new Dagger(0,0,48,32, 1));
        entities.put("charged dagger", new ChargedDagger(0,0,48,32, 1));
        entities.put("throwing axe", new Axe(0,0,48,32, 1));
        entities.put("charged throwing axe", new ChargedAxe(0,0,48,32, 1));
        entities.put("cross", new Cross(0,0,48,48,1));
        entities.put("charged cross", new ChargedCross(0,0,48,48,1));
        entities.put("orb attack", new OrbAttack(0,0,16,16,1));
        entities.put("fireball", new FireBall(0, 0));
        entities.put("meteor", new Meteorite(0,0));
        entities.put("explosion", new Explosion(0,0));
        entities.put("bubble", new BubbleProjectile(0,0));
        entities.put("bomb", new BombProjectile(0,0));
        entities.put("rock", new RockProjectile(0,0));


        // FIXME normalize naming conventions
        // pickups
        entities.put("energy pickup", new EnergyPickup(0,0,32,32,1));
        entities.put("coin pickup", new Coin(0,0,32,32,1));
        entities.put("picked coin", new PickedCoin(0,0,32,32,1));
        entities.put("health potion", new HealthPotion(0,0,48,48,1));


        //item entities
        entities.put("friendlyOrb", new FriendlyOrb(0,0,16,16,1));
        entities.put("micro bomb", new MicroBomb(0,0));
        entities.put("micro explosion", new MicroExplosion(0,0));


        // environment
        entities.put("chest", new Chest(0,0,64,64,1));
        entities.put("exit door", new ExitDoor(0,0,64,64,1));
        entities.put("furniture", new Furniture(0,0));

        //return
        return entities;
    }
}
