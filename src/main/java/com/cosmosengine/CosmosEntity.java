package com.cosmosengine;

import com.cosmosengine.SpriteManager.CosmosSprite;
import com.cosmosengine.SpriteManager.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This is the main base of all entities. Contains everything needed for moving and drawing
 */
public abstract class CosmosEntity {

    protected GameCanvas game;

    // defines if the objects has a right to exist, act, collide, or side scroll
    protected boolean isAlive = true;
    protected double dY;
    protected double dX;

    // Sprite components
    protected CosmosSprite[] sprites;
    protected CosmosSprite[] onDeathSprites;
    private int current = 0;
    protected long millis;
    protected int stepsNeeded;
    protected int step = 0;

    protected Rectangle me = new Rectangle(); // self image of player

    /**
     * Use this constructor to specify width and height of the object.
     *
     * @param game    used to access main class.
     * @param folder  which contains the images
     * @param ref     the name of the images separated by a ';' i.e. name1.png;name2.png;name3.png
     * @param onDeath the name of the images to animate when dead. Must be in folder/death/ folder. Same format as ref.
     * @param x       x-pos of the object
     * @param y       y-pos of the object
     * @param width   width of the object
     * @param height  of the object
     * @param millis  on to use when animating. -1 for no constant animation.
     */
    protected CosmosEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, int width, int height, long millis) {
        String[] imgs;
        this.game = game;
        if (ref != null) {
            imgs = ref.split(";");
            this.sprites = new CosmosSprite[imgs.length];
            for (int i = 0; i < imgs.length; i++) {
                if (folder != null) {
                    sprites[i] = ImageLoader.get().getSprite(folder + "/" + imgs[i]);
                } else {
                    sprites[i] = ImageLoader.get().getSprite(imgs[i]);
                }
            }
        }
        if (onDeath != null) {
            imgs = onDeath.split(";");
            this.onDeathSprites = new CosmosSprite[imgs.length];
            for (int i = 0; i < imgs.length; i++) {
                if (folder != null) {
                    onDeathSprites[i] = ImageLoader.get().getSprite(folder + "/death/" + imgs[i]);
                } else {
                    onDeathSprites[i] = ImageLoader.get().getSprite(imgs[i]);
                }
            }
        }
        this.millis = millis;
        stepsNeeded = (int) (millis / CosmosConstants.PERIOD);
        me.x = x;
        me.y = y;
        me.width = width;
        me.height = height;
    }

    /**
     * Use this constructor to make the width and the height to be the image's width and heigh.
     *
     * @param game    used to access main class.
     * @param folder  which contains the images
     * @param ref     the name of the images separated by a ';' i.e. name1.png;name2.png;name3.png
     * @param onDeath the name of the images to animate when dead. Must be in folder/death/ folder. Same format as ref.
     * @param x       x-pos of the object
     * @param y       y-pos of the object
     * @param millis  on to use when animating. -1 for no constant animation.
     */
    public CosmosEntity(GameCanvas game, String folder, String ref, String onDeath, int x, int y, long millis) {
        String[] imgs;
        this.game = game;
        if (ref != null) {
            imgs = ref.split(";");
            this.sprites = new CosmosSprite[imgs.length];
            for (int i = 0; i < imgs.length; i++) {
                if (folder != null) {
                    sprites[i] = ImageLoader.get().getSprite(folder + "/" + imgs[i]);
                } else {
                    sprites[i] = ImageLoader.get().getSprite(imgs[i]);
                }
            }
        }
        if (onDeath != null) {
            imgs = onDeath.split(";");
            this.onDeathSprites = new CosmosSprite[imgs.length];
            for (int i = 0; i < imgs.length; i++) {
                if (folder != null) {
                    onDeathSprites[i] = ImageLoader.get().getSprite(folder + "/death/" + imgs[i]);
                } else {
                    onDeathSprites[i] = ImageLoader.get().getSprite(imgs[i]);
                }
            }
        }
        if (sprites != null && sprites[0] != null) {
            me.width = sprites[0].getWidth();
            me.height = sprites[0].getHeight();
        }
        this.millis = millis;
        stepsNeeded = (int) (millis / CosmosConstants.PERIOD);
        me.x = x;
        me.y = y;
    }

    /**
     * Returns a point representing the center of the entity.
     *
     * @return
     */
    public Point getPoint() {
        if (sprites != null && sprites[getCurrent()] != null)
            return new Point((int) this.getX() + ((int) this.getWidth() / 2), (int) this.getY() + ((int) this.getHeight() / 2));
        else
            return new Point((int) this.getX() + (me.width / 2), (int) this.getY() + (me.height / 2));
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public double getX() {
        return me.x;
    }

    public void setX(double x) {
        me.x = (int) x;
    }

    public double getY() {
        return me.y;
    }

    public void setY(double y) {
        me.y = (int) y;
    }

    public double getWidth() {
        return me.width;
    }

    public void setWidth(double width) {
        me.width = (int) width;
    }

    public double getHeight() {
        return me.height;
    }

    public void setHeight(double height) {
        me.height = (int) height;
    }

    public CosmosSprite[] getSprites() {
        return sprites;
    }

    public void setSprites(CosmosSprite[] sprites) {
        this.sprites = sprites;
    }

    public Rectangle getBounds() {
        return me;
    }

    public void draw(Graphics g) {
        sprites[current].draw(g, me.x, me.y);
        if (CosmosConstants.DEBUG) {
            g.setColor(Color.WHITE);
            g.drawRect(me.x, me.y, me.width, me.height);
            g.setColor(Color.BLUE);
            g.fillOval(me.x + me.width / 2, me.y + me.height / 2, 3, 3);
        }
    }

    public abstract void collision();

    public abstract void act();

    public void setCurrent(int current) {
        this.current = current;
    }

    protected int getCurrent() {
        return current;
    }
}
