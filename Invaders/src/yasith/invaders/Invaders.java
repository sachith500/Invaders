package yasith.invaders;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Logger;

public class Invaders implements ApplicationListener {
	
	private TextureAtlas atlas;
	private float delta; // The time since the last frame was drawn
	
	private SpriteBatch batch; // Use the sprite batch to draw the sprites
	
	private Sprite ship;
	private Sprite invader;
	private Sprite heart;
	
	private float shipX = 100.0f;
	private float shipY = 100.0f;
	private float shipVelocity = 50.0f; // px per second
	
	private int lives = 3;
	
	@Override
	public void create() {
		atlas = new TextureAtlas(Gdx.files.internal("images/pack"));
		
		ship = atlas.createSprite("ship0");
		invader = atlas.createSprite("invader");
		heart = atlas.createSprite("heart");
		
		batch = new SpriteBatch();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		this.delta = Gdx.graphics.getDeltaTime();
		this.updatePositions();
	
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
			this.drawUI();
			ship.draw(batch);
		batch.end();
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
	
	private void updatePositions(){
			
		if(Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)){
			shipX += Gdx.input.getAccelerometerY() * delta * shipVelocity;
		} else {
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				shipX += shipVelocity * delta;
			} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				shipX -= shipVelocity * delta;
			}
		}
		
		float end = Gdx.graphics.getWidth() - ship.getWidth();
		shipX = shipX < 0 ? 0 : shipX;
		shipX = shipX > end ? end : shipX; 
		
		ship.setPosition(shipX, shipY);
		
	}
	
	private void drawUI(){
		// Draw one heart for each life on the top-left corner
		for (int i = 0; i < lives; i++) {
			heart.setPosition(i * (heart.getWidth() + 5.0f) + 5.0f, Gdx.graphics.getHeight() - heart.getHeight());
			heart.draw(batch, 0.9f);
		}
	}
}
