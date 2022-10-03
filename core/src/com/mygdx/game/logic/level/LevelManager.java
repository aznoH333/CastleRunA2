package com.mygdx.game.logic.level;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.entities.player.PlayerSpawner;
import com.mygdx.game.logic.level.tileCollums.ICollumnActivatavle;
import com.mygdx.game.logic.level.tileCollums.ISpecialTile;
import com.mygdx.game.logic.level.tileCollums.TileCollum;
import com.mygdx.game.data.enums.Directions;
import com.mygdx.game.data.enums.TileCollumSpecial;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.abstracts.Entity;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.entities.ParticleManager;
import com.mygdx.game.logic.player.ItemManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.ui.UIManager;

import java.util.ArrayList;
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
    private static final int mapWidth = (int)(Gdx.graphics.getWidth() * (1280f/Gdx.graphics.getHeight())) / tileScale + 2;
    private static final int tileThickness = 16; // affects only collision detection

    //vars
    private final DrawingManager spr = DrawingManager.getINSTANCE();
    private float distance = 0;
    private float advanceDistance = 0;
    private final TileCollum[] map = new TileCollum[mapWidth];
    private final BackgroundRenderer backgroundRenderer = BackgroundRenderer.getINSTANCE();
    private Level lvl;
    private final Random random = Game.getSeededRandom();
    private final ParticleManager part = ParticleManager.getINSTANCE();
    private static final EntityManager e = EntityManager.getINSTANCE();
    //lvl generation vars
    private int height = gamePosition;
    private boolean grace = false;
    private Directions dir = Directions.None;
    private int changeFor = 0;
    private int levelLength = 0;
    private int startGenerationIndex = 3;
    private boolean isBossLevel = false;
    private final ArrayList<TileActivator> tileActivators = new ArrayList<>();
    private int levelModiferTimer = 20;
    private long levelStartTimeStamp = 0;


    // very dumb
    // the y offset of level tiles
    public static final int gamePosition = 426;

    // FIXME : this sucks! clean it up!
    // no
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
            lvl.getModifier().onTileGenerate(temp);
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
                    if (height > lvl.minHeight() + gamePosition)
                        height -= stepHeight;
                    break;
                case Up:
                    if (height < lvl.maxHeight() + gamePosition)
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
            else {
                advanceDistance = dist + distance;
            }
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
        // camera fail safe
        if (distance % tileScale > 0 && advanceDistance == distance){
            advanceToTile(tileScale - (distance%tileScale));
        }



        //advance
        if (advanceDistance > distance) {
            float advanceBy = (float) Math.ceil(advanceSpeed * Math.abs((distance / tileScale) - (advanceDistance / tileScale)));
            e.shiftAllEntities(advanceBy);
            part.shiftPartsBy(advanceBy);

            // shift activators
            for (TileActivator a: tileActivators) {
                a.shiftBy(advanceBy);
            }

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



        // advance activators
        if (Game.Time()%32==0){
            for (TileActivator a: tileActivators) {
                a.advance();
            }

        }

        // this sucks...                                oh no, anyway..
        for (TileActivator a: tileActivators) {
            if (a.getX() < (mapWidth-1)*tileScale && getOnPos(a.getX() + distance%64) instanceof ICollumnActivatavle) ((ICollumnActivatavle) getOnPos(a.getX() + distance%64)).activate(a);
        }

        // modifiers
        if (lvl.hasModifier()){
            levelModiferTimer--;
            if (levelModiferTimer == 0){
                lvl.getModifier().levelModifierTick();
                levelModiferTimer = 20;
            }
        }

        if (Game.Time() == levelStartTimeStamp + 160) {
            UIManager.getINSTANCE().displayMessage(lvl.getModifier().getIntroMessage());
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

    public boolean collidesWithLevel(Entity object){
        return object.getY() <= getLevelY(object) && object.getY() + object.getYSize() > getLevelY(object) + tileThickness;
    }

    public float getLevelY(Entity object){
        return getOnPos(object.getX() + (object.getXSize()/2) + (distance%tileScale)).getY() + tileScale;
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

    public float getAlignedX(float currentX){
        return ((int) currentX >> 6) * tileScale - (distance % tileScale);
    }

    public void loadLevel(Level lvl) {

        this.lvl = lvl;
        part.clear();

        isBossLevel = lvl.isBossLevel();
        backgroundRenderer.setBackground(lvl.getBackground());
        distance = 0;
        advanceDistance = 0;
        trapOffset = 0;
        tileActivators.clear();
        levelModiferTimer = 120;

        int maxActivatorCount = (mapWidth * tileScale) / lvl.getActivationRate();

        startGenerationIndex = 3;
        if (!isBossLevel)
            height = random.nextInt(lvl.maxHeight()-lvl.minHeight()) + lvl.minHeight() + gamePosition;
        else
            height = lvl.defaultH + gamePosition;
        levelLength = lvl.getLength();
        e.clear();
        // pre generate first screen

        int tilesSinceLastActivator = 0;
        tileActivators.add(new TileActivator(0));

        for (int i = 0; i < mapWidth; i++) {
            generateLevel(i);
            // spawn tile activators
            if (tileActivators.size() <= maxActivatorCount && (tilesSinceLastActivator * tileScale) % lvl.getActivationRate() < tileScale){
                tileActivators.add(new TileActivator(i * tileScale));
                tilesSinceLastActivator = 0;
            }
            tilesSinceLastActivator++;

        }

        // spawn player
        e.addEntity(new PlayerSpawner(64,map[1].getY() + 64));
        levelStartTimeStamp = Game.Time();
        ItemManager.getINSTANCE().onLevelStart();
        PlayerStats.getINSTANCE().restoreStats();
    }



}
