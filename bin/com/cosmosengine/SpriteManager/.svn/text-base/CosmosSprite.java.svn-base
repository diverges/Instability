package com.cosmosengine.SpriteManager;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Base class for all sprites.
 */
public class CosmosSprite {

	private Image img;

	public CosmosSprite(Image img) {
		this.img = img;
	}

	public int getWidth() {
		return img.getWidth(null);
	}

	public int getHeight() {
		return img.getHeight(null);
	}

	public void draw(Graphics g, int x, int y) {
		g.drawImage(img, x, y, null);
	}
}
