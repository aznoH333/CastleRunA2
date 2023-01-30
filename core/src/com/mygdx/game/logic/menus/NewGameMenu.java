package com.mygdx.game.logic.menus;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Game;
import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.data.PlayerClass;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.items.items.CursedItem;
import com.mygdx.game.logic.drawing.DrawingManager;
import com.mygdx.game.logic.entities.EntityManager;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.player.InventoryManager;
import com.mygdx.game.logic.player.ItemManager;
import com.mygdx.game.logic.player.PlayerStats;
import com.mygdx.game.logic.player.ProgressManager;
import com.mygdx.game.logic.stage.LevelProgressionManager;
import com.mygdx.game.ui.UIManager;


public class NewGameMenu {
    private static NewGameMenu INSTANCE;
    public static NewGameMenu getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new NewGameMenu();
        return INSTANCE;
    }

    private static final LevelProgressionManager lvlMan = LevelProgressionManager.getINSTANCE();
    private static final DrawingManager spr = DrawingManager.getINSTANCE();
    private static final ProgressManager prgrs = ProgressManager.getINSTANCE();
    private static final InputManager input = InputManager.getINSTANCE();
    private final PlayerClass[] classes = PlayerClass.values();
    private int selectedClass = 0;
    private float rotation = (float) Math.PI;
    private static final int circleHeight = 50;

    public void draw(){
        for (int i = 0; i < classes.length; i++){


            double rotationValue = (i * ((Math.PI * 2) / classes.length)) + rotation + tempRotation;
            boolean isUnlocked = prgrs.getClasses().get(classes[i]);

            spr.draw(
                    (isUnlocked ? classes[i].sprite : "locked_char") + ((i == selectedClass && Game.Time() % 40 > 20 && isUnlocked) ? "2" : "0"),
                    Game.gameWorldWidth/2 - ((2.3f-(float) (Math.cos(rotationValue)))*32) + (float)(Math.sin(rotationValue) * (Game.gameWorldWidth/2 - 128)),
                    600 + (float)(Math.cos(rotationValue) * circleHeight),
                    (Math.cos(rotationValue) < 0.10) ? 5 : 4,
                    2.3f-(float) (Math.cos(rotationValue)),
                    2.3f-(float) (Math.cos(rotationValue)),
                    Color.WHITE,
                    false);

        }
    }

    private boolean isCurrentlyRotating = false;
    private float holdPos = 0;
    private float tempRotation;
    public void update(){

        rotation = loopRotationValue(rotation);
        float offsetRotationalValue = loopRotationValue(rotation + (float) (0.5f * Math.PI));
        selectedClass = classes.length - 1 - (int) Math.round((offsetRotationalValue / (Math.PI * 2)) * (classes.length)) % classes.length;

        spr.drawText(classes[selectedClass].name(), 200, 800, 3);

        if (input.getMouseY() > 125 * spr.getPixelScale() &&
                input.getMouseY() < 225 * spr.getPixelScale()){
            if (!isCurrentlyRotating && input.isInputHeld()){
                holdPos = input.getMouseX();
                isCurrentlyRotating = true;

            }else if (isCurrentlyRotating && !input.isInputHeld()){
                isCurrentlyRotating = false;
                rotation += tempRotation;
                tempRotation = 0;
            }
            if (isCurrentlyRotating)
                tempRotation = ((holdPos - input.getMouseX())/ (Game.gameWorldWidth/2));
        }

        if (!isCurrentlyRotating){
            // snap to selected class
            double desiredPos = (((1-((float) selectedClass / classes.length)) + 0.5) % 1) * Math.PI * 2;
            if (rotation < desiredPos - 0.1) rotation+=0.03;
            else if (rotation > desiredPos + 0.1) rotation-=0.03;
            else rotation = (float) desiredPos;
        }
    }

    public void startNewGame(long newSeed){
        EntityManager.getINSTANCE().clear();
        ItemManager.getINSTANCE().clearItems();
        Game.getSeededRandom().setSeed(newSeed);

        // ???
        Game.getSeededRandom().nextInt();


        PlayerStats.getINSTANCE().setPlayerClass(classes[selectedClass]);
        InventoryManager.getINSTANCE().resetState();
        PlayerStats.getINSTANCE().resetStats();
        lvlMan.resetProgress();
        lvlMan.progressLevel();
        lvlMan.startLevel();
        UIManager.getINSTANCE().transition(GameState.Game);
        // give cursed skull
        if (PlayerStats.getINSTANCE().getPlayerClass() == PlayerClass.Haunted){
            ItemManager.getINSTANCE().addItem(new CursedItem());
        }
    }

    public ILambdaFunction buttonOperation(){
        if (prgrs.getClasses().get(classes[selectedClass])) return ()-> startNewGame(Game.getGeneralRandom().nextInt(999));
        else                                                return ()-> prgrs.unlockClass(classes[selectedClass]);
    }


    private float loopRotationValue(float rotationValue){
        float output = rotationValue;
        output %= Math.PI * 2;
        if (output < 0) output = (float) (Math.PI * 2) + output;
        return output;
    }
}
