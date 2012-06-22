package yasith.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Button extends Actor {
	
	ButtonListener mListener;
	
	// Sprites for normal and touched stages
	private Sprite mNormalSprite;
	private Sprite mTouchedSprite;

	// Key to differentiate buttons in the onClickListener
	private String mKey;
	
	// The sprite is being touched
	private boolean mTouched;
	
	public Button(Sprite normalSprite, Sprite touchedSprite, ButtonListener listener, String key){
		mNormalSprite = normalSprite;
		mTouchedSprite = touchedSprite;
		mListener = listener;
		mKey = key;
		
		this.width = mNormalSprite.getWidth();
		this.height = mNormalSprite.getHeight();
		
		setPosition(0, 0);
	}
	
	public Button(Sprite sprite, ButtonListener listener, String key){
		this(sprite, sprite, listener, key);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Sprite selectedSprite = mNormalSprite;
	
		// TODO: Implement the touched functionality
		if(mTouched){
			selectedSprite = mTouchedSprite;
		}
		
		selectedSprite.setPosition(x, y);
		selectedSprite.setScale(scaleX, scaleY);
		
		selectedSprite.draw(batch, parentAlpha);
	}

	@Override
	public Actor hit(float x, float y) {
		
		// x and y are relative to the lower-left corner of sprite
		// 0,0 = lower-left corner
		// width, height = top-right corner
		if(x <= width && y <= height){
			mListener.onClickListener(mKey);
		}
		
		return null;
	}

	/**
	 * Sets the x, y coordinates of the Button
	 */
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
}
