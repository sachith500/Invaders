package yasith.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Holds information about the current state of the Game
 */
public class GameState {

	// Singleton instance
	private static GameState sInstance = null;
	
	// variables used to store the game state
	TextureAtlas atlas; // no use of the atlas being private

	private int lives = 0; // remaining lives
	
	// private constructor prevents initialization outside of GameState
	private GameState() {
		atlas = new TextureAtlas(Gdx.files.internal("images/pack"));
	}

	/** Returns the instance of GameState if there is one, otherwise
	 *	create a new one.
	 */
	public static synchronized GameState getInstance() {
		if(sInstance == null){
			sInstance = new GameState();
		}
		return sInstance;
	}

	/**
	 * Return the remaining lives of the player
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Set the remaining lives of the player
	 */
	public void setLives(int lives)	{
		this.lives = lives;
	}
}
