package com.cosmosengine.entities.texture;

import com.cosmosengine.GameCanvas;
import com.cosmosengine.inventory.Item;

/**
 * Extends Block entity but with a restriction. A gate that can only be killed with a custom number of specific items.
 */
public class GateEntity extends BlockEntity {

    private Item killWith;
    private int quantityNeeded;
    private String onDeathObj;

    public GateEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, Item killWith, int quantity, String onDeathObj, int health) {
        super(game, folder, ref, onDeath, x, y, width, height, null, health, false);
        this.killWith = killWith;
        this.quantityNeeded = quantity;
        this.onDeathObj = onDeathObj;
    }

    @Override
    public void deathDrop() {
        game.player.getInventory().remove(killWith, quantityNeeded);
        game.setObjective(onDeathObj);
    }

    @Override
    public int dealDamage(int damage) {
        if (game.player.getInventory().hasEnough(this.killWith, this.quantityNeeded) != null) {
            return super.dealDamage(damage);
        }
        return 0;
    }

}
