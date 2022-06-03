package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

public class LibGdxGame extends ApplicationAdapter {

    private Game game;

    // very sick class
    // just waits for libgdx to load

    @Override
    public void create() {
        game = new Game();
    }

    @Override
    public void render() {
        game.render();
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
