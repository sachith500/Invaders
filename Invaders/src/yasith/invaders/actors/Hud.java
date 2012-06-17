package yasith.invaders.actors;

import java.util.ArrayList;

import yasith.invaders.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Manages the Heads Up Display
 */
public class Hud extends Actor{
	
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
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		
		// Draw one heart for each life on the top-left corner
		// spaced with 5px, starts with 5px offset from left
		for (int i = 0; i < gameState.getLives(); i++) {
			mHeart.setPosition(i * (mHeart.getWidth() + 5.0f) + 5.0f, 
					Gdx.graphics.getHeight() - mHeart.getHeight());
			mHeart.draw(batch, 0.9f); // draw heart with 90% opacity
		}	
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}
}
