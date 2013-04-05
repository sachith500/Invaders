package yasith.invaders.screens;

import static yasith.invaders.GameConstants.INVADER_BULLET_CAP;
import static yasith.invaders.GameConstants.INVADER_COLS;
import static yasith.invaders.GameConstants.INVADER_ROWS;
import static yasith.invaders.GameConstants.OFFSET_X;
import static yasith.invaders.GameConstants.OFFSET_Y;
import static yasith.invaders.GameConstants.PLAYER_LIVES;
import static yasith.invaders.GameConstants.START_X;
import static yasith.invaders.GameConstants.START_Y;

import java.util.ArrayList;
import java.util.Iterator;

import yasith.invaders.GameState;
import yasith.invaders.ScoreBoard;
import yasith.invaders.actors.Bullet;
import yasith.invaders.actors.Hud;
import yasith.invaders.actors.Invader;
import yasith.invaders.actors.Ship;
import yasith.util.AbstractScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
//import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
//import com.badlogic.gdx.scenes.scene2d.actions.Forever;
//import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
//import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

/**
 * Directs the Game Play
 */
public class GameScreen extends AbstractScreen {
	
	private Ship mShip;
	
	// Controls the animation of the invaders
	private Group mInvadersGroup = null;
	
	// Holds the bullets fired by the invaders
	private ArrayList<Bullet> mInvaderBullets = null;
	
	// Heads-Up-Display
	// Displays Health, Score and Level
	private Hud mHud;

	/**
	 * Creates a new GameScreen Instance
	 */
	public GameScreen() {
		super();
		
		// Add the player's ship to the GameScreen
		mShip = new Ship();
		GameState.getInstance().setShip(mShip);
		
		mInvadersGroup = new Group();
		
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
		
		mHud = new Hud();
		GameState.getInstance().setLives(PLAYER_LIVES);
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
		mInvadersGroup.setX(0.0f);
		mInvadersGroup.setY(375.0f);
		mStage.addActor(mInvadersGroup);
		
		// Add the Heads-Up-Display
		mStage.addActor(mHud);
		
		// Animate the invaders as coming down
		MoveByAction right = new MoveByAction();
		right.setAmount(100.0f, 0);
		right.setDuration(5.0f);
		MoveByAction down = new MoveByAction();
		right.setAmount(0.0f, -10.0f);
		right.setDuration(2.0f);
		MoveByAction left = new MoveByAction();
		right.setAmount(-100.0f, 0);
		right.setDuration(5.0f);
		RepeatAction forever = new RepeatAction();
		forever.setActor(mInvadersGroup);
		forever.setAction((Actions.sequence(right,down,left,down)));
		forever.setCount(-1); //-1 is FOREVER
		forever.act(0);
		// Make the stage transparent
		// Then add a fade-in effect
		mStage.getRoot().getColor().a = 0f;
		mStage.getRoot().addAction(Actions.fadeIn(0.5f));
		
		// Reset the score, before starting the game
		ScoreBoard.getInstance().reset();
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
				//mStage.removeActor(b);
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
			Vector2 coords = new Vector2(invader.getX(),invader.getY());
			Vector2 screencoords = invader.localToStageCoordinates(coords);
			// Create a new bullet, add it to the list
			Bullet b = new Bullet(screencoords.x, screencoords.y, -1);
			mInvaderBullets.add(b);
			mStage.addActor(b);
		}
		
		GameState state = GameState.getInstance();
		if(state.getInvaderList().size() == 0){ // Won the game
			state.getGame().setScreen(new GameOverScreen(true));
		}else if(state.getLives() == 0){ // Lost the game
			state.getGame().setScreen(new GameOverScreen(false));
		}
	}
}
