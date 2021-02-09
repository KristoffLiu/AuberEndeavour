package com.team23.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team23.game.save.AuberInfo;
import com.team23.game.screens.teleportscreen.TeleportScreen;
import com.team23.game.utils.Position;
import com.team23.game.save.Save;
import com.team23.game.save.SaveManager;
import com.team23.game.screens.GameOverScreen;
import com.team23.game.screens.playscreen.PlayConfig;
import com.team23.game.screens.playscreen.PlayState;
import com.team23.game.screens.startscreen.StartScreen;
import com.team23.game.screens.playscreen.PlayScreen;

/***
 * Game Entry
 * the whole .
 *
 * @author Zhikang Liu
 */
public class GameEntry extends Game {
	public static GameEntry current;
	public SpriteBatch batch;
	public SaveManager saveManager;
	public static final int VIEW_WIDTH = 1920;
	public static final int VIEW_HEIGHT = 1080;
	public static final int ZOOM = 12;
	public String teleporting;
	public boolean demo;
	public PlayConfig playConfig;
	private PlayState state;

	public StartScreen startScreen;
	public PlayScreen playScreen;
	public GameOverScreen gameOverScreen;

	@Override
	public void create () {
		current = this;
		batch = new SpriteBatch();
		saveManager = new SaveManager("Save/save.json");
		startScreen = new StartScreen(this);
		gameOverScreen = new GameOverScreen(this);
		this.setGameState(PlayState.NOTSTARTED);
		teleporting = "false";

	}

	public void testAddSave(){
		saveManager.loadFromFile();
		Save demoSave = new Save("Save 3");
		AuberInfo playerInfo = new AuberInfo();
		playerInfo.position = new Position(50,200);
		demoSave.auberInfo = playerInfo;
		saveManager.add(demoSave);
		saveManager.saveToFile();
	}

	/**
	 * Initialises the game's play screen
	 * @param config the type of game we are going to play, including new game, loaded game and demo game.
	 */
	public void createPlayScreen(PlayConfig config){
		playScreen = new PlayScreen(this, config);
		setGameState(config.state);
	}

	public PlayState getState(){
		return this.state;
	}

	public void setGameState(PlayState state){
		if(this.state != state){
			switch (state){
				case NOTSTARTED:
					setScreen(startScreen);
					break;
				case PLAYING:
					setScreen(playScreen);
					break;
				case TELEPORTING:
					setScreen(new TeleportScreen(this));
					break;
				case win:
					setScreen(gameOverScreen);
					break;
				case LOST:
					setScreen(gameOverScreen);
					break;
				default:
					break;
			}
			this.state = state;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
