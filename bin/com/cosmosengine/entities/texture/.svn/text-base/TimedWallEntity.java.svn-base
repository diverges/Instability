package com.cosmosengine.entities.texture;

import java.awt.Color;
import java.awt.Graphics;

import com.cosmosengine.GameCanvas;

/**
 * Walls which appear on a timer blocking the players path. (or hurting the player)
 */
public class TimedWallEntity extends WallEntity {

	int timeCount = 0; // ON and OFF timer

	/**
	 * @param millis
	 *            - specific ON and OFF time.
	 * @param orientation
	 *            - 0: Horizontal 1: Vertical
	 */
	public TimedWallEntity(GameCanvas game, String folder, String ref,
			String onDeath, int x, int y, long millis, int orientation) {
		// load super constructor
		super(game, folder, ref, onDeath, x, y, 40, 20, millis);
		// set orientation
		if (orientation == 1) {
			// if Horizontal
			me.y += 10;
			me.width = 40;
			me.height = 20;
		} else if (orientation == 0) {
			// if Vertical
			me.x += 10;
			me.width = 20;
			me.height = 40;
		}
	}

	@Override
	public void act() {
		// count millis
		if (timeCount < millis / 10) {
			timeCount++;
		} else if (timeCount == millis / 10) {
			// restart timer
			timeCount = 0;
			dying = !dying;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		if (!dying)
			g.drawRect((int) me.x, (int) me.y, (int) me.width, (int) me.height);

	}

}
