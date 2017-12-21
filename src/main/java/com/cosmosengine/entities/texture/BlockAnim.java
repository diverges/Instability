package com.cosmosengine.entities.texture;

import com.cosmosengine.GameCanvas;
import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.SpriteManager.ImageLoader;
import com.cosmosengine.inventory.Item;

import java.awt.Graphics;

/**
 * Extends Block Entity and drops specific items according to the drill power of the player.
 */
public class BlockAnim extends BlockEntity {

    CosmosSprite[] constAnimSprites;
    int consAnimCurrent = 0;
    // constant animation timer
    int constAnimTimer = 0;
    int constAnimStep = 0;

    public BlockAnim(GameCanvas game, String folder, String ref, String onDeath, String consAnim, int x, int y, int width, int height, int health, boolean respawn, Item onDeathDrop) {
        super(game, folder, ref, onDeath, x, y, width, height, onDeathDrop, health, respawn);
        String[] imgs = null;
        if (ref != null) {
            imgs = consAnim.split(";");
            this.constAnimSprites = new CosmosSprite[imgs.length];
            for (int i = 0; i < imgs.length; i++) {
                if (folder != null)
                    constAnimSprites[i] = ImageLoader.get().getSprite(folder + "/constant/" + imgs[i]);
            }
        }
    }

    @Override
    public void act() {
        super.act();
        if (isAlive) {
            constAnimTimer++;
            if (constAnimTimer >= 10) {
                constAnimTimer = 0;
                constAnimStep++;
                if (constAnimStep == constAnimSprites.length) {
                    constAnimStep = 0;
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if (isBeingCollidedDown || isBeingCollidedUp || isBeingCollidedLeft || isBeingCollidedRight || dying)
            super.draw(g);
        else
            constAnimSprites[constAnimStep].draw(g, me.x, me.y);

    }
}
