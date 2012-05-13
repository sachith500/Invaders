package yasith.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents the ship the player controls
 */
public class Ship {

	private float mX = 0.0f;
	private float mY = 0.0f;
	private float mVelocity = 50.0f; // px per second
	
	private Sprite mSprite = null;
	
	GameState gameState;

	/**
	 * Creates new instance of a ship
	 */
	public Ship(){
		gameState = GameState.getInstance();
		
		mSprite = gameState.atlas.createSprite("ship0");
	}

	/**
	 * Renders the ship sprite on the given SpriteBatch
	 */
	public void render(SpriteBatch batch){
		mSprite.setPosition(mX, mY);
		mSprite.draw(batch);
	}

	/**
	 * Sets the position of the ship
	 */
	public void setPosition(float x, float y){
		mX = x;
		mY = y;
	}
	
	/**
	 * Moves the ship by x * velocity. No need to change y coord.
	 */
	public void move(float x){
		mX += x * mVelocity;
	
		float maxX = Gdx.graphics.getWidth() - mSprite.getWidth();
		mX = mX > maxX ? maxX : mX; // Stop at the right edge
		mX = mX < 0 ? 0 : mX; // Stop at the left edge
	}
}
