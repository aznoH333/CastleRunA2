package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.LibGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 640;
        config.height = 640;
		// 360x640 = normal resolution
		// 270x600 = dumb rare resolution
		// 640x640 = testing square
		// 1000x1000 = stupod
		// FIXME : performance might be suffering because of unoptimized drawing
		// FIXME : android studio is drunk and doesn't acknowledge the existence of class Game
        config.fullscreen = false;
		new LwjglApplication(new LibGdxGame(), config);
	}
}
