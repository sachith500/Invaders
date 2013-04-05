package yasith.invaders.screens;

import yasith.invaders.GameState;
import yasith.util.AbstractScreen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Displays a splash screen, and starts the game
 */
public class SplashScreen extends AbstractScreen {
	
	private TextureAtlas mAtlas;
	private Image mSplashImage;

	@Override
	public void show(){
		super.show();
	
		// Get the splash screen image from the atlas via a sprite
		mAtlas = GameState.getInstance().atlas;
		Sprite sprite = mAtlas.createSprite("splash-screen");
		mSplashImage = new Image(sprite);
		
		// Splash image fades in quickly, stays there, and fades away
		SequenceAction actions = 
				Actions.sequence(Actions.fadeIn(0.25f), Actions.delay(1.5f, Actions.fadeOut(0.75f)),new Action(){
					public boolean act( float delta ) {
						GameState.getInstance().getGame().setScreen(new MainScreen());
						return true;
				}
				});
		
		// After the splash screen the game should start
		/*actions.setCompletionListener(new OnActionCompleted(){
			
			@Override
			public void completed(Action action) {
				GameState.getInstance().getGame().setScreen(new MainScreen());
			}
		});*/
		
		mSplashImage.addAction(actions);
		mStage.addActor(mSplashImage);
	}
	
	
}
