package yasith.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents a bullet
 */
public class Bullet {

	private float mX;
	private float mY;
	private float mVelocity = 200.0f; // px per second
	
	// -1 for up, 1 for down. This makes it easier to move the bullets
	// using libgdx coordinate system (bottom-left = (0,0)) 
	private int mDir;
	
	private boolean isHit = false;
	
	private Sprite mSprite;

	GameState gameState;
	
	/**
	 * Creates a new instance of a bullet
	 * 
	 * @param dir the direction of the bullet (-1 for up, 1 for down)
	 */
	public Bullet(float x, float y, int dir){
		gameState = GameState.getInstance();
		
		// TODO: cache the bullet sprite, since it's used a lot
		mSprite = gameState.atlas.createSprite("bullet");
		
		// If the bullet is going down, we need to turn it around
		if(dir == 1) mSprite.setScale(0.0f, -1.0f);
		
		mDir = dir;
		mX = x;
		mY = y;
	}
	
	/**
	 * Return x coordinate of the bullet
	 */
	public float x(){
		return mX;
	}
	
	/**
	 * Return y coordinate of the bullet
	 */
	public float y(){
		return mY;
	}
	
	/**
	 * Update the position of the bullet
	 */
	public void move(){
		// TODO: Should deltaTime be in GameState ?
		mY += mVelocity * Gdx.graphics.getDeltaTime() * (float) mDir;
	}

	/*
	 * Callback function when the bullet is hit.
	 */
	public void hit(){
		// TODO: Show a particle and play a sound
	}

	/*
	 * Renders the bullet sprite on the given SpriteBatch
	 */
	public void render(SpriteBatch batch) {
		mSprite.setPosition(mX, mY);
		mSprite.draw(batch);
	}
}
