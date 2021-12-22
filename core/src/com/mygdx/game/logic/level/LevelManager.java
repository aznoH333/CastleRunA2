package com.mygdx.game.logic.level;

import com.mygdx.game.data.Level;
import com.mygdx.game.data.TileCollum;
import com.mygdx.game.data.enums.Directions;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.sprites.SpriteManager;
import com.mygdx.game.logic.entities.Entity;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;

import java.util.Random;

public class LevelManager {


    //constants
    private final float advanceSpeed = 6f;
    private final int stepHeight = 16;
    public static final int tileScale = 64;
    public static final int mapWidth = 21;

    //vars
    private final SpriteManager spr;
    private float distance = 0;
    private float advanceDistance = 0;
    private final TileCollum[] map = new TileCollum[mapWidth];
    private final BackgroundRenderer b;
    private Level lvl;
    private Random r;
    private EntityManager e;
    private ParticleManager part = ParticleManager.getINSTANCE();
    //lvl generation vars
    private int height = 0;
    private boolean grace = false;
    private Directions dir = Directions.None;
    private int changeFor = 0;
    private int levelLength = 6400;
    private int startGenerationIndex = 3;

    public LevelManager(SpriteManager spr, Random r) {
        this.spr = spr;
        this.r = r;
        this.b = BackgroundRenderer.getINSTANCE();
    }

    public void setE(EntityManager e) {
        this.e = e;
    }

    //TODO: exiting levels
    //TODO: side passages
    private void generateLevel(int index) {
        // generate starts & ends
        if (startGenerationIndex > 0 || distance > levelLength - 196) {
            TileCollum temp;
            do {
                temp = lvl.randomTile(r, height);
            } while (temp.getSpecial() != TileCollumSpecial.None);
            map[index] = temp;
            startGenerationIndex--;
        } else {
            // tile selection & grace handling
            TileCollum temp;
            while (true) {
                temp = lvl.randomTile(r, height);
                if (!grace || !temp.grace()) {
                    if (temp.grace()) {
                        grace = true;
                        height = lastHeight;
                    }
                    break;
                }
            }
            //add enemies
            if (!grace) {
                Entity tempE = lvl.randomEnemy(r, index * tileScale - (distance % tileScale), height);
                if (tempE != null) {
                    e.addEntity(tempE);
                }
            }

            moveInDirection();
            changeDirection();
            if (!temp.grace()) grace = false;
            map[index] = temp;
        }
    }

    private void changeDirection() {
        //change direction
        if (changeFor < 1) {
            if (r.nextFloat() < lvl.changeChance()) {
                if (r.nextBoolean()) dir = Directions.Up;
                else dir = Directions.Down;
            } else dir = Directions.None;
            changeFor = r.nextInt(lvl.changeLengthMax() - lvl.changeLengthMin()) + lvl.changeLengthMin();
        }
    }

    private int lastHeight = 0;

    private void moveInDirection() {
        // move in direction
        if (!grace) {
            lastHeight = height;
            switch (dir) {
                case Down:
                    if (height > lvl.minHeight())
                        height -= stepHeight;
                    break;
                case Up:
                    if (height < lvl.maxHeight())
                        height += stepHeight;
                    break;
            }
            changeFor--;
        }
    }

    private void renderLevel() {
        b.draw(spr);

        for (int x = 0; x < mapWidth; x++) {
            TileCollum currentCollum = map[x];
            if (currentCollum.getSpecial() != TileCollumSpecial.Gap) {
                // render tiles
                for (int y = 0; y < currentCollum.getY() / tileScale + 2; y++) {
                    spr.draw(currentCollum.getTexture(y), x * tileScale - (distance % tileScale), currentCollum.getY() - y * tileScale);
                }

                // render special tile objects
                if (currentCollum.getSpecial() != TileCollumSpecial.None)
                    currentCollum.draw(x * tileScale - (distance % tileScale), currentCollum.getY());
            }

        }
    }


    public void advanceToTile(float dist) {
        if (dist + distance > advanceDistance){
            if (dist + distance > levelLength) advanceDistance = levelLength;
            else advanceDistance = dist + distance;
        }

    }


    public int getMapWidth() {
        return mapWidth;
    }

    public void update() {
        //advance
        if (advanceDistance > distance) {
            float advanceBy = (float) Math.ceil(advanceSpeed * Math.abs((distance / tileScale) - (advanceDistance / tileScale)));
            e.shiftAllEntities(advanceBy);
            part.shiftPartsBy(advanceBy);
            distance += advanceBy;
            b.advance(advanceBy * lvl.getParallax());

            //shift map & generate
            //shift only while advancing
            if (distance % tileScale < advanceBy) {
                System.arraycopy(map, 1, map, 0, mapWidth - 1);
                generateLevel(20);
            }
        }
        // special tile update
        for (final TileCollum collum : map) {
            if (collum.getSpecial() != TileCollumSpecial.None && collum.getSpecial() != TileCollumSpecial.Gap)
                collum.update();
        }

        renderLevel();
    }

    public TileCollum getOnPos(float i) {
        return map[(int) (i / tileScale)];
    }

    public float snapToPosition(float x){
        return x-(x%tileScale)-distance%tileScale;
    }

    public int getTileScale() {
        return tileScale;
    }

    public void loadLevel(Level lvl) {
        b.setBackground(lvl.getBackground());
        distance = 0;
        this.lvl = lvl;
        height = r.nextInt(lvl.maxHeight()-lvl.minHeight()) + lvl.minHeight();

        // pre generate first screen
        for (int i = 0; i < mapWidth; i++) {
            generateLevel(i);
        }
    }
}
