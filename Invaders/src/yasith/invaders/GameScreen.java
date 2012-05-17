package yasith.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import static yasith.invaders.GameConstants.*;

/**
 * Directs the Game Play
 */
public class GameScreen extends AbstractScreen {
	
	private Ship mShip;

	/**
	 * Creates a new GameScreen Instance
	 */
	public GameScreen() {
		super();
		
		mShip = new Ship();
	}

	@Override
	public void show(){
		// Calls the draw functions of its' Actors
		super.show();
		
		Gdx.app.log(LOG_TAG, "Showing GameScreen");
		
		mShip.setPosition(100.0f, 100.0f);
		mStage.addActor(mShip);
		
		// Make the stage transparent
		// Then add a fade-in effect
		mStage.getRoot().color.a = 0f;
		mStage.getRoot().action(FadeIn.$(0.5f));
	}
}
