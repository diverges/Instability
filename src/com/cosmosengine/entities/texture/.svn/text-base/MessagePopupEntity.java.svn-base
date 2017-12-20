package com.cosmosengine.entities.texture;

import java.awt.Color;
import java.awt.Graphics;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.SoundManager.CosmosSound;
import com.cosmosengine.SoundManager.SoundLoader;

/**
 * Event base popup message box.
 */
public class MessagePopupEntity extends CosmosEntity {

	private String[] parts;
	private String[] message;
	int y_space = 15;
	int row = 0;
	int charCount = 0;
	int timeCount = 0;
	int doneCount = 0;
	private CosmosSound sound = null;
	boolean playing = false;

	public MessagePopupEntity(GameCanvas game, long millis, String msg,
			String soundRef) {
		super(game, null, null, null, 150, 150, 200, 200, millis);
		parts = msg.split("-n");
		message = new String[parts.length];

		for (int i = 0; i < parts.length; i++) {
			message[i] = "";
		}

		isAlive = false; // objects starts of "dead" until event
		if (soundRef != null) sound = SoundLoader.get().getSound(soundRef);

	}

	@Override
	public void act() {
		// when acting pause game and disable HUD
		game.pause();
		game.hud.setEnabled(false);
		try {
			if (isAlive && !isDone()) {

				if (timeCount < millis / 10) {
					// count every passed amount of milliseconds
					timeCount++;
				} else {
					// print out one character at a time
					if (message[row].length() < parts[row].length()) {
						message[row] = message[row]
								+ parts[row].charAt(charCount);
						charCount++;
					} else {
						row++;
						charCount = 0;
					}
					timeCount = 0;
				}
			} else {
				doneCount++;
				if (doneCount >= 100) {
					// when done unpause
					game.unpause();
					game.hud.setEnabled(true);
					this.setAlive(false);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void setAlive(boolean isAlive) {
		super.setAlive(isAlive);
		if (!playing && isAlive) {
			if (sound != null) sound.playSoundOnce();
			playing = true;
		} else if (playing && !isAlive) {
			if (sound != null) sound.stopSound();
			playing = false;
		}
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics g) {

		g.setColor(Color.GRAY);
		g.fillRect((int) me.x, (int) me.y, (int) me.width, (int) me.height); // draw
		// background
		g.setColor(Color.WHITE);
		for (int i = 0; i < message.length; i++) {
			g.drawString(message[i], (int) me.x + 10, (int) me.y + 10 + y_space);
			y_space += 15;
		}
		y_space = 15;
		g.drawString("Press enter to hide", (int) (me.x + 10), (int) (me.y
				+ me.height - 10));
	}

	public boolean isDone() {
		return message[message.length - 1].equals(parts[message.length - 1]);
	}
}
