package com.mygdx.game.data.load;

import com.mygdx.game.entities.enemies.ArmoredSkeleton;
import com.mygdx.game.entities.enemies.GhostSkull;
import com.mygdx.game.entities.enemies.Goblin;
import com.mygdx.game.entities.enemies.RocketSkeleton;
import com.mygdx.game.entities.enemies.SawKnight;
import com.mygdx.game.entities.enemies.Skeleton;
import com.mygdx.game.entities.enemies.bosses.mechboss.MechBoss;
import com.mygdx.game.entities.enemies.bosses.mechboss.MechBossSpawner;
import com.mygdx.game.entities.enemies.bosses.mechboss.RocketProjectile;
import com.mygdx.game.entities.enemies.bosses.mechboss.SawbladeProjectile;
import com.mygdx.game.entities.enemies.bosses.slimeboss.SlimeBoss;
import com.mygdx.game.entities.enemies.bosses.slimeboss.SlimeBossDeathAnimation;
import com.mygdx.game.entities.enemies.frogs.BlueFrog;
import com.mygdx.game.entities.enemies.frogs.GreenFrog;
import com.mygdx.game.entities.enemies.frogs.RedFrog;
import com.mygdx.game.entities.enemies.projectiles.Bone;
import com.mygdx.game.entities.enemies.slimes.PurpleSlime;
import com.mygdx.game.entities.enemies.slimes.RedSlime;
import com.mygdx.game.entities.enemies.slimes.Slime;
import com.mygdx.game.entities.environment.Chest;
import com.mygdx.game.entities.environment.Coin;
import com.mygdx.game.entities.environment.EnergyPickup;
import com.mygdx.game.entities.environment.ExitDoor;
import com.mygdx.game.entities.environment.Furniture;
import com.mygdx.game.entities.environment.HealthPotion;
import com.mygdx.game.entities.environment.PickedCoin;
import com.mygdx.game.entities.player.Projectiles.BombProjectile;
import com.mygdx.game.entities.player.Projectiles.BubbleProjectile;
import com.mygdx.game.entities.player.Projectiles.OrbAttack;
import com.mygdx.game.entities.player.Projectiles.RockProjectile;
import com.mygdx.game.entities.player.Projectiles.axe.Axe;
import com.mygdx.game.entities.player.Projectiles.axe.ChargedAxe;
import com.mygdx.game.entities.player.Projectiles.cross.ChargedCross;
import com.mygdx.game.entities.player.Projectiles.cross.Cross;
import com.mygdx.game.entities.player.Projectiles.dagger.ChargedDagger;
import com.mygdx.game.entities.player.Projectiles.dagger.Dagger;
import com.mygdx.game.entities.player.Projectiles.fireball.FireBallProjectile;
import com.mygdx.game.entities.player.Projectiles.fireball.Meteorite;
import com.mygdx.game.entities.player.Projectiles.sword.ChargedSwordSwipe;
import com.mygdx.game.entities.player.Projectiles.sword.SwordSwipe;
import com.mygdx.game.entities.player.itemEntities.Explosion;
import com.mygdx.game.entities.player.itemEntities.FriendlyOrb;
import com.mygdx.game.entities.player.itemEntities.MicroBomb;
import com.mygdx.game.entities.player.itemEntities.MicroExplosion;
import com.mygdx.game.entities.player.playerClasses.PlayerHaunted;
import com.mygdx.game.entities.player.playerClasses.PlayerHunter;
import com.mygdx.game.entities.player.playerClasses.PlayerKnight;
import com.mygdx.game.entities.player.playerClasses.PlayerMidas;
import com.mygdx.game.logic.entities.abstracts.Entity;

import java.util.HashMap;

public class EntityLoadList {
    public static HashMap<String,Entity> loadEntities(){
        HashMap<String, Entity> entities = new HashMap<>();

        //add enemies
        entities.put("slime", new Slime(0,0,64,64,1));
        entities.put("red slime", new RedSlime(0,0,64,64,2));
        entities.put("purple slime", new PurpleSlime(0,0));
        entities.put("skeleton", new Skeleton(0,0,64,64,1));
        entities.put("armored skeleton", new ArmoredSkeleton(0,0));
        entities.put("ghost skull", new GhostSkull(0,0));
        entities.put("goblin", new Goblin(0,0));
        entities.put("rocket skeleton", new RocketSkeleton(0,0));
        entities.put("saw knight", new SawKnight(0,0));

        // add frogs
        entities.put("green frog", new GreenFrog(0,0));
        entities.put("red frog", new RedFrog(0,0));
        entities.put("blue frog", new BlueFrog(0,0));

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
        entities.put("sword swipe", new SwordSwipe(0,0));
        entities.put("charged sword swipe", new ChargedSwordSwipe(0,0));
        entities.put("dagger", new Dagger(0,0,48,32, 1));
        entities.put("charged dagger", new ChargedDagger(0,0,48,32, 1));
        entities.put("throwing axe", new Axe(0,0,48,32, 1));
        entities.put("charged throwing axe", new ChargedAxe(0,0,48,32, 1));
        entities.put("cross", new Cross(0,0));
        entities.put("charged cross", new ChargedCross(0,0));
        entities.put("orb attack", new OrbAttack(0,0,16,16,1));
        entities.put("fireball", new FireBallProjectile(0, 0));
        entities.put("meteor", new Meteorite(0,0));
        entities.put("explosion", new Explosion(0,0));
        entities.put("bubble", new BubbleProjectile(0,0));
        entities.put("bomb", new BombProjectile(0,0));
        entities.put("rock", new RockProjectile(0,0));

        // FIXME normalize naming conventions
        // pickups
        entities.put("energy pickup", new EnergyPickup(0,0,32,32,1));
        entities.put("coin pickup", new Coin(0,0));
        entities.put("picked coin", new PickedCoin(0,0,32,32,1));
        entities.put("health potion", new HealthPotion(0,0,48,48,1));


        //item entities
        entities.put("friendlyOrb", new FriendlyOrb(0,0));
        entities.put("micro bomb", new MicroBomb(0,0));
        entities.put("micro explosion", new MicroExplosion(0,0));


        // environment
        entities.put("chest", new Chest(0,0,64,64,1));
        entities.put("exit door", new ExitDoor(0,0,64,64,1));
        entities.put("furniture", new Furniture(0,0));


        // players
        entities.put("playerKnight", new PlayerKnight(0,0));
        entities.put("playerHunter", new PlayerHunter(0,0));
        entities.put("playerMidas", new PlayerMidas(0,0));
        entities.put("playerHaunted", new PlayerHaunted(0,0));


        //return
        return entities;
    }
}
