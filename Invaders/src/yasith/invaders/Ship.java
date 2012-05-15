package yasith.invaders;

import java.util.*;

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
	private ArrayList<Bullet> mBullets; // Holds the bullets
	
	GameState gameState;

	/**
	 * Creates new instance of a ship
	 */
	public Ship(){
		gameState = GameState.getInstance();
		
		mSprite = gameState.atlas.createSprite("ship0");
		
		mBullets = new ArrayList<Bullet>();
	}

	/**
	 * Renders the ship sprite on the given SpriteBatch
	 */
	public void render(SpriteBatch batch){
		mSprite.setPosition(mX, mY);
		mSprite.draw(batch);
		
		// Update the Bullets, and draw them.
		Iterator<Bullet> it = mBullets.iterator();
		while(it.hasNext()){
			Bullet b = it.next();
			b.move();
			b.render(batch);
			
			// If the bullet is dead remove
			// Using iterator's remove is the only removal with
			// specified behavior
			if(!b.isAlive()) it.remove();
		}
		
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
	
	/**
	 * Return the x coordinate of the ship
	 */
	public float x() { 
		return mX;
	}
	
	/**
	 * Return the y coordinate of the ship
	 */
	public float y() {
		return mY;
	}
	
	/**
	 * Fires a bullet up, only 2 bullets allowed at any given time
	 */
	public void fire(){
		// Limit the total number of bullets that can be fired,
		// at any given time.
		// TODO: Should go into GameConstants
		if(mBullets.size() < 2){
			// Bullet should fire from the middle of the ship
			mBullets.add(new Bullet(mX + mSprite.getWidth() * 0.5f, mY, 1));
		}
	}
}
