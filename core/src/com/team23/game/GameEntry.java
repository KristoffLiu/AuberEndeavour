package com.team23.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team23.game.screens.GameOverScreen;
import com.team23.game.screens.startscreen.StartScreen;
import com.team23.game.screens.PlayScreen;

/***
 * Game Entry
 * the whole .
 *
 * @author Zhikang Liu
 */
public class GameEntry extends Game {
	public static GameEntry current;
	public SpriteBatch batch;
	public static final int VIEW_WIDTH = 1920;
	public static final int VIEW_HEIGHT = 1080;
	public static final int ZOOM = 12;
	public String teleporting;
	public Screen screen;
	public boolean demo;
	//game state -1= intro screen 0=exit startscreen 1=playing 2=win 3=lost
	public int gameState;
	private Screen introScreen;
	private GameOverScreen gameOverScreen;



	@Override
	public void create () {
		current = this;
		batch = new SpriteBatch();
		introScreen = new StartScreen(this);
		gameOverScreen = new GameOverScreen(this);
		setScreen(introScreen);
		teleporting = "false";
		gameState = -1;
	}

	/**
	 * Initialises the game's play screen
	 * @param demo If the screen should be in demo mode or not
	 */
	public void createPlayScreen(boolean demo){
		screen = new PlayScreen(this,demo);
	}

	@Override
	public void render () {
		super.render();
		//exit intro screen and start game
		if (gameState==0||gameState==4){
			setScreen(screen);
			gameState=1;
		}

		//when game over go to gameoverscreen
		if (gameState==2 || gameState==3){
			setScreen(gameOverScreen);
		}

		if (teleporting !="true" && teleporting !="false"){
			//exit teleport screen
			setScreen(screen);
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public void setGameState(int gameState){
		this.gameState=gameState;
	}
}
