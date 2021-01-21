package commygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import commygdx.game.screens.GameOverScreen;
import commygdx.game.screens.IntroScreen;
import commygdx.game.screens.PlayScreen;

public class AuberGame extends com.badlogic.gdx.Game {
	public SpriteBatch batch;
	public static final int V_WIDTH=2560;
	public static final int V_HEIGHT=1440;
	public static final int ZOOM=12;
	public String onTeleport;
	public Screen screen;
	public boolean demo;
	//game state -1= intro screen 0=exit introscreen 1=playing 2=win 3=lost
	public int gameState;
	private Screen introScreen;
	private GameOverScreen gameOverScreen;



	@Override
	public void create () {
		batch = new SpriteBatch();
		introScreen=new IntroScreen(this);
		gameOverScreen=new GameOverScreen(this);
		setScreen(introScreen);
		onTeleport="false";
		gameState=-1;
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

		if (onTeleport!="true" && onTeleport!="false"){
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
