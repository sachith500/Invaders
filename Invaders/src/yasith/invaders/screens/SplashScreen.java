package yasith.invaders.screens;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import yasith.invaders.GameState;
import yasith.util.AbstractScreen;
import yasith.util.DynamicActor;

/**
 * Displays a splash screen, and starts the game
 */
public class SplashScreen extends AbstractScreen {
	
	private TextureAtlas mAtlas;
	private Image mSplashImage;

	@Override
	public void show(){
		super.show();
	
		// Get the splash screen image from the atlas
		mAtlas = GameState.getInstance().atlas;
		AtlasRegion splashRegion = mAtlas.findRegion("splash-screen");
		
		mSplashImage = new Image(splashRegion);
		
		// Splash image fades in quickly, stays there, and fades away
		Sequence actions = 
				Sequence.$(FadeIn.$(0.25f), Delay.$( FadeOut.$(0.75f), 1.5f));
		
		// After the splash screen the game should start
		actions.setCompletionListener(new OnActionCompleted(){
			
			@Override
			public void completed(Action action) {
				GameState.getInstance().getGame().setScreen(new MainScreen());
			}
		});
		
		mSplashImage.action(actions);
		mStage.addActor(mSplashImage);
	}
	
	
}
