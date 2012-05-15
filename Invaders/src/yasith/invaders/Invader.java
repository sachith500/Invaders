package yasith.invaders;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Represents an invader
 */
public class Invader {

	private float mX;
	private float mY;
	private float mVelocity = GameConstants.INVADER_VELOCITY;
	
	private Sprite mSprite = null;
	
	GameState gameState;

	/**
	 * Creates new instance of an invader
	 */
	public Invader(float x, float y, int row) {
		
		setPosition(x, y);
		
		gameState = GameState.getInstance();
		TextureAtlas atlas = gameState.atlas;
		// TODO: Cache the sprites
		mSprite = atlas.createSprite(GameConstants.INVADER_SPRITES[row]);
	}
	
	/**
	 * Renders the invader sprite on the given SpriteBatch
	 */
	public void render(SpriteBatch batch) {
		mSprite.setPosition(mX, mY);
		mSprite.draw(batch);
	}
	
	/**
	 * Sets the position of the sprite
	 */
	public void setPosition(float x, float y){
		mX = x;
		mY = y;
	}
	
	/**
	 * Moves the invader by x * velocity and y * velocity
	 */
	public void move(float x, float y){
		mX += x * mVelocity;
		mY += y * mVelocity;
	}
}
