package yasith.invaders.actors;

import java.util.*;

import yasith.invaders.GameState;
import yasith.util.DynamicActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;

import static yasith.invaders.GameConstants.*;

/**
 * Represents the ship the player controls
 */
public class Ship extends DynamicActor{

	private ArrayList<Bullet> mBullets; // Holds the bullets

	/**
	 * Creates new instance of a ship
	 */
	public Ship(){
		super(GameState.getInstance().atlas.createSprite("ship0"));
		
		mBullets = new ArrayList<Bullet>();
		mVelocity = SHIP_VELOCITY;
	}
	
	/**
	 * Moves the ship by x * velocity. No need to change y coord.
	 */
	public void move(float dx){
		setX(getX() + (dx * mVelocity));
	
		float maxX = Gdx.graphics.getWidth() - mSprite.getWidth();
		setX(getX() > maxX ? maxX : getX()); // Stop at the right edge
		setX(getX() < 0 ? 0 : getX()); // Stop at the left edge
	}
	
	/**
	 * Fires a bullet up, only 2 bullets allowed at any given time
	 */
	public void fire(){
		// Limit the total number of bullets that can be fired,
		// at any given time.
		// TODO: Should go into GameConstants
		if(mBullets.size() < BULLET_LIMIT){
			// Bullet should fire from the middle of the ship
			Bullet b = new Bullet(getX() + mSprite.getWidth() * 0.5f, getY(), 1);
			mBullets.add(b);
			getStage().addActor(b);
		}
	}
	
	/**
	 * Updates the ship, and invokes related actions
	 */
	@Override
	public void act(float delta){
		
		// Update the position of the ship
		float dx = 0.0f;
		// If the Accelerometer is available use that
		if(Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)){
			dx = Gdx.input.getAccelerometerY() * delta;
		} else { // else assume we are on the computer
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				dx = delta;
			} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				dx = -delta;
			}
		}
		
		move(dx);
		// End of Updating the position of the ship
	
		// Fire a bullet when the user taps the screen
		if(Gdx.input.justTouched()){
			fire();
		}
	
		// Update the Bullets, and draw them.
		Iterator<Bullet> it = mBullets.iterator();
		while(it.hasNext()){
			Bullet b = it.next();
			
			// If the bullet is dead remove
			// Using iterator's remove is the only removal with
			// specified behavior
			if(!b.isAlive()){
				it.remove();
				
				// Remove from the stage
				//getStage().removeActor(b);
			}
		}
		
	}

	/**
	 * Callback function to handle events when the ship gets hit
	 * by a bullet
	 */
	public void hit() {
		// TODO: Notify the user 
		// Do we need an OSD ?
		
		// Reduce the lives by one
		int lives = GameState.getInstance().getLives();
		GameState.getInstance().setLives(lives - 1);
	}
}
