package com.mygdx.game.logic.menus;

import com.mygdx.game.Game;
import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.data.PlayerClass;
import com.mygdx.game.data.enums.GameState;
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
            // TODO : locked class sprite

            double rotationValue = (i * ((Math.PI * 2) / classes.length)) + rotation + tempRotation;

            spr.draw(
                    (prgrs.getClasses().get(classes[i]) ? classes[i].sprite : "slime") + ((i == selectedClass && Game.Time() % 40 > 20) ? "2" : "0"),
                    Game.gameWorldWidth/2 - ((2.3f-(float) (Math.cos(rotationValue)))*32) + (float)(Math.sin(rotationValue) * (Game.gameWorldWidth/2 - 128)),
                    600 + (float)(Math.cos(rotationValue) * circleHeight),
                    (Math.cos(rotationValue) < 0.10) ? 5 : 4,
                    true,
                    2.3f-(float) (Math.cos(rotationValue)));
        }
    }

    private boolean isHold = false; // FIXME : this is eangrish
    private float holdPos = 0;
    private float tempRotation;
    public void update(){

        rotation %= Math.PI * 2;
        if (rotation < 0) rotation = (float) (Math.PI * 2) + rotation;

        selectedClass = (classes.length - (int)Math.round(
                (((rotation - Math.PI) % (Math.PI*2)) / (Math.PI * 2)) * (classes.length - 1)
        )) % classes.length;

        spr.drawText(classes[selectedClass].name(), 200, 800, 3);

        if (input.getMouseY() > 500 && input.getMouseY() < 700){
            if (!isHold && input.isInputHeld()){
                holdPos = input.getMouseX();
                isHold = true;

            }else if (isHold && !input.isInputHeld()){
                isHold = false;
                rotation += tempRotation;
                tempRotation = 0;
            }
            if (isHold)
                tempRotation = ((holdPos - input.getMouseX())/ (Game.gameWorldWidth/2));
        }

        if (!isHold){
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
        PlayerStats.getINSTANCE().setPlayerClass(classes[selectedClass]);
        InventoryManager.getINSTANCE().resetState();
        PlayerStats.getINSTANCE().resetStats();
        lvlMan.resetProgress();
        lvlMan.progressLevel();
        lvlMan.startLevel();
        UIManager.getINSTANCE().transition(GameState.Game);
    }

    public ILambdaFunction buttonOperation(){
        if (prgrs.getClasses().get(classes[selectedClass])) return ()-> startNewGame(Game.getGeneralRandom().nextInt(999));
        else                                                return ()-> prgrs.unlockClass(classes[selectedClass]);
    }
}
