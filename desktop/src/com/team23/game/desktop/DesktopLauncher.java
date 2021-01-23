package com.team23.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.team23.game.GameEntry;

import java.awt.*;

/***
 * Desktop Launcher
 * Main entry where you start this game.
 *
 * @author Zhikang Liu
 * @since 2.0
 */
public class DesktopLauncher {
	/**
	 * The main function, which is the entry.
	 * @param arg an array of string parsed by the entry.
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		float scale = 0.6F;
		config.title = "Auber Game - York CS ENG1 Assessment - Team 23";
		config.width = (int) (1920 * scale);
		config.height = (int) (1080 * scale);
		config.resizable = true;

		new LwjglApplication(new GameEntry(), config);
	}
}
