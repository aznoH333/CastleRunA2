package com.mygdx.game.logic.level;


import com.mygdx.game.Game;
import com.mygdx.game.logic.level.tileCollums.ISpecialTile;
import com.mygdx.game.logic.level.tileCollums.TileCollum;
import com.mygdx.game.data.enums.Directions;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;

import java.util.Random;

public class LevelManager {

    private static LevelManager INSTANCE;

    public static LevelManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new LevelManager();
        return INSTANCE;
    }



    //constants
    private static final float advanceSpeed = 6f;
    private static final int stepHeight = 16;
    public static final int tileScale = 64;
    public static final int mapWidth = 13;

    //vars
    private final DrawingManager spr = DrawingManager.getINSTANCE();
    private float distance = 0;
    private float advanceDistance = 0;
    private final TileCollum[] map = new TileCollum[mapWidth];
    private final BackgroundRenderer backgroundRenderer = BackgroundRenderer.getINSTANCE();
    private Level lvl;
    private final Random random = Game.getSeededRandom();
    private ParticleManager part = ParticleManager.getINSTANCE();
    private static final EntityManager e = EntityManager.getINSTANCE();
    //lvl generation vars
    private int height = 0;
    private boolean grace = false;
    private Directions dir = Directions.None;
    private int changeFor = 0;
    private int levelLength = 0;
    private int startGenerationIndex = 3;
    private boolean isBossLevel = false;





    private void generateLevel(int index) {
        // generate starts & ends
        if (startGenerationIndex > 0 || distance > levelLength - (tileScale * 4)) {
            TileCollum temp;
            do {
                temp = lvl.randomTile(random, height);
            } while (temp.getSpecial() != TileCollumSpecial.None);
            lastHeight = height;
            // spawn exit door
            if (Math.abs(levelLength-(tileScale*2) - distance) < 10 && !isBossLevel)
                    EntityManager.getINSTANCE().spawnEntity("exit door",index * tileScale - (distance % tileScale), height);
            else if ((Math.abs(levelLength-(tileScale*3) - distance) < 32 && isBossLevel))
                    EntityManager.getINSTANCE().spawnEntity(lvl.getBoss(),index * tileScale - (distance % tileScale), height);
            map[index] = temp;
            startGenerationIndex--;
        } else {
            // tile selection & grace handling
            TileCollum temp;
            while (true) {
                temp = lvl.randomTile(random, height);
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
                Entity tempE = lvl.randomEnemy(random, index * tileScale - (distance % tileScale), height);
                if (tempE != null) {
                    e.addEntity(tempE);
                }
            }
            if (!isBossLevel){
                moveInDirection();
                changeDirection();
            }

            if (!temp.grace()) grace = false;
            map[index] = temp;
        }
    }

    private void changeDirection() {
        //change direction
        if (changeFor < 1) {
            if (random.nextFloat() < lvl.changeChance()) {
                if (random.nextFloat() <= 0.5) dir = Directions.Up;
                else dir = Directions.Down;
            } else dir = Directions.None;
            changeFor = random.nextInt(lvl.changeLengthMax() - lvl.changeLengthMin()) + lvl.changeLengthMin();
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
        backgroundRenderer.draw(spr);

        for (int x = 0; x < mapWidth; x++) {
            TileCollum currentCollum = map[x];
            if (currentCollum.getSpecial() != TileCollumSpecial.Gap) {
                // render tiles
                for (int y = 0; y < currentCollum.getY() / tileScale + 2; y++) {
                    spr.drawGame(currentCollum.getTexture(y), x * tileScale - (distance % tileScale), currentCollum.getY() - y * tileScale);
                }

                // render special tile objects
                if (currentCollum instanceof ISpecialTile)
                    ((ISpecialTile)currentCollum).draw(x * tileScale - (distance % tileScale), currentCollum.getY());
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

    private int trapOffset = 0;
    public int getTrapOffset(){
        return trapOffset++;
    }

    public void update() {
        //advance
        if (advanceDistance > distance) {
            float advanceBy = (float) Math.ceil(advanceSpeed * Math.abs((distance / tileScale) - (advanceDistance / tileScale)));
            e.shiftAllEntities(advanceBy);
            part.shiftPartsBy(advanceBy);
            distance += advanceBy;
            backgroundRenderer.advance(advanceBy);

            //shift map & generate
            //shift only while advancing
            if (distance % tileScale < advanceBy) {
                System.arraycopy(map, 1, map, 0, mapWidth - 1);
                generateLevel(mapWidth-1);
            }
        }
        // special tile update
        for (final TileCollum collum : map) {
            if (collum instanceof ISpecialTile)
                ((ISpecialTile)collum).update();
        }

        // snap camera to boss
        if (isBossLevel && Math.abs(levelLength-(tileScale*((mapWidth>>1)-2)) - distance) < 10)
            advanceToTile(levelLength);
        renderLevel();
    }

    public TileCollum getOnPos(float i) {
        int b = (int) i >> 6;
        if (b < 0 || b >= mapWidth)
            return map[0];
        else
            return map[b];
    }

    public float snapToPosition(float x){
        return x-(x%tileScale)-distance%tileScale;
    }

    public boolean collidesWithLevel(Entity object){
        // TODO: advanced level collisions
        return object.getY() < getLevelY(object);
    }

    public float getLevelY(Entity object){
        return getOnPos(object.getX() + ((int)object.getXSize()>>1) + (distance%tileScale)).getY() + tileScale;
    }

    public int getTileScale() {
        return tileScale;
    }

    public int getLevelLength(){
        return levelLength;
    }

    public float getDistance(){
        return distance;
    }

    public void loadLevel(Level lvl) {

        this.lvl = lvl;
        part.clear();

        isBossLevel = lvl.isBossLevel();
        backgroundRenderer.setBackground(lvl.getBackground());
        distance = 0;
        advanceDistance = 0;
        trapOffset = 0;

        startGenerationIndex = 3;
        if (!isBossLevel)
            height = random.nextInt(lvl.maxHeight()-lvl.minHeight()) + lvl.minHeight();
        else
            height = lvl.defaultH;
        levelLength = lvl.getLength();
        e.clear();
        // pre generate first screen
        for (int i = 0; i < mapWidth; i++) {
            generateLevel(i);
        }
    }
}
