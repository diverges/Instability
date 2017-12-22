package com.cosmosengine.inventory;

import com.cosmosengine.CosmosConstants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * ItemSlot contains items and methods to combine/remove/add other items.
 */
public class ItemSlot {
    public static final int DEFAULT_MAX = 24;

    private int max; // maximum number of this item per
    // slot
    private int quantity = 0; // current quantity in slot
    private Rectangle me = new Rectangle();

    int x;
    private int y;

    private Item item;

    public ItemSlot(int x, int y, int max) {
        this.x = x;
        this.y = y;
        me.x = x;
        me.y = y;
        me.width = 40;
        me.height = 40;
        this.max = max;

    }

    /**
     * Draw the specific item in the Inventory, must be 40 by 40 pixels.
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 40, 40);
        if (item != null) {
            item.draw(g, x, y);
            // display current quantity
            g.setColor(Color.RED);
            if (quantity != 1)
                g.drawString(Integer.toString(quantity), x, y + 10);
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

    public Rectangle getBounds() {
        return me;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMax() {
        return max;
    }
}
