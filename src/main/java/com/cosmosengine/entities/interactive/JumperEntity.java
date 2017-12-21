package com.cosmosengine.entities.interactive;

import com.cosmosengine.CosmosConstants;
import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;

import java.awt.Graphics;

/**
 * One way jumper to a specific point.
 */
public class JumperEntity extends CosmosEntity {

    int targetX, targetY;

    public JumperEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, long millis, int targetX, int targetY) {
        super(game, folder, ref, onDeath, x, y, width, height, millis);
        this.targetX = targetX;
        this.targetY = targetY;
    }

    @Override
    public void collision() {

        if (game.player.getPoint().distance(this.getPoint()) < 20) {
            game.player.getBounds().x = CosmosConstants.x_Offset;
            game.player.getBounds().y = CosmosConstants.y_Offset;
            game.level.positionCamera(targetX, targetY); // teleport player
        }

    }

    @Override
    public void act() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        //g.setColor(Color.GREEN);
        //g.drawRect((int) me.x, (int) me.y, (int) me.width, (int) me.height);
    }

}
