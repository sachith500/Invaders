package yasith.util;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import static yasith.invaders.GameConstants.*;

public class Button extends Actor {
	
	ButtonListener mListener;
	
	// Sprites for normal and touched stages
	private Sprite mNormalSprite;
	private Sprite mTouchedSprite;

	// Key to differentiate buttons in the onClickListener
	private String mKey;
	
	// The sprite is being touched
	private boolean mTouched;
	
	private Rectangle mBounds;
	
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
		
		if(mTouched){
			selectedSprite = mTouchedSprite;
		}
		
		selectedSprite.setPosition(x, y);
		selectedSprite.setScale(scaleX, scaleY);
		
		selectedSprite.draw(batch, parentAlpha);
	}

	@Override
	public Actor hit(float x, float y) {
		
		Gdx.app.log(LOG_TAG, "X:" + x + " Y: " + y);
		
		if(mBounds.contains(x, y)){
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
		
		setBounds();
	}
	
	/**
	 * Sets the mBounds rectangle
	 */
	private void setBounds(){
		Vector2 coord = new Vector2();
		Widget.toScreenCoordinates(this, coord);
		
		mBounds = new Rectangle(coord.x, coord.y, width, height);
	}
}
