package com.cosmosengine.entities.interactive;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;

import java.awt.Graphics;

/**
 * Temporarily increases the players drill power until the end of the level.
 */
public class DrillUpEntity extends CosmosEntity {

    int amount = 0;

    /**
     * @param amount number to increment power by
     */
    public DrillUpEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, long millis, int amount) {
        super(game, folder, ref, onDeath, x, y, width, height, millis);
        this.amount = amount;
    }

    @Override
    public void collision() {
        if (this.isAlive) {
            if (game.player.getPoint().distance(this.getPoint()) < 20) {
                game.player.setDamagePower(game.player.getDamagePower() + amount);
                this.isAlive = false;

            }
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        //g.setColor(Color.ORANGE);
        //g.drawRect((int) me.x, (int) me.y, (int) me.width, (int) me.height);
    }

    @Override
    public void act() {
        // TODO Auto-generated method stub

    }

}
