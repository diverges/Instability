package com.cosmosengine.entities.players;

import com.cosmosengine.GameCanvas;

public class SpikeEnemyEntity extends EnemyEntity {

	public static final int NORTH = 1;
	public static final int WEST = 2;
	public static final int EAST = 3;
	public static final int SOUTH = 4;

	private int speed;
	private int direction;
	private int distance;
	private int stepCount = 0;
	private int constAnimTimer;

	public SpikeEnemyEntity(GameCanvas game, String folder, String ref, int x,
			int y, long millis, int direction, int distance, int speed) {
		super(game, folder, ref, null, x, y, 40, 40, millis);
		this.direction = direction;
		this.distance = distance;
		this.speed = speed;
		this.isAlive = true;
	}

	@Override
	public void act() {
		if (isAlive) {
			constAnimTimer++;
			if (constAnimTimer >= 10) {
				constAnimTimer = 0;
				setCurrent(getCurrent() + 1);
				if (getCurrent() == this.sprites.length) {
					setCurrent(0);
				}
			}
			switch (direction) {
				case NORTH:
					if (stepCount < distance) {
						me.y -= speed;
						stepCount++;
					} else if (stepCount == distance) {
						// restart timer
						stepCount = 0;
						speed = -speed;
					}
					break;
				case WEST:
					if (stepCount < distance) {
						me.x -= speed;
						stepCount++;
					} else if (stepCount == distance) {
						// restart timer
						stepCount = 0;
						speed = -speed;
					}
					break;
				case SOUTH:
					if (stepCount < distance) {
						me.y += speed;
						stepCount++;
					} else if (stepCount == distance) {
						// restart timer
						stepCount = 0;
						speed = -speed;
					}
					break;
				case EAST:
					if (stepCount < distance) {
						me.x += speed;
						stepCount++;
					} else if (stepCount == distance) {
						// restart timer
						stepCount = 0;
						speed = -speed;
					}
					break;
			}

		}

	}
}
