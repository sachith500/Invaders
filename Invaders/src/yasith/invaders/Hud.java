package yasith.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Manages the Heads Up Display
 */
public class Hud {
	
	private Sprite mHeart = null;
	
	GameState gameState;
	
	/**
	 * Create a new instance of Hud
	 */
	public Hud(){
		gameState = GameState.getInstance();
		
		mHeart = gameState.atlas.createSprite("heart");
	}

	/**
	 * Draw the Heads Up Display
	 * 
	 * - Hearts, to represent lives in the top-left corner
	 * - More ?
	 */
	public void render(SpriteBatch batch){
		
		// Draw one heart for each life on the top-left corner
		// spaced with 5px, starts with 5px offset from left
		for (int i = 0; i < gameState.getLives(); i++) {
			mHeart.setPosition(i * (mHeart.getWidth() + 5.0f) + 5.0f, 
					Gdx.graphics.getHeight() - mHeart.getHeight());
			mHeart.draw(batch, 0.9f); // draw heart with 90% opacity
		}	
	}
}
