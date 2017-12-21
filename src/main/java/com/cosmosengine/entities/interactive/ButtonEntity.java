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
    String msg = "";
    String buttonRef;

    /**
     * Create a button without text
     *
     * @param game
     * @param buttonRef
     * @param folder
     * @param ref
     * @param onDeath
     * @param x_pos
     * @param y_pos
     * @param width
     * @param height
     */
    public ButtonEntity(GameCanvas game, String buttonRef, String folder, String ref, String onDeath, int x_pos, int y_pos, int width, int height) {
        super(game, folder, ref, onDeath, x_pos, y_pos, width, height, -1);
        this.buttonRef = buttonRef;
    }

    /**
     * Create a button with text
     *
     * @param game
     * @param buttonRef
     * @param folder
     * @param ref
     * @param x_pos
     * @param y_pos
     * @param width
     * @param height
     * @param msg
     *
     * @deprecated
     */
    public ButtonEntity(GameCanvas game, String buttonRef, String folder, String ref, String onDeath, int x_pos, int y_pos, int width, int height, String msg) {
        super(game, folder, ref, onDeath, x_pos, y_pos, width, height, -1);
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
            this.sprites[getCurrent()].draw(g, (int) me.x, (int) me.y);
        else {
            if (getCurrent() == 0) {
                g.setColor(Color.LIGHT_GRAY);
                g.fill3DRect((int) me.x, (int) me.y, (int) me.width, (int) me.height, true);
                g.setColor(Color.BLACK);
                g.drawString(msg, (int) me.x + 10, (int) (me.y + me.height / 2 + 3));
                g.setColor(c);
            } else {
                g.setColor(Color.LIGHT_GRAY);
                g.fill3DRect((int) me.x, (int) me.y, (int) me.width, (int) me.height, false);
                g.setColor(Color.BLACK);
                g.drawString(msg, (int) me.x + 10, (int) (me.y + me.height / 2 + 5));
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