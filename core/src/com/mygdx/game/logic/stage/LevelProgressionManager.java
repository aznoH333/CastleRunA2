package com.mygdx.game.logic.stage;

import com.mygdx.game.Game;
import com.mygdx.game.data.levelgeneration.EntityWeightData;
import com.mygdx.game.data.levelgeneration.TileWeightData;
import com.mygdx.game.data.tilesets.traps.BreakingPlatform;
import com.mygdx.game.data.tilesets.traps.Gap;
import com.mygdx.game.data.tilesets.traps.GhostPlatform;
import com.mygdx.game.data.tilesets.traps.SpikeTrap;
import com.mygdx.game.entities.environment.Furniture;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.level.Level;
import com.mygdx.game.logic.level.LevelManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.shops.Shop;
import com.mygdx.game.ui.UIManager;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void progressLevel(){
        currentLevelIndex++;
        switch (Game.getSeededRandom().nextInt(3)){
            default:
            case 0: lTemplate = LevelTemplate.Castle; break;
            case 1: lTemplate = LevelTemplate.Cave; break;
            case 2: lTemplate = LevelTemplate.Forest; break;
            case 3: lTemplate = LevelTemplate.CastleYard; break;
        }
        if (currentLevelIndex % 5 == 0){
            // boss level
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
            // TODO : this also kinda sucks, think of a rework
            if (currentLevelIndex > 1 && currentLevelIndex < 12 && r.nextFloat() < 0.2 + Math.min((float)currentLevelIndex/10, 0.5))        tempEntities.add(new EntityWeightData(10f, "red slime"));
            if (currentLevelIndex > 2 && currentLevelIndex < 8 && r.nextFloat() < 0.2 + Math.min((float)currentLevelIndex/10, 0.5))        tempEntities.add(new EntityWeightData(10f, "skeleton"));
            if (currentLevelIndex > 5 && currentLevelIndex < 10 && r.nextFloat() < 0.6)        tempEntities.add(new EntityWeightData(10f, "goblin"));
            // TODO : armored skeletons
            //if (currentLevelIndex > 5 && currentLevelIndex < 12 && r.nextFloat() < 0.2 + Math.min((float)currentLevelIndex/10, 0.5))        tempEntities.add(new EntityWeightData(10f, "armored skeleton"));
            if (currentLevelIndex > 7 && currentLevelIndex < 15 && r.nextFloat() < 0.8)        tempEntities.add(new EntityWeightData(10f, "purple slime"));
            if (currentLevelIndex > 8 && currentLevelIndex < 15 && r.nextFloat() < 0.6)        tempEntities.add(new EntityWeightData(10f, "rocket skeleton"));
            if (currentLevelIndex > 8 && currentLevelIndex < 15 && r.nextFloat() < 0.6)        tempEntities.add(new EntityWeightData(10f, "saw knight"));

            /* add green slime
             has 3 special cases
             1 ) always spawns on early levels
             2 ) spawns if no other enemies spawn (fallback option. should be unlikely)
             3 ) spawn rate decreases with level
            */
            if (r.nextFloat() < 0.5 - Math.min((float)currentLevelIndex/10, 0.4) || currentLevelIndex < 3 || tempEntities.size() == 2)      tempEntities.add(new EntityWeightData(10f - Math.min(currentLevelIndex, 7), "slime"));


            //print
            System.out.println("level length is " + (60 + Math.min(currentLevelIndex * 10, 120)));
            System.out.println("added empty : 30");
            for (EntityWeightData w: tempEntities) {
                System.out.println("added " + w.getEntity() + " : " + w.getWeight());
            }


            currentLevel = new Level.LevelBuilder(tempTiles.toArray(new TileWeightData[0]), 10 + Math.min(currentLevelIndex * 10, 120), 96, lTemplate.background)
                    .enemies(30f, tempEntities.toArray(new EntityWeightData[0]))
                    .height(128,96)
                    .chance(lTemplate.cChance, lTemplate.cMax, lTemplate.cMin)
                    .build();
            shop.restock(3);
            lvl.loadLevel(currentLevel);
        }
        levelDrawingOffset = (currentLevelIndex-3) * 128;

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
            if (levelDrawingOffset < (currentLevelIndex-2) * 128 && !UIManager.getINSTANCE().isTransitioning()) levelDrawingOffset+=0.1f;

            if ((i+1) % 5 == 0)
                spr.draw("map_tile1", ((Game.gameWorldWidth / 2) - 32) - levelDrawingOffset + i * 128 - 128, 512, 2);
            else
                spr.draw("map_tile0", ((Game.gameWorldWidth / 2) - 32) - levelDrawingOffset + i * 128 - 128, 512, 2);
            spr.draw("map_tile2", ((Game.gameWorldWidth / 2) - 32) - levelDrawingOffset + i * 128 - 64, 512, 2);
            if (Game.Time() % 20 >= 10 || levelDrawingOffset+1 > (currentLevelIndex-2) * 128)
                spr.draw("player0", ((Game.gameWorldWidth / 2) - 32), 600, 2);
            else
                spr.draw("player2", ((Game.gameWorldWidth / 2) - 32), 600, 2);

        }
    }
}
