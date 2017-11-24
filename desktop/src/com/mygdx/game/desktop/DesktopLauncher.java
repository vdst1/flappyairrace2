package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.flappyairrace2;

import static com.mygdx.game.flappyairrace2.HEIGHT;
import static com.mygdx.game.flappyairrace2.TITLE;
import static com.mygdx.game.flappyairrace2.WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH ;
		config.height = HEIGHT;
		config.title = TITLE;
		new LwjglApplication(new flappyairrace2(), config);
	}
}
