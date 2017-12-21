package com.cosmosengine.entities.interactive;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.inventory.Item;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Dropped Items are generated when a player drops an item from inventory, or a block drops it when destroyed. It is made slightly transparent to see blocks under it.
 */
public class DroppedItemEntity extends CosmosEntity {
    private Item onPickup = null;
    private int quantity = 0;
    private boolean justDropped = true;

    /**
     * @param game
     * @param folder
     * @param ref
     * @param onDeath
     * @param x
     * @param y
     * @param width
     * @param height
     * @param millis
     * @param onPickup
     */
    public DroppedItemEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, long millis, Item onPickup, int quantity, boolean waitForPlayerToMove) {
        super(game, folder, ref, onDeath, x, y, width, height, millis);
        this.onPickup = onPickup;
        this.quantity = quantity;
        justDropped = waitForPlayerToMove;
    }

    @Override
    public void collision() {
        if (this.isAlive) {
            if (game.player.getPoint().distance(this.getPoint()) < 20) {
                if (!justDropped) {
                    onPickUp();
                }

            } else
                justDropped = false;
        }

    }

    public void onPickUp() {
        this.isAlive = !game.player.getInventory().add(onPickup, quantity);
    }

    @Override
    public void act() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Composite temp = g2.getComposite();
        int type = AlphaComposite.SRC_OVER;
        float alpha = 0.75f;
        AlphaComposite rule = AlphaComposite.getInstance(type, alpha);
        g2.setComposite(rule);
        super.draw(g2);
        g2.setComposite(temp);
    }

}
