package com.cosmosengine.levels;

import java.awt.Color;
import java.awt.Graphics;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.LevelLoader;
import com.cosmosengine.SoundManager.SoundLoader;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.entities.texture.MessageEntity;

/**
 * Beta Level for testing purposes only
 */
public class Credits extends LevelLoader {

	private MessageEntity script1;

	public Credits(GameCanvas game) {
		super(game);
		this.name = "Credits";
		bg_sound = SoundLoader.get().getSound("menu/credits.wav");
		// load level
		level = "::::::::::::::::::wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n"
				+ "::::::::::::::::::wS:::::::HHAAHAMANREUGddddCddddddsddw\n"
				+ "::::::::::::::::::w::::::::dddEdddddddddddddgddHddddUdw\n"
				+ "::::::::::::::::::wdddddddddddddddddddEdddgdddddNdGdddw\n"
				+ "::::::::::::::::::wdddgddddgddgddddRddddddHddddddddddAw\n"
				+ "::::::::::::::::::wdGdddndddddHddddddddUdddUddNdddddddw\n"
				+ "::::::::::::::::::wddddddnddgddddddRddnddddddddddUddddw\n"
				+ "::::::::::::::::::wddgddgddddddgddddddGdddMddAddddndRdw\n"
				+ "::::::::::::::::::wdddddddddsddddddgddddddddddddddddddw\n"
				+ "::::::::::::::::::wdgdddgddddddnddsddddEdddddUddddddddw\n"
				+ "::::::::::::::::::wdddddddddddddddddddddddRdddddMdddddw\n"
				+ "::::::::::::::::::wdddddddddddddddddddEdddgdddddNdGdddw\n"
				+ "::::::::::::::::::wdddgddddgddgddddRddddddHddddddddddAw\n"
				+ "::::::::::::::::::wdGdddndddddHddddddddUdddUddNdddddddw\n"
				+ "::::::::::::::::::wddddddnddgddddddRddnddddddddddUddddw\n"
				+ "::::::::::::::::::wddgddgddddddgddddddGdddMddAddddndRdw\n"
				+ "::::::::::::::::::wdddddddddsddddddgddddddddddddddddddw\n"
				+ "::::::::::::::::::wdgdddgddddddnddsddddEdddddUddddddddw\n"
				+ "::::::::::::::::::wdddddddddddddddddddddddRdddddMdddddw\n"
				+ "::::::::::::::::::wdddddddddddddddddddEdddgdddddNdGdddw\n"
				+ "::::::::::::::::::wdddgddddgddgddddRddddddHddddddddddAw\n"
				+ "::::::::::::::::::wdGdddndddddHddddddddUdddUddNdddddddw\n"
				+ "::::::::::::::::::wddddddnddgddddddRddnddddddddddUddddw\n"
				+ "::::::::::::::::::wddgddgddddddgddddddGdddMddAddddndRdw\n"
				+ "::::::::::::::::::wdddddddddsddddddgddddddddddddddddddw\n"
				+ "::::::::::::::::::wdgdddgddddddnddsddddEdddddUddddddddw\n"
				+ "::::::::::::::::::wdddddddddddddddddddddddRdddddMdddddw\n"
				+ "::::::::::::::::::wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n";

		// load background image
		background = ImageLoader.get().getSprite("credits.png");
		game.player.setCanMove(true);
		script1 = new MessageEntity(
			game,
			null,
			null,
			null,
			50,
			50,
			-1,
			-1,
			75,
			"Credits...-n"
					+ "-n"
					+ "Main Developers:-n"
					+ "Andres Schmois        -n"
					+ "Miguel Sotolongo        -n"
					+ "-n"
					+ "Graphic Designer:-n"
					+ "Alex Garcia        -n"
					+ "-n"
					+ "Special Thanks to:-n"
					+ "Michael Gomez for providing music.                -n"
					+ "Brandon de la Uz for assisting with graphics.                -n"
					+ "Brent Lopez and Veda Cruz for voice acting.                -n"
					+ "-n" + "and...        -n" + "C. Charters!        -n"
					+ "-n" + "-n" + "The End... Free mining!                ");
		// load level
		loadLevel("");

		game.player.setDamagePower(100);
		game.setObjective("Congratulations you beat InSTaBILiTi!");
	}

	public void onLoad(Graphics g) {
		if (bg_sound != null && !bg_sound.isPlaying()) {
			bg_sound.playSound();
		}
		// load loading screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, CosmosConstants.WIDTH, CosmosConstants.HEIGHT);
		// act out loading text
		script1.act();
		script1.draw(g);
		isOnLoadFinished = script1.isDone();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Credits(game);
	}

	@Override
	public void act(String source) {
		// TODO Auto-generated method stub

	}

}
