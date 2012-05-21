package yasith.invaders.screens;

import java.util.ArrayList;

import yasith.invaders.GameState;
import yasith.invaders.actors.Invader;
import yasith.invaders.actors.Ship;
import yasith.util.AbstractScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

import static yasith.invaders.GameConstants.*;

/**
 * Directs the Game Play
 */
public class GameScreen extends AbstractScreen {
	
	private Ship mShip;
	
	// Array List to store the invaders for easy access
	// Controls the animation of the invaders
	private Group mInvadersGroup = null;

	/**
	 * Creates a new GameScreen Instance
	 */
	public GameScreen() {
		super();
		
		// Add the player's ship to the GameScreen
		mShip = new Ship();
		
		mInvadersGroup = new Group("invaders");
		
		// Add the invaders
		ArrayList<Invader> lst = new ArrayList<Invader>();
		
		for(int i = 0; i < INVADER_ROWS; ++i){
			for(int j = 0; j < INVADER_COLS; ++j){
				Invader invader = 
						Invader.createInvader(
								START_X + (j * OFFSET_X), // x-coord of invader
								START_Y - (i * OFFSET_Y), // y-coord of invader 
								i); // row of the invader
				
				// Add the invader to the Array List
				lst.add(invader);
				
				// Add the actor the Group, which will be added to the stage
				mInvadersGroup.addActor(invader);
			}
		}
		
		// Set the invader list in GameState
		GameState.getInstance().setInvaderList(lst);
	}

	@Override
	public void show(){
		super.show();
		
		// Add the ship to the stage's actor list
		// It's act-draw functions would get called repeatedly
		// TODO: Do something about the positioning
		mShip.setPosition(100.0f, 100.0f);
		mStage.addActor(mShip);
		
		// Add the Invaders Group to the stage,
		// Then animate them
		mInvadersGroup.x = 0.0f;
		mInvadersGroup.y = 375.0f;
		mStage.addActor(mInvadersGroup);
		
		// Animate the invaders as coming down
		mInvadersGroup.action(Forever.$(Sequence.$(
				MoveBy.$(100.0f, 0.0f, 5.0f),
				MoveBy.$(0.0f, -10.0f, 2.0f),
				MoveBy.$(-100.0f, 0.0f, 5.0f),
				MoveBy.$(0.0f, -10.0f, 2.0f)
				)));
		
		// Make the stage transparent
		// Then add a fade-in effect
		mStage.getRoot().color.a = 0f;
		mStage.getRoot().action(FadeIn.$(0.5f));
	}
}
