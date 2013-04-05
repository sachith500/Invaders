package yasith.invaders.actors;

import static yasith.invaders.GameConstants.BULLET_VELOCITY;
import static yasith.invaders.GameConstants.LOG_TAG;

import java.util.ArrayList;

import yasith.invaders.GameState;
import yasith.util.DynamicActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

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
		this.setX(x);
		this.setY(y);
		
		// Set the bullet's velocity
		mVelocity = BULLET_VELOCITY;
	}
	
	/*
	 * Callback function when the bullet is hit.
	 */
	public void hit(){
		// TODO: Show a particle and play a sound
		mIsHit = true;
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
		// Move the bullet, and update mOnScreen if the bullet
		// goes off the screen
		setY(getY() + (mVelocity * (float) mDir * delta));
		if(getY() > Gdx.graphics.getHeight() || getY() < 0){
			mOnScreen = false;
			return; // If the bullet's off the screen, it won't collide
		}
	
		// Bounding box for the bullet
		// We need to convert all coords, relative to groups and stage
		// to screen coordinates
		Vector2 coords = new Vector2(this.getX(),this.getY());
		Vector2 screencoords = this.localToStageCoordinates(coords);
		Rectangle bulletRect = 
				new Rectangle(screencoords.x, screencoords.y, getWidth(), getHeight());

		// TODO: Might want to add some effects or something here
		
		// If the bullet is directed at the invaders
		if(mDir == 1 && isInvaderCollision(bulletRect)) return;
		
		// Bullet is directed at the ship
		if(mDir == -1 && isShipCollision(bulletRect)) return;
	}
	
	
	/**
	 * Check if the bullet collides with the invaders
	 * 
	 * @return true if collides, false otherwise	
	 */
	private boolean isInvaderCollision(Rectangle bulletRect){
		// Go through each invader and see if the bullet collides
		ArrayList<Invader> lst = GameState.getInstance().getInvaderList();
		for(Invader inv: lst){

			// We need to convert all coords, relative to groups and stage
			// to screen coordinates
			Vector2 coords = new Vector2(inv.getX(),inv.getY());
			Vector2 screencoords = this.localToStageCoordinates(coords);

			// Bounding box for the invader
			Rectangle invRect = new Rectangle
					(screencoords.x, screencoords.y, inv.getWidth(), inv.getHeight());

			// If the bullet is inside the invader, kill it
			// then destroy the bullet
			if(invRect.overlaps(bulletRect)){
				Gdx.app.log(LOG_TAG, "Bullet hit Invader:" + 
						bulletRect.toString() + " hit " + invRect.toString());
				inv.hit();
				hit();
				return true; // We don't want one bullet to kill 2 invaders
			}
		}
		
		return false; // Didn't collide
	}
	
	/**
	 * Check if the bullet collides with the ship
	 * 
	 * @return true if collides, false otherwise
	 */
	private boolean isShipCollision(Rectangle bulletRect){
		
		// Convert coordinates to screen coordinates
		Ship ship = GameState.getInstance().getShip();
		Vector2 coords = new Vector2(ship.getX(),ship.getY());
		Vector2 screencoords = this.localToStageCoordinates(coords);

		
		// Bounding box for the ship
		Rectangle shipRect = new Rectangle(screencoords.x, screencoords.y, 
				ship.getWidth(), ship.getHeight());
		
		// Check for collision
		if(shipRect.overlaps(bulletRect)){
			Gdx.app.log(LOG_TAG, "Ship got hit!!!");
			
			ship.hit();
			hit();
			return true; // Collision
		}
		
		return false; // No collision
	}
}
