package yasith.invaders;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.badlogic.gdx.tools.imagepacker.*;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class Main {
	public static void main(String[] args) {
		
		// Pack the textures
		packTextures();
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Invaders";
		cfg.useGL20 = false;
		cfg.width = 640;
		cfg.height = 480;
		
		new LwjglApplication(new InvadersGame(), cfg);
	}
	
	static void packTextures(){
		Settings settings = new Settings();
		settings.padding = 2;
		settings.maxWidth = 512;
		settings.maxHeight = 512;
		settings.incremental = true;
		
		TexturePacker.process(settings, 
				"../images", "../Invaders-android/assets/images");
		TexturePacker.process(settings, 
				"../images/", "../Invaders-desktop/images");
	}
}
