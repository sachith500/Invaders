package yasith.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import yasith.util.AbstractScreen;

public class GameOverScreen extends AbstractScreen {
	
	boolean mWin;

	public GameOverScreen(boolean win){
		super();
		
		mWin = win;
	}
	
	@Override
	public void show(){
		super.show();
	}
	
	@Override 
	public void render(float delta){
		BitmapFont font = new BitmapFont();
		
		String str = mWin ? "YOU WIN!" : "YOU LOSE";
		
		mStage.getSpriteBatch().begin();
		font.draw(mStage.getSpriteBatch(), 
				str, 
				Gdx.graphics.getWidth() * 0.45f, 
				Gdx.graphics.getHeight() * 0.5f);
		
		mStage.getSpriteBatch().end();
	}
}
