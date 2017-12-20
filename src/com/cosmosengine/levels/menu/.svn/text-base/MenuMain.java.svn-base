package com.cosmosengine.levels.menu;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.entities.interactive.ButtonEntity;
import com.cosmosengine.levels.LevelOne;

/**
 * The menu that will start up when the game is loaded.
 */
public class MenuMain extends Menu {
	boolean playing = false;

	public MenuMain(GameCanvas game) {
		super(game);
		/*
		 * String level = ":\n::[b;Start Game;start]\n\n\n" + "::[b;Options;options]\n\n\n" + "::[b;Quit;quit]\n";
		 */
		/**
		 * Margin formula is m = (h/n)/(n+1)<br />
		 * m = margin<br />
		 * n = number of buttons<br />
		 */
		int margin = (CosmosConstants.HEIGHT / 2) / 3;
		levelTextureObjects.add(new ButtonEntity(game, "start", "menumain",
			"start-button-idle.png;start-button-clicked.png", null,
			CosmosConstants.WIDTH - 300, margin + 120, 200, 100));
		/*
		 * levelTextureObjects.add(new EntityButton(game, "options", null, null, CosmosConstants.WIDTH / 2 - 50, margin * 2 + 100, 100, 100, "Options"));
		 */

		levelTextureObjects.add(new ButtonEntity(game, "quit", "menumain",
			"quit-button-idle.png;quit-button-clicked.png", null,
			CosmosConstants.WIDTH - 300, margin * 2 + 170, 200, 100));

		this.background = ImageLoader.get().getSprite(
			"menumain/galaxy_menu.png");

	}

	public void onLoad(Graphics g) {
	}

	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		for (CosmosEntity b : levelTextureObjects) {
			if (b instanceof ButtonEntity) {
				if (((ButtonEntity) b).isInButton(e.getPoint())) {
					ButtonEntity button = (ButtonEntity) b;
					if (button.getRef().equals("start")) {
						game.startLevel(new LevelOne(game));
						break;
					} else if (button.getRef().equals("quit")) {
						System.exit(0);
						break;
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
