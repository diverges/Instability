package com.cosmosengine.entities.interactive;

import com.cosmosengine.CosmosEntity;
import com.cosmosengine.GameCanvas;
import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.SpriteManager.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * A clickable Cosmos Object.
 */
public class ButtonEntity extends CosmosEntity {

    /**
     * The message of a button
     *
     * @deprecated
     */
    @Deprecated
    private String msg = "";
    private String buttonRef;

    /**
     * Create a button without text
     *
     * @param game
     * @param buttonRef
     * @param folder
     * @param ref
     * @param onDeath
     * @param xPos
     * @param yPos
     * @param width
     * @param height
     */
    public ButtonEntity(GameCanvas game, String buttonRef, String folder, String ref, String onDeath, int xPos, int yPos, int width, int height) {
        super(game, folder, ref, onDeath, xPos, yPos, width, height, -1);
        this.buttonRef = buttonRef;
    }

    /**
     * Create a button with text
     *
     * @param game
     * @param buttonRef
     * @param folder
     * @param ref
     * @param xPos
     * @param yPos
     * @param width
     * @param height
     * @param msg
     *
     * @deprecated
     */
    @Deprecated
    public ButtonEntity(GameCanvas game, String buttonRef, String folder, String ref, String onDeath, int xPos, int yPos, int width, int height, String msg) {
        super(game, folder, ref, onDeath, xPos, yPos, width, height, -1);
        this.msg = msg;
        this.buttonRef = buttonRef;
        if (ref != null) {
            this.sprites = new CosmosSprite[2];
            sprites[0] = ImageLoader.get().getSprite(ref.split(";")[0]);
            sprites[1] = ImageLoader.get().getSprite(ref.split(";")[1]);
        }
        this.setCurrent(0);
    }

    public boolean isInButton(Point point) {
        return getBounds().contains(point);
    }

    @Override
    public Rectangle getBounds() {
        return me;
    }

    public String getRef() {
        return buttonRef;
    }

    @Override
    public void draw(Graphics g) {
        Color c = g.getColor();
        if (this.sprites != null)
            this.sprites[getCurrent()].draw(g, me.x, me.y);
        else {
            if (getCurrent() == 0) {
                g.setColor(Color.LIGHT_GRAY);
                g.fill3DRect(me.x, me.y, me.width, me.height, true);
                g.setColor(Color.BLACK);
                g.drawString(msg, me.x + 10, (me.y + me.height / 2) + 3);
                g.setColor(c);
            } else {
                g.setColor(Color.LIGHT_GRAY);
                g.fill3DRect(me.x, me.y, me.width, me.height, false);
                g.setColor(Color.BLACK);
                g.drawString(msg, me.x + 10, (me.y + me.height / 2) + 5);
                g.setColor(c);
            }
        }

    }

    public void pushDown() {
        setCurrent(1);
    }

    public void pullUp() {
        setCurrent(0);
    }

    @Override
    public void act() {
    }

    @Override
    public void collision() {
    }
}
