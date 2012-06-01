package yasith.invaders;

public class GameConstants {

	// Number of invaders
	public static final int INVADER_COLS = 9;
	public static final int INVADER_ROWS = 4;
	
	// Starting position of the invaders grid (top-left)
	public static final float START_X = 10.0f;
	public static final float START_Y = 70.0f;
	// Space between each invader
	public static final float OFFSET_X = 70.0f;
	public static final float OFFSET_Y = 50.0f;
	
	// Speed of the invaders
	public static final float INVADER_VELOCITY = 50.0f;
	
	// Maximum number of bullets invaders can fire at once
	public static final int INVADER_BULLET_CAP = 3;
	
	// Sprites used for each level of invaders
	// Should have INVADER_ROWS entries
	public static final String INVADER_SPRITES[] = 
			new String[]{"invader", "invader", "invader", "invader"};
	
	// Scores for destroying invaders at different rows
	public static final int INVADER_SCORES[] = new int[]{10, 20, 30, 40, 50};
	
	// Number of lives the player begins with
	public static final int PLAYER_LIVES = 3;
	
	// Tag for Logging
	public static final String LOG_TAG = "Invaders";
	
	// Bullet's properties
	public static final float BULLET_VELOCITY = 200.0f;
	public static final int BULLET_LIMIT = 2;
	
	// Ship's properties
	public static final float SHIP_VELOCITY = 200.0f;
}
