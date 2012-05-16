package yasith.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class AbstractScreen implements Screen{

	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 480;
	
	private final Stage mStage;
	private final Game mGame;
	
	private SpriteBatch mBatch;
	
	public AbstractScreen(Game game){
		this.mGame = game;
		// Stretch the graphics to match the resolution to screen size
		this.mStage = new Stage(GAME_WIDTH, GAME_HEIGHT, true);
	}
	
	public SpriteBatch getSpriteBatch(){
		return this.mBatch;
	}
	
	// Screen Implementation
	
	@Override
	public void show(){
		Gdx.input.setInputProcessor(mStage);
	}
	
	@Override
	public void resize(int width, int height){
		
	}
	
	@Override
	public void render(float delta){
		// Update the actors
		mStage.act(delta);
		
		// Clear the screen with black
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Draw the actors
		mStage.draw();
	}
	
	@Override
	public void hide(){
		dispose();
	}
	
	@Override
	public void pause(){
		
	}
	
	@Override
	public void resume(){
		
	}
	
	@Override
	public void dispose(){
		if(mBatch != null) mBatch.dispose();
	}
	
}
