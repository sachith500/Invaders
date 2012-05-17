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
	
	// Sprites used for each level of invaders
	// Should have INVADER_ROWS entries
	public static final String INVADER_SPRITES[] = 
			new String[]{"invader", "invader", "invader", "invader"};
	
	// Number of lives the player begins with
	public static final int PLAYER_LIVES = 3;
	
	// Tag for Logging
	public static final String LOG_TAG = "Invaders";
}
