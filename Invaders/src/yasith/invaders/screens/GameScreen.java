package yasith.invaders.screens;

import java.util.ArrayList;
import java.util.Iterator;

import yasith.invaders.GameState;
import yasith.invaders.actors.Bullet;
import yasith.invaders.actors.Invader;
import yasith.invaders.actors.Ship;
import yasith.util.AbstractScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import static yasith.invaders.GameConstants.*;

/**
 * Directs the Game Play
 */
public class GameScreen extends AbstractScreen {
	
	private Ship mShip;
	
	// Controls the animation of the invaders
	private Group mInvadersGroup = null;
	
	// Holds the bullets fired by the invaders
	private ArrayList<Bullet> mInvaderBullets = null;

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
								i, j); // row,column of the invader
				
				// Add the invader to the Array List
				lst.add(invader);
				
				// Add the actor the Group, which will be added to the stage
				mInvadersGroup.addActor(invader);
			}
		}
		
		// Set the invader list in GameState
		GameState.getInstance().setInvaderList(lst);
		
		mInvaderBullets = new ArrayList<Bullet>();
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

	@Override
	public void render(float delta){
		super.render(delta);
		
		// Update the state of invader bullets
		Iterator<Bullet> it = mInvaderBullets.iterator();
		while(it.hasNext()){
			Bullet b = it.next();
			
			// Clean-up
			if(! b.isAlive()){
				it.remove();
				mStage.removeActor(b);
			}
		}
		
		
		// Add more Bullets if required
		
		int n = GameState.getInstance().getInvaderList().size();
		
		while(mInvaderBullets.size() < Math.min(n, INVADER_BULLET_CAP)){
			// TODO try catch
			// select a random invader
			int index = (int) (Math.random() * n);
			Invader invader = GameState.getInstance().getInvaderList().get(index); 
			
			// Get the coordinates of the invaders relative to the screen
			Vector2 coords = new Vector2();
			Widget.toScreenCoordinates(invader, coords);
			
			// Create a new bullet, add it to the list
			Bullet b = new Bullet(coords.x, coords.y, -1);
			mInvaderBullets.add(b);
			mStage.addActor(b);
		}
	}
}
