package yasith.invaders.screens;

import yasith.invaders.GameState;
import yasith.util.AbstractScreen;
import yasith.util.Button;
import yasith.util.ButtonListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainScreen extends AbstractScreen implements ButtonListener{

	
	
	public MainScreen(){
		super();
	}
	
	@Override
	public void show(){
		super.show();
		
	
		String buttons[] = {"Play", "Score", "Store", "Options"};
		
		float posY = Gdx.graphics.getHeight() * 0.75f;
		float posX = Gdx.graphics.getWidth() * 0.5f;
		
		float padding = 10.0f;
		
		TextureAtlas atlas = GameState.getInstance().atlas;
		
		for(String str: buttons){
			Button button = new Button(
					atlas.createSprite("button"+str),
					this,
					str);
			
			button.setPosition(posX - (button.width * 0.5f), posY);
			
			posY -= button.height + padding;
			
			mStage.addActor(button);
		}
		
	}

	@Override
	public void onClickListener(String key) {
		if(key == "Play"){
			GameState.getInstance().getGame().setScreen(new GameScreen());
		}
	}
}
