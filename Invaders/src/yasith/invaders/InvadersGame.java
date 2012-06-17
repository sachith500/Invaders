package yasith.invaders;

import java.util.*;

import yasith.invaders.screens.GameScreen;
import yasith.invaders.screens.SplashScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import static yasith.invaders.GameConstants.*;

/**
 * Starts the invaders game, and handles updating the game loop, and display.
 */
public class InvadersGame extends Game{
	
	public InvadersGame(){
		super();
	}
	
	@Override
	public void create() {
		setScreen(new SplashScreen());
		
		// Save the game, so we can use this to change the screen later
		GameState.getInstance().setGame(this);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		super.render();
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
	
	@Override
	public void setScreen(Screen screen){
		super.setScreen(screen);
		
		Gdx.app.log(LOG_TAG,
				"Setting Screen: " + screen.getClass().getSimpleName());
	}
}