package yasith.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents a bullet
 */
public class Bullet extends DynamicActor{

	// 1 for up, -1 for down. This makes it easier to move the bullets
	// using libgdx coordinate system (bottom-left = (0,0)) 
	private int mDir;
	
	private boolean mIsHit = false;
	private boolean mOnScreen = true;
	
	/**
	 * Creates a new instance of a bullet
	 * 
	 * @param dir the direction of the bullet (1 for up, -1 for down)
	 */
	public Bullet(float x, float y, int dir){
		// TODO: cache the bullet sprite, since it's used a lot
		super(GameState.getInstance().atlas.createSprite("bullet"));
		
		// If the bullet is going down, we need to turn it around
		if(dir == -1) mSprite.setScale(1.0f, -1.0f);
		
		mDir = dir;
		this.x = x;
		this.y = y;
		
		mVelocity = 200.0f;
	}
	
	/**
	 * Return x coordinate of the bullet
	 */
	public float x(){
		return x;
	}
	
	/**
	 * Return y coordinate of the bullet
	 */
	public float y(){
		return y;
	}
	
	/**
	 * Update the position of the bullet
	 */
	public void move(float delta){
		// TODO: Should deltaTime be in GameState ?
		y += mVelocity * (float) mDir * delta;
		
		if(y > Gdx.graphics.getHeight() || y < 0) mOnScreen = false;
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
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}
	
	/*
	 * Returns true if the bullet is still alive
	 * (On screen and haven't hit anything)
	 */
	public boolean isAlive(){
		return (mOnScreen && (!mIsHit));
	}

	@Override
	public void act(float delta) {
		move(delta);
	}
}
