package yasith.invaders;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * An Actor created with a Sprite
 */
public abstract class DynamicActor extends Actor {
	
	protected Sprite mSprite;
	protected float mVelocity;

	/**
	 * Creates a new instance of a Dynamic Actor with the given sprite
	 */
	public DynamicActor(Sprite sprite){
		mSprite = sprite;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// Prepare the sprite before drawing
		mSprite.setPosition(x, y);
		mSprite.setScale(scaleX, scaleY);
		
		// Draw the sprite
		mSprite.draw(batch, parentAlpha);
	}

	@Override
	public Actor hit(float x, float y) {
		// We don't need touch detection for our Actors yet
		return null;
	}
	
	@Override
	public abstract void act(float delta);
}
