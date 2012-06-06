package yasith.invaders.actors;

import java.util.ArrayList;

import yasith.invaders.GameState;
import yasith.util.DynamicActor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import static yasith.invaders.GameConstants.*;

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
		y += mVelocity * (float) mDir * delta;
		if(y > Gdx.graphics.getHeight() || y < 0){
			mOnScreen = false;
			return; // If the bullet's off the screen, it won't collide
		}
	
		// Bounding box for the bullet
		// We need to convert all coords, relative to groups and stage
		// to screen coordinates
		Vector2 bulletCoord = new Vector2();
		Widget.toScreenCoordinates(this, bulletCoord);
		Rectangle bulletRect = 
				new Rectangle(bulletCoord.x, bulletCoord.y, width, height);

		// Go through each invader and see if the bullet collides
		// If the bullet is coming from the ship (mDir = 1)
		if(mDir == 1){
		
			ArrayList<Invader> lst = GameState.getInstance().getInvaderList();
			for(Invader inv: lst){
				
				// We need to convert all coords, relative to groups and stage
				// to screen coordinates
				Vector2 invCoord = new Vector2();
				Widget.toScreenCoordinates(inv, invCoord);
				
				// Bounding box for the invader
				Rectangle invRect = new Rectangle
						(invCoord.x, invCoord.y, inv.width, inv.height);
				
				// If the bullet is inside the invader, kill it
				// then destroy the bullet
				if(invRect.overlaps(bulletRect)){
					Gdx.app.log(LOG_TAG, "Bullet hit Invader:" + 
							bulletRect.toString() + " hit " + invRect.toString());
					inv.hit();
					hit();
					return; // We don't want one bullet to kill 2 invaders
				}
			}
		} else{ // Bullet is directed at the ship
			
			// Convert coordinates to screen coordinates
			Vector2 shipCoord = new Vector2();
			Ship ship = GameState.getInstance().getShip();
			Widget.toScreenCoordinates(ship, shipCoord);
			
			// Bounding box for the ship
			Rectangle shipRect = new Rectangle(shipCoord.x, shipCoord.y, 
					ship.width, ship.height);
			
			// Check for collision
			if(shipRect.overlaps(bulletRect)){
				Gdx.app.log(LOG_TAG, "Ship got hit!!!");
				
				ship.hit();
				hit();
				return;
			}
		}
	}
}
