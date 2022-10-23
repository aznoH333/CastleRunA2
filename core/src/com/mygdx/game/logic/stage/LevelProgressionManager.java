package com.mygdx.game.logic.stage;

import com.mygdx.game.Game;
import com.mygdx.game.data.levelgeneration.EntityWeightData;
import com.mygdx.game.data.levelgeneration.TileWeightData;
import com.mygdx.game.data.tilesets.traps.BreakingPlatform;
import com.mygdx.game.data.tilesets.traps.Gap;
import com.mygdx.game.data.tilesets.traps.GhostPlatform;
import com.mygdx.game.data.tilesets.traps.SpikeTrap;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityFactory;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.level.EntityDistributionObject;
import com.mygdx.game.logic.level.Level;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.level.levelModifiers.ILevelModifier;
import com.mygdx.game.logic.level.levelModifiers.ModifierBoneZone;
import com.mygdx.game.logic.level.levelModifiers.ModifierSlimeRain;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.shops.Shop;
import com.mygdx.game.ui.UIManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class LevelProgressionManager {

    private static LevelProgressionManager INSTANCE;
    public static LevelProgressionManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new LevelProgressionManager();
        return INSTANCE;
    }

    int currentLevelIndex = 0;
    private final static int lastLevel = 15;
    Level currentLevel;
    LevelTemplate lTemplate = LevelTemplate.Castle;
    private final static Random r = Game.getSeededRandom();
    private final static LevelManager lvl = LevelManager.getINSTANCE();
    private final static Shop shop = Shop.getINSTANCE();

    public void startLevel(){

    }

    private final static EntityDistributionObject[] possibleEntities = {
            new EntityDistributionObject("slime", -5, 6, 30f),
            new EntityDistributionObject("red slime", 2, 12, 20f),
            new EntityDistributionObject("skeleton", 3, 7, 14.5f),
            new EntityDistributionObject("armored skeleton", 6, 14, 14.5f),
            new EntityDistributionObject("goblin", 5, 10, 30f),
            new EntityDistributionObject("purple slime", 9, 20, 29f),
            new EntityDistributionObject("rocket skeleton", 10, 20, 25f),
            new EntityDistributionObject("saw knight", 11, 20, 20f),

    };

    public void progressLevel(){
        currentLevelIndex++;
        switch (Game.getSeededRandom().nextInt(4)){
            default:
            case 0: lTemplate = LevelTemplate.Castle; break;
            case 1: lTemplate = LevelTemplate.Cave; break;
            case 2: lTemplate = LevelTemplate.Forest; break;
            case 3: lTemplate = LevelTemplate.CastleYard; break;
        }
        if (currentLevelIndex % 5 == 0){

            String currentBoss;

            switch (currentLevelIndex){
                case 5:  currentBoss = "slime boss"; break;
                case 10: currentBoss = "mech boss"; break;
                default:
                case 15: currentBoss = "slime"; break;
            }

            currentLevel = new Level.LevelBuilder(
                    lTemplate.defaultTiles,
                    20,96, lTemplate.background)
                    .setBoss(currentBoss)
                    .buildBossLevel();

        }else{
            // regular level
            // add tiles
            ArrayList<TileWeightData> tempTiles = new ArrayList<>(Arrays.asList(lTemplate.defaultTiles));

            // add traps
            // TODO : this is kinda dumb. think of a rework
            tempTiles.add(new TileWeightData(5f, new Gap()));
            if (currentLevelIndex >= 1)     tempTiles.add(new TileWeightData(2f, new SpikeTrap()));
            if (currentLevelIndex >= 2)     tempTiles.add(new TileWeightData(2f, new BreakingPlatform()));
            if (currentLevelIndex >= 4)     tempTiles.add(new TileWeightData(2f, new GhostPlatform()));

            // add entities
            ArrayList<EntityWeightData> tempEntities = new ArrayList<>();
            // always occurring entities
            tempEntities.add(new EntityWeightData(r.nextFloat() * 10 + 5, "furniture"));
            tempEntities.add(new EntityWeightData(r.nextFloat() * Math.min(currentLevelIndex, 5) + 2, "chest"));


            // enemies
            float maxChance = 0;
            for (EntityDistributionObject o: possibleEntities) {
                maxChance += o.getDistributionForLevel(currentLevelIndex);
            }

            do {
                float number = r.nextFloat() * maxChance;
                float dumb2 = 0;
                for (EntityDistributionObject o: possibleEntities) {
                    if (o.getDistributionForLevel(currentLevelIndex) + dumb2 > number){
                        tempEntities.add(new EntityWeightData(o.getDistributionForLevel(currentLevelIndex), o.getEntity()));
                        break;
                    }
                    dumb2 += o.getDistributionForLevel(currentLevelIndex);
                }

            }while ((r.nextBoolean() || tempEntities.size() < 4) && tempEntities.size() >= 6);

            //print
            System.out.println("level length is " + (60 + Math.min(currentLevelIndex * 10, 120)));
            System.out.println("added empty : 30");
            for (EntityWeightData w: tempEntities) {
                System.out.println("added " + w.getEntity() + " : " + w.getWeight());
            }


            //add level modifier
            // FIXME : tohle je garbage implementace
            ILevelModifier mod = null;
            if (currentLevelIndex >= 4){
                if (r.nextFloat() > 0.25f){
                    float rng = r.nextFloat();
                    if (rng < 0.5)  mod = new ModifierBoneZone();
                    else            mod = new ModifierSlimeRain();
                }
            }

            currentLevel = new Level.LevelBuilder(tempTiles.toArray(new TileWeightData[0]), 10 + Math.min(currentLevelIndex * 10, 120), 96, lTemplate.background)
                    .enemies(30f, tempEntities.toArray(new EntityWeightData[0]))
                    .height(128,96)
                    .chance(lTemplate.cChance, lTemplate.cMax, lTemplate.cMin)
                    .setModifier(mod)
                    .build();
            shop.restock(3);
        }
        levelDrawingOffset = (currentLevelIndex-3) * 128;
        lvl.loadLevel(currentLevel);



    }

    public void resetProgress(){
        currentLevelIndex = 0;
        levelDrawingOffset = 0;
    }

    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    private static final PlayerStats ps = PlayerStats.getINSTANCE();
    private float levelDrawingOffset = 0;
    public void drawLevelProgressUI(){
        for (int i = 0; i < lastLevel; i++){
            if (levelDrawingOffset < (currentLevelIndex-2) * 128 && UIManager.getINSTANCE().isTransitioning()) levelDrawingOffset+=0.1f;

            if (i == 0)
                spr.draw("map_tile3", ((Game.gameWorldWidth / 2) - 32) - levelDrawingOffset - 128, 512, 2);
            else if ((i+1) % 5 == 0)
                spr.draw("map_tile1", ((Game.gameWorldWidth / 2) - 32) - levelDrawingOffset + i * 128 - 128, 512, 2);
            else
                spr.draw("map_tile0", ((Game.gameWorldWidth / 2) - 32) - levelDrawingOffset + i * 128 - 128, 512, 2);
            spr.draw("map_tile2", ((Game.gameWorldWidth / 2) - 32) - levelDrawingOffset + i * 128 - 64, 512, 2);
            if (Game.Time() % 20 >= 10 || levelDrawingOffset+1 > (currentLevelIndex-2) * 128)
                spr.draw(ps.getPlayerClass().sprite + "0", ((Game.gameWorldWidth / 2) - 64), 600, 2, false, 2);
            else
                spr.draw(ps.getPlayerClass().sprite + "2", ((Game.gameWorldWidth / 2) - 64), 600, 2, false, 2);

        }
    }
}
