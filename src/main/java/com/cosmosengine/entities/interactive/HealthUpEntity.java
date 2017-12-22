package com.cosmosengine.entities.interactive;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.SpriteManager.ImageLoader;

import java.awt.Graphics;

public class HealthUpEntity extends CosmosEntity {

    private CosmosSprite sprite;
    private int amount;

    /**
     * @param amount number to increment power by
     */
    public HealthUpEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, long millis, int amount) {
        super(game, folder, ref, onDeath, x, y, width, height, millis);
        this.sprite = ImageLoader.get().getSprite("hp-armor-up.png");
        this.amount = amount;
    }

    @Override
    public void collision() {
        if (this.isAlive) {
            if (game.player.getPoint().distance(this.getPoint()) < 20) {
                game.player.restore(amount);
                this.isAlive = false;

            }
        }
    }

    @Override
    public void draw(Graphics g) {
        sprite.draw(g, me.x, me.y);
        //g.drawRect((int) me.x, (int) me.y, (int) me.width, (int) me.height);
    }

    @Override
    public void act() {
        // TODO Auto-generated method stub

    }
}
