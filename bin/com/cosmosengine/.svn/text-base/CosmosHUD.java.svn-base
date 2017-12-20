package com.cosmosengine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.cosmosengine.entities.interactive.ButtonEntity;
import com.cosmosengine.interfaces.Clickable;

/**
 * This is the Heads Up Display class. Where the buttons/notifiers will be placed statically on the screen.
 */
public class CosmosHUD implements Clickable {
	GameCanvas game;
	private ArrayList<CosmosEntity> staticEntities = new ArrayList<CosmosEntity>();
	private ButtonEntity pressing;
	private boolean enabled = true;

	public CosmosHUD(GameCanvas game) {
		this.game = game;
		loadHUD();
	}

	private void loadHUD() {
		/*
		 * staticEntities.add(new ButtonEntity(game, "sound", null, null, null, game.getParent().getWidth() - 75, 15, 50, 50, "Sound"));
		 */

		staticEntities.add(new ButtonEntity(game, "Pause", "hud",
			"reset-button-idle.png;reset-button-clicked.png", null, game
				.getParent().getWidth() - 100,
			game.getParent().getHeight() - 70, 100, 50));
		staticEntities.add(new ButtonEntity(game, "Inventory", "hud",
			"inventory-button-idle.png;inventory-button-clicked.png", null,
			game.getParent().getWidth() - 200,
			game.getParent().getHeight() - 70, 100, 50));
	}

	public void draw(Graphics g) {
		if (enabled) {
			g.setColor(Color.WHITE);
			g.drawString("Objective: " + game.getObjective(), 32, 75);
			g.drawString(game.level.name, 32, 35);
			for (CosmosEntity c : staticEntities) {
				c.draw(g);
			}
		}
	}

	public ArrayList<CosmosEntity> getStaticEntities() {
		return staticEntities;
	}

	public void mousePressed(MouseEvent e) {
		if (enabled) {
			for (CosmosEntity b : staticEntities) {
				if (b instanceof ButtonEntity) {
					if (((ButtonEntity) b).isInButton(e.getPoint())) {
						pressing = (ButtonEntity) b;
						pressing.pushDown();
						break;
					}
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (enabled) {
			if (pressing != null) {
				pressing.pullUp();
				if (pressing.isInButton(e.getPoint())) {
					String ref = ((ButtonEntity) pressing).getRef();
					if (ref.equals("Pause")) {
						game.player.kill();
					} else if (ref.equals("Inventory")) {
						// when inventory button is pressed
						if (!game.player.getInventory().isDisplay()) {
							if (game.player.getInventory().show()) {
								game.pause();// pause game and load inventory
							}
						} else {
							if (game.player.getInventory().hide()) {
								game.unpause(); // unpause game and unload
												// inventory
							}
						}
						if (CosmosConstants.debug)
							System.out.println("Load Inventory");
					} else if (ref.equals("sound")) {
						CosmosConstants.sounds = !CosmosConstants.sounds;
					}
				}
				pressing = null;
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
