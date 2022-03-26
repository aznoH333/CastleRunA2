package com.mygdx.game.ui;

import com.mygdx.game.Game;
import com.mygdx.game.data.enums.ButtonType;
import com.mygdx.game.data.enums.Controls;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.data.enums.UIActionStatus;
import com.mygdx.game.data.enums.UIType;
import com.mygdx.game.logic.player.InputManager;
import com.mygdx.game.logic.stage.StageManager;
import com.mygdx.game.ui.elements.BottomHud;
import com.mygdx.game.ui.elements.Button;
import com.mygdx.game.ui.elements.Sprite;
import com.mygdx.game.ui.elements.Text;
import com.mygdx.game.ui.elements.TransitionScreen;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIParentElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;

import java.util.ArrayList;

public class UIManager {
    private static UIManager INSTANCE;
    private final static InputManager input = InputManager.getINSTANCE();

    public static UIManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new UIManager();
        return INSTANCE;
    }

    private final ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final ArrayList<IUIUpdatable> uiUpdatables = new ArrayList<>();
    private final ArrayList<IUIParentElement> uiParents = new ArrayList<>();
    private GameState targetState = null;
    private boolean isTransitioning = false;
    private final static StageManager stageMan = StageManager.getINSTANCE();
    private final TransitionScreen transition = new TransitionScreen();


    // dumb constants
    private final static float buttonLX = 16f;
    private final static float buttonRX = 368f;
    private final static float xIconOffset = 136;
    private final static float xIconOffsetLarge = 302;
    private final static float yIconOffset = 38;



    public void drawUI(){
        for (IUIElement element: uiElements) {
            element.draw();
        }
        transition.draw();
    }

    public void changeUI(GameState state){
        clearUI();
        switch (state){
            case Game:
                // bottom bar
                addUIElement(new BottomHud(-515f,-150f));
                addUIElement(new Button(buttonLX,182, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.MoveLeft)));
                addUIElement(new Sprite(xIconOffset, yIconOffset,"icon1", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonRX,182, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.MoveRight)));
                addUIElement(new Sprite(xIconOffset, yIconOffset,"icon0", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,315, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.AttackLeft)));
                addUIElement(new Sprite(xIconOffset, yIconOffset,"icon3", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonRX,315, ButtonType.SmallAction, uiElements.get(0),()-> input.buttonHold(Controls.AttackRight)));
                addUIElement(new Sprite(xIconOffset, yIconOffset,"icon2", uiElements.get(uiElements.size()-1)));
                break;
            case StageMenu:
                addUIElement(new BottomHud(-515f,-30f));
                addUIElement(new Button(buttonLX,62,ButtonType.Large,uiElements.get(0),()->{transition(GameState.Game);stageMan.startLevel();})); // TODO: send player to main menu
                addUIElement(new Sprite(xIconOffsetLarge, yIconOffset,"icon4", uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,198,ButtonType.Small,uiElements.get(0),()->transition(GameState.EquipMenu)));
                addUIElement(new Text(94,80,"Items",uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonRX,198,ButtonType.Small,uiElements.get(0),()->transition(GameState.Shop)));
                addUIElement(new Text(110,80,"Shop",uiElements.get(uiElements.size()-1)));

                addUIElement(new Button(buttonLX,338,ButtonType.Large,uiElements.get(0),()->{transition(GameState.Game);stageMan.startLevel();}));
                addUIElement(new Sprite(xIconOffsetLarge, yIconOffset,"icon1", uiElements.get(uiElements.size()-1)));
        }
    }

    private void addUIElement(IUIElement element){
        uiElements.add(element);
        for (UIType type: element.getTypes()) {
            switch (type){
                case Parent:
                    uiParents.add((IUIParentElement) element);
                    break;
                case Updatable:
                    uiUpdatables.add((IUIUpdatable) element);
                    break;
            }
        }
    }

    public void open(){
        for (IUIParentElement parent: uiParents) {
            parent.uiOpen();
        }
        transition.uiOpen();
    }

    public void close(){
        for (IUIParentElement parent: uiParents) {
            parent.uiClose();
        }
        transition.uiClose();
    }

    public void updateUI(){
        for (IUIUpdatable element: uiUpdatables) {
            element.update();
        }
        transition.update();

        // transition handling
        if (isTransitioning){
            boolean transitionComplete = transition.getStatus() == UIActionStatus.Closed;

            for (IUIParentElement parent: uiParents) {
                if (parent.getStatus() != UIActionStatus.Closed) transitionComplete = false;
            }

            if(transitionComplete){
                isTransitioning = false;

                Game.changeState(targetState);
            }
        }


    }

    public void clearUI(){
        uiElements.clear();
        uiParents.clear();
        uiUpdatables.clear();
    }

    public void transition(GameState state){
        close();
        isTransitioning = true;
        targetState = state;
        // TODO : transition manager
    }
}
