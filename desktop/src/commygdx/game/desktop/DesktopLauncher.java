package commygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import commygdx.game.AuberGame;

import java.awt.*;


/***
 * Delegate for resolving constructors and factory methods.
 * Performs constructor resolution through argument matching.
 *
 * @author Zhikang Liu
 * @since 1.0
 */
public class DesktopLauncher {
	/**
	 * The main class, which is the entry.
	 * @param arg an array of string parsed by the entry.
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.x=0;
		config.y=0;
		config.width= Toolkit.getDefaultToolkit().getScreenSize().width;
		config.height= Toolkit.getDefaultToolkit().getScreenSize().height;

		new LwjglApplication(new AuberGame(), config);
	}
}
