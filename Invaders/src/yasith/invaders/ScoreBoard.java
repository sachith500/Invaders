package yasith.invaders;

/**
 * Holds the score, achievement related data
 */
public class ScoreBoard {

	
	// Singleton instance
	private static ScoreBoard sInstance = null;
	
	// Score for this session
	private int mScore;
	
	// Highest score for all the games played
	private int mHighScore;
	
	private ScoreBoard() {
		mScore = 0;
		loadHighScore();
	}
	
	/**
	 * Returns the ScoreBoard instance
	 */
	public static synchronized ScoreBoard getInstance() {
		if(sInstance == null){
			sInstance = new ScoreBoard();
		}
		return sInstance;
	}
	
	/**
	 * Adds points to the score
	 */
	public void addPoints(int points) {
		mScore += points;
	}
	
	/**
	 * Returns the score
	 */
	public int getScore() {
		return mScore;
	}
	
	/**
	 * Resets the score
	 */
	public void reset(){
		mScore = 0;
	}
	
	/**
	 * Loads and sets the High Score
	 */
	private void loadHighScore() {
		// TODO implement loading logic
		mHighScore = 0;
	}
}
