package com.cosmosengine.inventory;

import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.SpriteManager.ImageLoader;

import java.awt.Graphics;

/**
 * The main class for all items. Contains the necessary variables and methods the item needs to be used.
 */
public class Item {
    private CosmosSprite sprite;
    private String name;
    private String ref;
    private boolean stackable;

    public Item(String ref, String name, boolean stackable) {
        this.sprite = ImageLoader.get().getSprite(ref);
        this.name = name;
        this.stackable = stackable;
        this.ref = ref;
    }

    public void draw(Graphics g, int x, int y) {
        sprite.draw(g, x, y);
    }

    public boolean isStackable() {
        return stackable;
    }

    public String getName() {
        return name;
    }

    public String getRef() {
        return ref;

    }

}
