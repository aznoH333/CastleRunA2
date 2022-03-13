package com.mygdx.game.logic.level;

import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.tilesets.cave.CaveRegular;
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

        levels.put("1-1", new Level.LevelBuilder(
                        new TileCollum[]{new CastleRegular(), new Gap(), new SpikeTrap(), new BreakingPlatform()},
                        new float[]{20, 5, 2, 5},
                        30, 128,
                        Backgrounds.castle())
                .mapTiles("mIconCastle0", "mTile0")
                .height(256,96)
                .chance(0.7f,3,2)
                .enemies(new String[]{"slime", "chest"}, new float[]{10,5,1})
                .build()
        );

        levels.put("1-2", new Level.LevelBuilder(
                new TileCollum[]{new CaveRegular(), new Gap(), new BreakingPlatform()},
                new float[]{15, 5, 1},
                30, 128,
                Backgrounds.cave())
                .mapTiles("mIconCave0", "mTile0")
                .enemies(new String[]{"slime", "chest", "red slime"}, new float[]{20, 5, 1, 3})
                .build()
        );

        levels.put("1-3", new Level.LevelBuilder(
                new TileCollum[]{new CastleRegular(), new Gap(), new SpikeTrap(), new GhostPlatform()},
                new float[]{20, 5, 2, 3},
                30,128,
                Backgrounds.castle())
                .enemies(new String[]{"slime", "chest", "red slime", "skeleton"}, new float[]{20, 2, 1, 2, 5})
                .chance(0.9f, 3, 1)
                .height(96, 64)
                .mapTiles("mIconCastle1", "mTile0")
                .build());

        levels.put("1-4", new Level.LevelBuilder(
                new TileCollum[]{new CaveRegular()},
                new float[]{1},
                20,96, Backgrounds.cave())
                .mapTiles("mIconCave1", "mTile0")
                .setBoss("slime boss")
                .buildBossLevel());

    }

    public Level getByName(String name) {
        return levels.get(name);
    }

}
