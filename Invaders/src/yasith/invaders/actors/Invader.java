package yasith.invaders.actors;

import static yasith.invaders.GameConstants.INVADER_SCORES;
import yasith.invaders.GameState;
import yasith.invaders.ScoreBoard;
import yasith.util.DynamicActor;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Represents an invader
 */
public class Invader extends DynamicActor{

	// Row and column of this invader on the invaders grid
	private int mRow;
	private int mCol;

	/**
	 * Creates new instance of an invader,
	 * Use createInvader factory method to get the Actor
	 */
	private Invader(Sprite sprite) {
		super(GameState.getInstance().atlas.createSprite("invader"));
	}
	
	/**
	 * Creates and returns a new instance of an invader
	 */
	public static Invader createInvader(float x, float y, 
			int row, int col){
		Sprite sprite = GameState.getInstance().getInvaderSprite(row);
		
		Invader invader = new Invader(sprite);
		invader.setPosition(x, y);
		invader.mRow = row;
		invader.mCol = col;
		
		return invader;
	}
	
	/**
	 * Sets the position of the sprite
	 */

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
	}

	/**
	 * Callback function when a bullet is hit.
	 * Not to be confused with the hit callback function used by Actor
	 * to get touch events.
	 */
	public void hit() {
		//TODO: Sound effect or something
		//markToRemove(true);
		
		// We don't want the same invader to get hit again
		GameState.getInstance().removeFromInvaderList(this);
		
		// Give the player points for destroying the invader
		ScoreBoard.getInstance().addPoints(INVADER_SCORES[mRow]);
	}

	/**
	 * Returns the row of the invader
	 */
	public int getRow(){
		return mRow;
	}
	
	/**
	 * Returns the column of the invader
	 */
	public int getCol(){
		return mCol;
	}
}
