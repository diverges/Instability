package com.cosmosengine.inventory;

import com.cosmosengine.CosmosConstants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * ItemSlot contains items and methods to combine/remove/add other items.
 */
public class ItemSlot {
    public static final int DEFAULT_MAX = 24;

    private int max; // maximum number of this item per slot
    private int quantity = 0; // current quantity in slot
    private Rectangle bounds = new Rectangle(40, 40);
    private Item item;

    public ItemSlot(int max) {
        this.max = max;

    }

    /**
     * Draw the specific item in the Inventory
     *
     * @param g
     */
    public void draw(Graphics g, int x, int y, int slotSize) {
        bounds.x = x;
        bounds.y = y;
        bounds.width = slotSize;
        bounds.height = slotSize;

        g.setColor(Color.BLACK);
        g.drawRect(x, y, slotSize, slotSize);
        if (item != null) {
            item.draw(g, x, y);
            // display current quantity
            g.setColor(Color.RED);
            if (quantity != 1) {
                CosmosConstants.drawStringFromTop((Graphics2D) g, Integer.toString(quantity), x + 1, y + 1);
            }
        }
    }

    /**
     * Adds a number of items to this slot.
     *
     * @param amount
     */
    public void add(Item item, int amount) {
        this.quantity += amount;
        this.item = item;
    }

    /**
     * Check if another item can be stored in this slot.
     *
     * @param amount number of items to be added
     *
     * @return
     */
    public boolean hasSpace(int amount) {
        return quantity + amount <= max;
    }

    public boolean isEmpty() {
        return item == null;
    }

    public boolean isFull() {
        if (item != null)
            return !item.isStackable() && quantity == max;
        return quantity == max;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void remove(int quantity) {
        this.quantity -= quantity;
        if (this.quantity == 0)
            this.item = null;
    }

    public String combine(ItemSlot cS2, ItemSlot pS) {
        if (cS2.getItem() == null || this.item == null)
            return "You need two elements to combine.";
        Item cS2Item = cS2.getItem();
        if (!pS.isEmpty()) {
            pS.remove(pS.getQuantity());
        }
        boolean combined = false;
        for (Combination c : CosmosConstants.COMBINATIONS) {
            if (c.getBlockName1().equals(this.item.getName()) && c.getBlockName2().equals(cS2Item.getName())) {
                if (c.getQuantityNeeded1() == this.getQuantity() && c.getQuantityNeeded2() == cS2.getQuantity()) {
                    pS.add(c.getItemMade(), c.getQuantityMade());
                    combined = true;
                    break;
                }
            } else if (c.getBlockName1().equals(cS2Item.getName()) && c.getBlockName2().equals(this.item.getName())) {
                if (c.getQuantityNeeded1() == cS2.getQuantity() && c.getQuantityNeeded2() == this.getQuantity()) {
                    pS.add(c.getItemMade(), c.getQuantityMade());
                    combined = true;
                    break;
                }
            }
        }
        if (combined)
            return "Success!";
        else
            return "Not a valid combination.";

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getMax() {
        return max;
    }
}
