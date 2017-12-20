package com.cosmosengine.levels;

import java.awt.Color;
import java.awt.Graphics;

import com.cosmosengine.GameCanvas;
import com.cosmosengine.LevelLoader;
import com.cosmosengine.SoundManager.SoundLoader;
import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.entities.texture.MessagePopupEntity;

public class LevelTwo extends LevelLoader {

	MessagePopupEntity levelThreeScript;
	CosmosSprite loadImage;

	public LevelTwo(GameCanvas game) {
		super(game);
		this.name = "Inner Astroid - Tunnel";
		bg_sound = SoundLoader.get().getSound("a_s1.wav");

		level = ":::::::::::::::::wwwwwww:::::::::wwwwww::::::::::::\n"
				+ ":::::::::::::wwwwww::wwwww::::::ww:#::w::::::::::::\n"
				+ "::::::::::::ww:::::::S:::wwwwwwwww::::ww:::::::::::\n"
				+ ":::::::::::www::::ww::wwww::::::::::::www::::::::::\n"
				+ "::::::::::wwww:::wwddddw::::wwwwwwwwwwddwww::::::::\n"
				+ "::::::::::wwww::::wwwwww:::wwwwdddddddwwddww:::::::\n"
				+ "::::::::::www:::::::::::::wwwwwwwwwwwwwd#:dw:::::::\n"
				+ "::::::::::www:::::::ww::::::::::::#::#:::::w:::::::\n"
				+ "::::::::::wwww:::::www::::wwwwwwwwwwwwwd::dw:::::::\n"
				+ "::::::::::wwwww:#:wwwwww:::wwwwdddddddwwddww:::::::\n"
				+ "::::::::::wwwwww:wwddddw::::wwwwwwwwwwddwww::::::::\n"
				+ ":::::::::::::::wwwwwwwwwww:::::::::#::www::::::::::\n"
				+ ":::::::::::::::::::::::::wwwwwwwww::::ww:::::::::::\n"
				+ "::::::::::::::::::::::::::::::::ww::::w::::::::::::\n"
				+ ":::::::::::::::::::::::::::::::::wwwww:::::::::::::\n"
				+ ":::::::::::::::::::::::::::::::::::::::::::::::::::\n";

		String complex = "[terminal;LevelTwoA]\n" + "[terminal;LevelThree]\n"
				+ "[tz;40;40;levelThreeScript;0]\n"
				+ "[gt;nuke;1;80;40;Explore the Asteroid]\n" + "[hp;50]\n"
				+ "[terminal;LevelTwoB]\n";

		levelThreeScript = new MessagePopupEntity(game, 40,
			"In order to proceed through-n" + "this gate two powerful-n"
					+ "reagents are required. Further-n"
					+ "exploration is adviced.", null);

		this.levelConstantObjects.add(levelThreeScript);

		// load background image
		//background = ImageLoader.get().getSprite("background.jpg");
		loadImage = ImageLoader.get().getSprite("loading_screen.png");
		game.player.setCanMove(true);
		game.setObjective("Explore the Asteroid.");
		// load level
		loadLevel(complex);
	}

	@Override
	public void onLoad(Graphics g) {
		loadImage.draw(g, 0, 0);
		g.setColor(Color.WHITE);
		isOnLoadFinished = true;
		if (isOnLoadFinished) {
			// load game sound
			bg_sound.playSound();
		}
	}

	@Override
	public void act(String source) {
		if (source.equals("levelThreeScript")) {
			levelThreeScript.setAlive(true);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new LevelTwo(game);
	}

}
