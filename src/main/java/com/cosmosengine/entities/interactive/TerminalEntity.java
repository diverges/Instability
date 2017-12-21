package com.cosmosengine.entities.interactive;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.levels.Credits;
import com.cosmosengine.levels.LevelOne;
import com.cosmosengine.levels.LevelThree;
import com.cosmosengine.levels.LevelTwo;
import com.cosmosengine.levels.LevelTwoA;
import com.cosmosengine.levels.LevelTwoB;

import java.awt.Graphics;

/**
 * A terminal in which the player will be able to be moved into a new level.
 */
public class TerminalEntity extends CosmosEntity {
    String level;

    /**
     * @param game
     * @param folder
     * @param ref
     * @param x
     * @param y
     * @param width
     * @param height
     * @param millis
     * @param level  - target level
     */
    public TerminalEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, String levelClassName) {
        super(game, folder, ref, onDeath, x, y, 80, 80, -1);
        level = levelClassName;
    }

    @Override
    public void collision() {
        if (game.player.getPoint().distance(this.getPoint()) < 20) {
            // stop previous level sounds
            if (game.level.bg_sound != null)
                game.level.bg_sound.stopSound();

            if (level.equals("LevelOne")) {
                game.startLevel(new LevelOne(game));
            } else if (level.equals("LevelTwo")) {
                game.startLevel(new LevelTwo(game));
            } else if (level.equals("LevelTwoA")) {
                game.startLevel(new LevelTwoA(game));
            } else if (level.equals("LevelTwoB")) {
                game.startLevel(new LevelTwoB(game));
            } else if (level.equals("LevelThree")) {
                game.startLevel(new LevelThree(game));
            } else if (level.equals("Credits")) {
                game.startLevel(new Credits(game));

            }
        }

    }

    @Override
    public void act() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        // g.setColor(Color.WHITE);
        // g.drawOval((int) me.x, (int) me.y, (int) me.width, (int) me.height);
    }

}
