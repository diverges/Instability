package com.cosmosengine.levels;

import java.awt.Color;
import java.awt.Graphics;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.LevelLoader;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.entities.interactive.TerminalEntity;
import com.cosmosengine.entities.texture.MessageEntity;
import com.cosmosengine.entities.texture.MessagePopupEntity;

/**
 * Alpha Level for testing purposes only.
 */
public class AlphaLevel extends LevelLoader {
	public static final String NAME = "alpha";
	MessageEntity script1;
	MessagePopupEntity message1;
	MessagePopupEntity message2;

	public AlphaLevel(GameCanvas game) {
		super(game);
		this.name = "Alpha Level";
		// draw level string
		level = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n"
				+ "wS:#:::::::::::::::::::::::::::::::::::ww:::::::::::::::::::::::::w\n"
				+ "w:::::::::::::#:::::::::::::::::::::::ww:::::::wwwwwww::::::::::::w\n"
				+ "w:::::::::::::#:::::::::::::::::::::::ww:::::::w:::::w::::::::::::w\n"
				+ "w:::wwwwwwwww:#:::#:::::::::::::::::::ww:::::::w:::::w::::::::::::w\n"
				+ "w:::::::::::::w:::#:::::::::::::::::ww:::::::::w:::::w::::::::::::w\n"
				+ "w:::::::::::::w:::#::::::::::::::::ww::::::::::wwwwwww::::::::::::w\n"
				+ "w:::::::::::::w:::wwwww::::::::wwww:::::::::::::::::::::::::::::::w\n"
				+ "w::eeee:::::::w:::w::::::::::::w::w::::ww:::::::::::::::::::::::::w\n"
				+ "w:::::::::::::::::w::::::::::::w::w::::ww:::::::::::::::::::::::::w\n"
				+ "wwwwwwwwwwwwwwwwwww::::::::::::w::w::::ww::::::wwwwww:::::www:::::w\n"
				+ "w::::::::::::::::::::::::::::::wwww::::ww:::::ww:::www::::www:::::w\n"
				+ "w::::::::::::::w:::::w::::::::::::::::ww::::ww:::::ww:::::www:::::w\n"
				+ "w::::::::::::::w:::::w:::::::::::::::::ww::::::::::ww:::::www:::::w\n"
				+ "w::::::::::::::w:::::w::::::wwwww::::::ww:::::::::ww::::::www:::::w\n"
				+ "w::::::::::::::w:::::w::::::wwwww::::::ww::::::::ww:::::::www:::::w\n"
				+ "w:::::::::::::::::::::::::::wwwww::::::::::::::wwwwww:::::www:::::w\n"
				+ "w:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::w\n"
				+ "w:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::w\n"
				+ "wwwwwwwwwwwddwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n"
				+ "wwwwwwwwwwwddwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n"
				+ "wwwwwwwwwwwddwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n"
				+ "wwwwwwwwwwdddddddwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n"
				+ "wwwwwwwwwwdwwwwddddddddddwwwwddddddddddddddwwwdddddwwwwwwwwwwwwwwww\n"
				+ "wwwwwwwwwwdwwwwdwwwwwwwddwwwwwwdddddwwwwddddwwwwddddddddwwwwwwwwwww\n"
				+ "wwwdddddddddddddwwwwwwwddwwwwwwwwwdddwwwdddddwwwdddwwwwwwwwwwwwwwww\n"
				+ "wwwddddddddwwwwdwwwwwwwddwwwwwwwddddwwwwddddddddddddddddddwwwwwwwww\n"
				+ "wwwdddddddddddddwwwwwwdddwwwwwwwddddwwwwwwwwwwwwwwwwwwwddwwwwwwwww\n"
				+ "wwwwwwwwwwwdwwwwwwwwwdddwwwwwwwwddwwwwwwwwdddwwwwwwwwwwwdwwwwwwwwww\n"
				+ "wwwwwwwwwwwdwwwwwwwwwdwwwwddddddddwwwwwwwwdddwwwwwwwwwwwdwwwwwwwwww\n"
				+ "wwwwwwwwwwwddddddddddddddddwwwwwddwwwwwwwwdddddddddddddddwwwwwwwwww\n"
				+ "wwwwwwwwwwwddddddddddddddddwwwwwddwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n"
				+ "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n";

		String complex = "[msg;The Game, I am testing-nThe splitting -nof-nstrings.;50]\n"
				+ "[tw;1500;0]\n"
				+ "[tw;1500;0]\n"
				+ "[tw;1500;0]\n"
				+ "[tw;1500;0]\n" + "[tw;1500;0]\n" + "[tw;1500;0]";

		script1 = new MessageEntity(game, null, null, null, 100, 100, -1, -1,
			50, "You must wait :)");

		this.levelInteractiveObjects.add(new TerminalEntity(game, null, null,
			null, 100, 100, "BetaLevel"));

		message1 = new MessagePopupEntity(
			game,
			50,
			"Testing out a possible "
					+ "text that will-ndisplay itself in specific events,-nexcelent plot element.",
			null);

		message2 = new MessagePopupEntity(game, 50,
			"What's this? Another text box?-n"
					+ "And it works fine! Cosmos for the-nwin!!!", null);

		this.levelConstantObjects.add(message1);
		this.levelConstantObjects.add(message2);

		// load background image
		background = ImageLoader.get().getSprite("background.jpg");
		game.player.setCanMove(true);
		// load level
		loadLevel(complex);
	}

	public void act(String source) {
		if (CosmosConstants.debug)
			System.out.println("Entity: " + source
					+ " - is trigerring an event.");

		if (source.equals("testmessage")) message1.setAlive(true); // act out event
		if (source.equals("testmessage2")) message2.setAlive(true); // act out event
	}

	public void onLoad(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, CosmosConstants.WIDTH, CosmosConstants.HEIGHT);
		g.setColor(Color.WHITE);

		// act out loading text
		script1.act();
		script1.draw(g);
		isOnLoadFinished = script1.isDone();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new AlphaLevel(game);
	}

}
