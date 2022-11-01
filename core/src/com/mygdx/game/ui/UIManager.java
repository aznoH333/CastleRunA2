package com.mygdx.game.ui;

import com.mygdx.game.Game;
import com.mygdx.game.data.ILambdaFunction;
import com.mygdx.game.data.enums.GameState;
import com.mygdx.game.data.enums.UIActionStatus;
import com.mygdx.game.ui.elements.parents.MessageBox;
import com.mygdx.game.ui.elements.regularElements.TransitionScreen;
import com.mygdx.game.ui.interfaces.IUIElement;
import com.mygdx.game.ui.interfaces.IUIParentElement;
import com.mygdx.game.ui.interfaces.IUIUpdatable;
import com.mygdx.game.ui.interfaces.IUIUpdateOnInit;

import java.util.ArrayList;

public class UIManager {
    private static UIManager INSTANCE;

    public static UIManager getINSTANCE(){
        if (INSTANCE == null) INSTANCE = new UIManager();
        return INSTANCE;
    }

    private final ArrayList<IUIElement> uiElements = new ArrayList<>();
    private final ArrayList<IUIUpdatable> uiUpdatables = new ArrayList<>();
    private final ArrayList<IUIParentElement> uiParents = new ArrayList<>();
    private final ArrayList<IUIUpdateOnInit> uiUpdateOnInit = new ArrayList<>();
    private GameState targetState = null;
    private boolean isTransitioning = false;
    private final TransitionScreen transition = new TransitionScreen();
    private ILambdaFunction execOnTransitionFinish = ()->{};
    private final MessageBox msgBox = new MessageBox();





    public void drawUI(){
        for (IUIElement element: uiElements) {
            element.draw();
        }
        transition.draw();
        msgBox.draw();
    }

    public void changeUI(GameState state){
        clearUI();
        for (IUIElement element: state.state.getUI()) {
            addUIElement(element);
        }
        for (IUIUpdateOnInit u: uiUpdateOnInit) {
            u.updateOnInit();
        }
    }


    public void addUIElement(IUIElement element){
        uiElements.add(element);
        if (element instanceof IUIParentElement) uiParents.add((IUIParentElement) element);
        if (element instanceof IUIUpdatable) uiUpdatables.add((IUIUpdatable) element);
        if (element instanceof IUIUpdateOnInit) uiUpdateOnInit.add((IUIUpdateOnInit) element);
    }

    private boolean openOnTransitionFinish = true;
    private boolean changeStateOnTransitionClose = false;
    public void delayedOpening(){
        openOnTransitionFinish = true;
        transition.uiOpen();
    }

    private void open(){
        for (IUIParentElement parent: uiParents) {
            parent.uiOpen();
        }
    }

    public void close(){
        for (IUIParentElement parent: uiParents) {
            parent.uiClose();
        }
        msgBox.uiClose();
    }

    public void updateUI(){
        for (IUIUpdatable element: uiUpdatables) {
            element.update();
        }
        transition.update();
        msgBox.update();

        if (openOnTransitionFinish && transition.getStatus() == UIActionStatus.Open){
            openOnTransitionFinish = false;
            open();
        }

        if (changeStateOnTransitionClose && transition.getStatus() == UIActionStatus.Closed){
            changeStateOnTransitionClose = false;
            Game.changeState(targetState);
            execOnTransitionFinish.function();
        }

        // transition handling
        if (isTransitioning){
            boolean transitionComplete = true;

            for (IUIParentElement parent: uiParents) {
                if (parent.getStatus() != UIActionStatus.Closed) transitionComplete = false;
            }

            if(transitionComplete){
                isTransitioning = false;
                transition.uiClose();
                changeStateOnTransitionClose = true;
            }
        }


    }

    public void clearUI(){
        uiElements.clear();
        uiParents.clear();
        uiUpdatables.clear();
        uiUpdateOnInit.clear();
    }

    public void transition(GameState state, ILambdaFunction execOnTransition){
        close();
        isTransitioning = true;
        targetState = state;
        this.execOnTransitionFinish = execOnTransition;
    }


    public void displayMessage(String text){
        msgBox.setText(text);
        msgBox.uiOpen();
    }

    public void transition(GameState state){
        transition(state, ()->{});
    }

    public boolean isTransitioning(){
        return transition.getStatus() != UIActionStatus.Transitioning && !isTransitioning;
    }
}
