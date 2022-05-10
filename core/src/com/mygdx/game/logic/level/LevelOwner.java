package com.mygdx.game.logic.level;

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

    private HashMap<String, Level> levels = new HashMap<>();


    public LevelOwner() {
        // 100 tiles = good length for a regular level
        levels.put("1-1", new Level.LevelBuilder(
                        new TileCollum[]{new CastleRegular(), new Gap(), new SpikeTrap(), new BreakingPlatform()},
                        new float[]{20, 5, 2, 1},
                        30, 128,
                        Backgrounds.castle())
                .mapTiles("mIconCastle0", "mTile0")
                .height(256,96)
                .chance(0.7f,3,2)
                .enemies(new String[]{"slime", "chest"}, new float[]{20,6,2})
                .build()
        );

        levels.put("1-2", new Level.LevelBuilder(
                new TileCollum[]{new CaveRegular(), new Gap(), new BreakingPlatform()},
                new float[]{15, 5, 1},
                110, 128,
                Backgrounds.cave())
                .mapTiles("mIconCave0", "mTile0")
                .enemies(new String[]{"slime", "chest", "red slime"}, new float[]{20, 5, 1, 3})
                .build()
        );

        levels.put("1-3", new Level.LevelBuilder(
                new TileCollum[]{new CastleRegular(), new Gap(), new SpikeTrap(), new GhostPlatform()},
                new float[]{20, 5, 2, 3},
                95,128,
                Backgrounds.castle())
                .enemies(new String[]{"slime", "chest", "red slime", "skeleton"}, new float[]{20, 2, 1, 2, 5})
                .chance(0.9f, 3, 1)
                .height(128, 96)
                .mapTiles("mIconCastle1", "mTile0")
                .build());

        levels.put("1-4", new Level.LevelBuilder(
                new TileCollum[]{new CaveRegular()},
                new float[]{1},
                20,96, Backgrounds.cave())
                .mapTiles("mIconCave1", "mTile0")
                .setBoss("slime boss")
                .buildBossLevel());

        levels.put("2-1", new Level.LevelBuilder(
                new TileCollum[]{new ForestRegular(), new SpikeTrap(), new Gap()},
                new float[]{30f,5.5f,6f},
                100,128, Backgrounds.forest())
                .mapTiles("player0", "mTile0")
                .chance(0.7f, 3,1)
                .height(256, 96)
                .enemies(new String[]{"slime", "chest", "red slime", "skeleton", "ghost skull"}, new float[]{25f, 2f, 2f, 5f, 3.5f, 10f})
                .build());

        levels.put("2-2", new Level.LevelBuilder(
                new TileCollum[]{new ForestRegular(), new SpikeTrap(), new Gap()},
                new float[]{30f,5.5f,6f},
                100,128, Backgrounds.forest())
                .mapTiles("player0", "mTile0")
                .chance(0.7f, 3,1)
                .height(256, 96)
                .enemies(new String[]{"slime", "chest", "red slime", "skeleton"}, new float[]{25f, 2f, 2f, 5f, 3.5f})
                .build());
        levels.put("2-3", new Level.LevelBuilder(
                new TileCollum[]{new ForestRegular(), new SpikeTrap(), new Gap()},
                new float[]{30f,5.5f,6f},
                100,128, Backgrounds.forest())
                .mapTiles("player0", "mTile0")
                .chance(0.7f, 3,1)
                .height(256, 96)
                .enemies(new String[]{"slime", "chest", "red slime", "skeleton"}, new float[]{25f, 2f, 2f, 5f, 3.5f})
                .build());
        levels.put("2-4", new Level.LevelBuilder(
                new TileCollum[]{new ForestRegular(), new SpikeTrap(), new Gap()},
                new float[]{30f,5.5f,6f},
                100,128, Backgrounds.forest())
                .mapTiles("player0", "mTile0")
                .chance(0.7f, 3,1)
                .height(256, 96)
                .enemies(new String[]{"slime", "chest", "red slime", "skeleton"}, new float[]{25f, 2f, 2f, 5f, 3.5f})
                .build());

    }

    public Level getByName(String name) {
        return levels.get(name);
    }

}
