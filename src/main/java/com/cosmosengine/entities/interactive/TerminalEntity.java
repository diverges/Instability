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
    private String level;

    /**
     * @param game
     * @param folder
     * @param ref
     * @param x
     * @param y
     * @param levelClassName
     */
    public TerminalEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, String levelClassName) {
        super(game, folder, ref, onDeath, x, y, 80, 80, -1);
        level = levelClassName;
    }

    @Override
    public void collision() {
        if (game.player.getPoint().distance(this.getPoint()) < 20) {
            // stop previous level SOUNDS
            if (game.level.backgroundSound != null)
                game.level.backgroundSound.stopSound();
            switch (level) {
                case "LevelOne":
                    game.startLevel(new LevelOne(game));
                    break;
                case "LevelTwo":
                    game.startLevel(new LevelTwo(game));
                    break;
                case "LevelTwoA":
                    game.startLevel(new LevelTwoA(game));
                    break;
                case "LevelTwoB":
                    game.startLevel(new LevelTwoB(game));
                    break;
                case "LevelThree":
                    game.startLevel(new LevelThree(game));
                    break;
                case "Credits":
                    game.startLevel(new Credits(game));
                    break;
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
