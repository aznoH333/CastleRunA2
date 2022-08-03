package com.mygdx.game.logic.level;

import com.mygdx.game.data.levelgeneration.EntityWeightData;
import com.mygdx.game.data.levelgeneration.TileWeightData;
import com.mygdx.game.data.tilesets.forest.ForestRegular2;
import com.mygdx.game.data.tilesets.forest.ForestRegular3;
import com.mygdx.game.logic.level.tileCollums.TileCollum;
import com.mygdx.game.data.tilesets.cave.CaveRegular;
import com.mygdx.game.data.tilesets.forest.ForestRegular;
import com.mygdx.game.data.tilesets.traps.BreakingPlatform;
import com.mygdx.game.data.tilesets.traps.GhostPlatform;
import com.mygdx.game.data.tilesets.traps.Gap;
import com.mygdx.game.data.tilesets.castle.CastleRegular;
import com.mygdx.game.data.tilesets.traps.SpikeTrap;

import java.util.HashMap;


public class LevelOwner {
    private static LevelOwner INSTANCE;

    public static LevelOwner getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new LevelOwner();
        return INSTANCE;
    }

    private final HashMap<String, Level> levels = new HashMap<>();
    private static final Backgrounds backgrounds = Backgrounds.getINSTANCE();


    public LevelOwner() {
        // 100 tiles = good length for a regular level

        levels.put("1-1", new Level.LevelBuilder(
                        new TileWeightData[]{
                                new TileWeightData(20f, new CastleRegular()),
                                new TileWeightData(5, new Gap()),
                                new TileWeightData(2, new SpikeTrap()),
                                new TileWeightData(1, new BreakingPlatform()),
                        },
                        75, 96,
                        backgrounds.castle())
                .mapTiles("mIconCastle0", "mTile0")
                .height(128,96)
                .chance(0.7f,3,2)
                .enemies(10, new EntityWeightData[]{
                        new EntityWeightData(6, "slime"),
                        new EntityWeightData(20, "goblin"),
                        new EntityWeightData(1.5f, "chest"),
                        new EntityWeightData(10, "furniture")})
                .build()
        );

        levels.put("1-2", new Level.LevelBuilder(
                new TileWeightData[]{
                        new TileWeightData(15, new CaveRegular()),
                        new TileWeightData(5, new Gap()),
                        new TileWeightData(1, new BreakingPlatform()),
                },
                85, 128,
                backgrounds.cave())
                .mapTiles("mIconCave0", "mTile0")
                .enemies(15, new EntityWeightData[]{
                        new EntityWeightData(5, "slime"),
                        new EntityWeightData(1, "chest"),
                        new EntityWeightData(3, "red slime"),
                        new EntityWeightData(5, "furniture")})

            .build()
        );

        levels.put("1-3", new Level.LevelBuilder(
                new TileWeightData[]{
                        new TileWeightData(20f, new CastleRegular()),
                        new TileWeightData(5, new Gap()),
                        new TileWeightData(2, new SpikeTrap()),
                        new TileWeightData(3, new GhostPlatform()),
                },
                80,128,
                backgrounds.castle())
                .enemies(10, new EntityWeightData[]{
                        new EntityWeightData(2, "slime"),
                        new EntityWeightData(1, "chest"),
                        new EntityWeightData(2, "red slime"),
                        new EntityWeightData(5, "skeleton"),
                        new EntityWeightData(10, "furniture")})
                .chance(0.9f, 3, 1)
                .height(128, 96)
                .mapTiles("mIconCastle1", "mTile0")
                .build());

        levels.put("1-4", new Level.LevelBuilder(
                new TileWeightData[]{
                        new TileWeightData(15, new CaveRegular()),
                },
                20,96, backgrounds.cave())
                .mapTiles("mIconCave1", "mTile0")
                .setBoss("slime boss")
                .buildBossLevel());

        levels.put("2-1", new Level.LevelBuilder(
                new TileWeightData[]{
                        new TileWeightData(10, new ForestRegular()),
                        new TileWeightData(10f, new ForestRegular2()),
                        new TileWeightData(10f, new ForestRegular3()),
                        new TileWeightData(5.5f, new SpikeTrap()),
                        new TileWeightData(6, new Gap()),
                },
                100,96, backgrounds.forest())
                .mapTiles("player0", "mTile0")
                .chance(0.7f, 3,1)
                .height(128, 64)
                .enemies(25, new EntityWeightData[]{
                        new EntityWeightData(2, "slime"),
                        new EntityWeightData(2, "chest"),
                        new EntityWeightData(5, "red slime"),
                        new EntityWeightData(3.5f, "skeleton"),
                        new EntityWeightData(10f, "ghost skull")})
                .build());

        levels.put("2-2", new Level.LevelBuilder(
                new TileWeightData[]{
                        new TileWeightData(30, new ForestRegular()),
                        new TileWeightData(5.5f, new SpikeTrap()),
                        new TileWeightData(6, new Gap()),
                },
                100,128, backgrounds.forest())
                .mapTiles("player0", "mTile0")
                .chance(0.7f, 3,1)
                .height(256, 96)
                .enemies(25, new EntityWeightData[]{
                        new EntityWeightData(2, "slime"),
                        new EntityWeightData(2, "chest"),
                        new EntityWeightData(5, "red slime"),
                        new EntityWeightData(3.5f, "skeleton"),
                        new EntityWeightData(10f, "ghost skull")})
                .build());
        levels.put("2-3", new Level.LevelBuilder(
                new TileWeightData[]{
                        new TileWeightData(30, new ForestRegular()),
                        new TileWeightData(5.5f, new SpikeTrap()),
                        new TileWeightData(6, new Gap()),
                },
                100,128, backgrounds.forest())
                .mapTiles("player0", "mTile0")
                .chance(0.7f, 3,1)
                .height(256, 96)
                .enemies(25, new EntityWeightData[]{
                        new EntityWeightData(2, "slime"),
                        new EntityWeightData(2, "chest"),
                        new EntityWeightData(5, "red slime"),
                        new EntityWeightData(3.5f, "skeleton"),
                        new EntityWeightData(10f, "ghost skull")})
                .build());
        levels.put("2-4", new Level.LevelBuilder(
                new TileWeightData[]{
                        new TileWeightData(30, new ForestRegular()),
                        new TileWeightData(5.5f, new SpikeTrap()),
                        new TileWeightData(6, new Gap()),
                },
                100,128, backgrounds.forest())
                .mapTiles("player0", "mTile0")
                .chance(0.7f, 3,1)
                .height(256, 96)
                .enemies(25, new EntityWeightData[]{
                        new EntityWeightData(2, "slime"),
                        new EntityWeightData(2, "chest"),
                        new EntityWeightData(5, "red slime"),
                        new EntityWeightData(3.5f, "skeleton"),
                        new EntityWeightData(10f, "ghost skull")})
                .build());

    }

    public Level getByName(String name) {
        return levels.get(name);
    }

}
