package yasith.invaders;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Starts the invaders game, and handles updating the game loop, and display.
 */
public class Game implements ApplicationListener {
	
	GameState gameState;
	
	private float mDelta; // The time since the last frame was drawn
	private SpriteBatch mBatch; // Use the sprite batch to draw the Sprites.

	private Ship mShip;
	private Hud mHud;
	
	@Override
	public void create() {
		
		mShip = new Ship();
		mShip.setPosition( Gdx.graphics.getWidth() * 0.5f, 50.0f);
		
		mHud = new Hud();
		
		mBatch = new SpriteBatch();
		
		gameState = GameState.getInstance();
		gameState.setLives(3);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		this.mDelta = Gdx.graphics.getDeltaTime();
		this.updatePositions();
	
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		mBatch.begin();
			mHud.render(mBatch);
			mShip.render(mBatch);
		mBatch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	/** Updates position of the ship
	 * 	
	 * 	On Android, uses the accelerometer to detect movement,
	 * 	on PC uses arrow keys.
	 */
	private void updatePositions(){
			
		float dx = 0.0f;
		if(Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)){
			dx = Gdx.input.getAccelerometerY() * mDelta;
		} else {
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				dx = mDelta;
			} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				dx = -mDelta;
			}
		}
		
		mShip.move(dx);
	}
}